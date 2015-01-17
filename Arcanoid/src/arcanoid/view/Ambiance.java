/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.view;

import arcanoid.collision.CollisionObjectWithBoundary;
import arcanoid.collision.CollisionObjectWithObject;
import arcanoid.events.GameFieldChangeEvent;
import arcanoid.events.GameFieldChangeListener;
import arcanoid.events.GameStateChangeListener;
import arcanoid.model.Bounced;
import arcanoid.model.Bouncing;
import arcanoid.model.ChangingGameState;
import arcanoid.model.CollisionHandler;
import arcanoid.model.FieldElement;
import arcanoid.model.Managable;
import arcanoid.service.Buffer;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Класс изображения обстановки игрового поля
 * @author Мария
 */
public class Ambiance implements GameFieldChangeListener {
    /** Задний фон */
    private Background background;
    /** Отскакивающие элементы */
    private ArrayList<SpriteGroup> bouncing;
    /** Элементы управляющие элементами от которых отскакиваешь*/
    private SpriteGroup manageBounced;
    /** Элементы от которых отскакиваешь*/
    private SpriteGroup bounced;
    /** Таблица сталкивающихся элементов */
    private Map<String,String> collidedGroups;
    /** Таблица соответствия элемента и спрайта*/
    private Buffer table;
    /** Таблица соответствия элемента и его картики*/
    private HashMap<String, String> images;
    /** Отображаемые элементы*/
    private ArrayList<ViewFieldElement> viewElements;
    /** Контроллер столкновения объекта с объектом*/
    private CollisionObjectWithObject objectsCollision;
    /** Контроллеры столкновения объекта с границей*/
    private ArrayList<CollisionObjectWithBoundary> bouncingCollisions;

    /**
     * Конструктор
     * @param buffer Таблица соответствия элемента и спрайта
     */
    public  Ambiance(Buffer buffer) {
        // Инициализировать поля класса
        table = buffer;
        viewElements = new ArrayList<>();
        images = new HashMap<>();
        bouncing = new ArrayList<>();
        bouncingCollisions = new ArrayList<>();
        collidedGroups = new HashMap<>();
        bounced = new SpriteGroup("Bounced");
        manageBounced = new SpriteGroup("manageBounced");
        // Добавить путь к изображениям в таблицу
        images.put("Ball", "img/ball.png");
        images.put("Racket", "img/r.png");
    }
    
    /**
     * Добавление элемента в обстановку
     * @param e Событие добавления элемента
     */
    @Override
    public void addElement(GameFieldChangeEvent e) {
        SpriteGroup group;
        //Проверка наличия файла с изображением
        try {
            BufferedImage image  = ImageIO.read(new File(getImage(e.element)));
            Sprite sprite = new Sprite(image, e.position.x, e.position.y);
            //Добавление спрайта к элементу
            if (e.element instanceof Bouncing) {
                String name  = "Bouncing_" + String.valueOf(bouncing.size() - 1);
                group = new SpriteGroup(name);
                group.add(sprite);
                bouncing.add(group);
            } else if (e.element instanceof Managable) {
                manageBounced.add(sprite);
            } else if (e.element instanceof Bounced) {
                bounced.add(sprite);
            }
            // Добавление соответствия элемента к спрайту в таблицу
            table.addPair(e.element, sprite);
            e.element.setPosition(e.position);
            // Добавление отображения объектов
            switch(getClassName(e.element)) {
                case "Ball":
                    viewElements.add(new ViewBall(sprite, null));
                    break;
                case "Racket":
                    viewElements.add(new ViewRacket(sprite, null));
                    break;
                default:break;
            }
        } catch (IOException ex) {
            Logger.getLogger(Ambiance.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    /**
     * Регистрация группы спрайтов
     * @param playField Игровое поле GTGE
     */
    public void registerSpriteGroups (PlayField playField) {
        //Добавление групп спрайтов на поле
        playField.addGroup(bounced);
        playField.addGroup(manageBounced);
        for (SpriteGroup group:bouncing) {
            playField.addGroup(group);
        }
    }
    
    /**
     * Удаление элемента из игрового поля
     * @param e Событие удаления элемента
     */
    @Override
    public void removeElement(GameFieldChangeEvent e) {
        Sprite sprite = table.getSprite(e.element); // Получить спрайт по элементу
        // Удалить элемент и спрайт
        table.deletePair(e.element);
        bounced.remove(sprite);
        manageBounced.remove(sprite);
        for (SpriteGroup group: bouncing) {
            group.remove(sprite);
        }
    }

    /**
     * Изменение элемента
     * @param e Событие удаления элемента
     */
    @Override
    public void changeElement(GameFieldChangeEvent e) {
        // Добавление элемента
        if (e.type == GameFieldChangeEvent.ChangingType.creation) {
            addElement(e);
        } else { // Удаление элемента
            removeElement(e);
        }
    }
    
    /**
     * Вернуть имя класса элемента
     * @param element элемент поля
     * @return имя класса элемента
     */
    private String getClassName (FieldElement element) {
        String className = element.getClass().toString();
        className = className.substring(className.lastIndexOf('.') + 1);
        return className;
    }
    
    /**
     * Получить путь к изображению элемента
     * @param element элемент
     * @return путь к изображению элемента
     */
    private String getImage(FieldElement element) {
        return images.get(getClassName(element));
    }
    
    /**
     * Задать обработчик коллизий с границами
     * @param playField игровое поле GTGE
     */
    public void setCollisionBounds(PlayField playField) {
        CollisionHandler handler = new CollisionHandler(table);
        // Добавление слушателя для элементов от которых отскакивает
        CollisionObjectWithBoundary bounderCollision = new CollisionObjectWithBoundary(0, 0, 808, 640);
        bounderCollision.addSpritesCollidedListener(handler);
        playField.addCollisionGroup(bounced, null, bounderCollision);
        bounderCollision = new CollisionObjectWithBoundary(0, 0, 808, 640);
        // Добавление слушателя для элемента управляющем элементами от которых отскакивает
        handler = new CollisionHandler(table);
        bounderCollision.addSpritesCollidedListener(handler);
        playField.addCollisionGroup(manageBounced, null, bounderCollision);        
        // Добавление слушателей для отскакивающих элементов
        for (SpriteGroup group : bouncing) {
            CollisionObjectWithBoundary collisionBouncing = new CollisionObjectWithBoundary(78, 0, 628, 640);
            bouncingCollisions.add(collisionBouncing);
            handler = new CollisionHandler(table);
            collisionBouncing.addSpritesCollidedListener(handler);
            playField.addCollisionGroup(group, null, collisionBouncing);
        }
    }
    
    /**
     * Задать обработчик коллизий с объектами
     * @param playField игровое поле GTGE
     */
    public void setCollisionObjects(PlayField playField) {
        CollisionHandler handler;
        for (Map.Entry<String, String> entrySet : collidedGroups.entrySet()) {
            String key = entrySet.getKey();
            String value = entrySet.getValue();
            if (bounced.getSprites().length != 0 && table.getElement(bounced.getSprites()[0])!=null) {
                if (key.compareTo(getClassName(table.getElement(bounced.getSprites()[0]))) == 0) {
                    if (manageBounced.getSprites().length != 0 && table.getElement(manageBounced.getSprites()[0])!=null) {
                        if (getClassName(table.getElement(manageBounced.getSprites()[0])).compareTo(value) == 0) {
                            objectsCollision = new CollisionObjectWithObject();
                            handler = new CollisionHandler(table);
                            objectsCollision.addSpritesCollidedListener(handler);
                            playField.addCollisionGroup(bounced, manageBounced, objectsCollision);
                        }
                    }
                    for (SpriteGroup bounce:bouncing) {
                        if (bounce.getSprites().length != 0) {
                            if (getClassName(table.getElement(bounce.getSprites()[0])).compareTo(value) == 0) {
                                objectsCollision = new CollisionObjectWithObject();
                                handler = new CollisionHandler(table);
                                objectsCollision.addSpritesCollidedListener(handler);
                                playField.addCollisionGroup(bounced, bounce, objectsCollision);
                            }
                        }
                    }
                }
            }
            if (manageBounced.getSprites().length != 0) {
                if (key.compareTo(getClassName(table.getElement(manageBounced.getSprites()[0]))) == 0) {
                    if (bounced.getSprites().length != 0 && table.getElement(bounced.getSprites()[0])!=null) {
                        if (getClassName(table.getElement(bounced.getSprites()[0])).compareTo(value) == 0) {
                            objectsCollision = new CollisionObjectWithObject();
                            handler = new CollisionHandler(table);
                            objectsCollision.addSpritesCollidedListener(handler);
                            playField.addCollisionGroup(bounced, manageBounced, objectsCollision);
                        }
                    }
                    for (SpriteGroup bounce:bouncing) {
                        if (bounce.getSprites().length != 0 && table.getElement(bounce.getSprites()[0]) != null) {
                            if (getClassName(table.getElement(bounce.getSprites()[0])).compareTo(value) == 0) {
                                objectsCollision = new CollisionObjectWithObject();
                                handler = new CollisionHandler(table);
                                objectsCollision.addSpritesCollidedListener(handler);
                                playField.addCollisionGroup(manageBounced, bounce, objectsCollision);
                            }
                        }
                    }
                }
            }
            for (SpriteGroup bounce:bouncing) {
                if (bounce.getSprites().length != 0 && table.getElement(bounce.getSprites()[0]) != null) {
                    if (getClassName(table.getElement(bounce.getSprites()[0])).compareTo(key) == 0 ) {
                        if (bounced.getSprites().length != 0 && table.getElement(bounced.getSprites()[0])!=null) {
                            if (getClassName(table.getElement(bounced.getSprites()[0])).compareTo(value) == 0) {
                                objectsCollision = new CollisionObjectWithObject();
                                handler = new CollisionHandler(table);
                                objectsCollision.addSpritesCollidedListener(handler);
                                playField.addCollisionGroup(bounced, bounce, objectsCollision);
                            }
                        }
                        if (manageBounced.getSprites().length != 0 && table.getElement(manageBounced.getSprites()[0])!=null) {
                            if (getClassName(table.getElement(manageBounced.getSprites()[0])).compareTo(value) == 0) {
                                objectsCollision = new CollisionObjectWithObject();
                                handler = new CollisionHandler(table);
                                objectsCollision.addSpritesCollidedListener(handler);
                                playField.addCollisionGroup(manageBounced, bounce, objectsCollision);
                            }
                        }
                        for (SpriteGroup bounce1:bouncing) {
                            if (bounce1.getSprites().length != 0 && table.getElement(bounce1.getSprites()[0]) != null) {
                                if (getClassName(table.getElement(bounce1.getSprites()[0])).compareTo(value) == 0 && bouncing.indexOf(bounce) < bouncing.indexOf(bounce1)) {
                                    objectsCollision = new CollisionObjectWithObject();
                                    handler = new CollisionHandler(table);
                                    objectsCollision.addSpritesCollidedListener(handler);
                                    playField.addCollisionGroup(bounce1, bounce, objectsCollision);
                                }
                            }
                        }
                    }
                }
            }
        }
        
    }
    
    /**
     * установить попытку столкновения с границей
     * @param playField игровое поле GTGE
     */
    public void setAttemptCollisionBounds(PlayField playField) {
        // Удалить группы коллизий для контроллеров столкновения объекта с границей
        for (CollisionObjectWithBoundary collision: bouncingCollisions) {
            playField.removeCollisionGroup(collision);
        }
        // Задать контроллеры для отскакивающих элементов
        for (SpriteGroup group : bouncing) {
            CollisionObjectWithBoundary collisionBouncing = new CollisionObjectWithBoundary(0, 0, 808, 600);
            bouncingCollisions.add(collisionBouncing);
            CollisionHandler handler = new CollisionHandler(table);
            collisionBouncing.addSpritesCollidedListener(handler);
            playField.addCollisionGroup(group, null, collisionBouncing);
        }
    }
    
    /**
     * Добавление пары группы столкновения
     * @param first название класса первого элемента
     * @param second название класса второго элемента
     */
    public void addCollidedGroupPair(String first, String second) {
        collidedGroups.put(first, second);
    }
    
    /**
     * Установить связь с изменением состояния игры
     * @param object Слушатель падения мяча
     */
    public void setConnectionWithGhangingGameStateElement(GameStateChangeListener object) {
        FieldElement element;
        // Для всех отображаемых элементов добавить слущателя падения мяча
        for (ViewFieldElement viewElement:viewElements) {
           element = table.getElement(viewElement.getViewSprite());
           if (element instanceof ChangingGameState) {
               ((ChangingGameState)element).addGameStateChangeListener(object);
           }
        }
    }
}
