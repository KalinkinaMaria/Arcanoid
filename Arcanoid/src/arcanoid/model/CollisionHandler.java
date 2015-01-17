/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.model;

import arcanoid.events.SpritesCollidedEvent;
import arcanoid.events.SpritesCollidedListener;
import arcanoid.service.Buffer;
import arcanoid.service.SpeedVector;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;
import com.golden.gamedev.object.collision.CollisionRect;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Обработчик столкновений
 * 
 * @author Елена
 */
public class CollisionHandler implements SpritesCollidedListener {
    /** Таблица соответствий элемента поля со спрайтом */
    private Buffer table;
    private boolean block = false;
    public CollisionHandler (Buffer table) {
        this.table = table;
    }
    
    /**
     * Определить тип столкновения
     * 
     * @param storage хранилище со столкнувшимися спрайтами
     */
    public void getCollisionType(Map storage) {
        
    }
    
    /**
     * Получить элемент
     * 
     * @param sprite спрайт, который соответствует элементу
     */
    private FieldElement getElement (Sprite sprite) {
        return table.getElement(sprite);
    }

    private SpeedVector.Axis getAxis(int side) {
        SpeedVector.Axis axis;
        if (side == CollisionBounds.LEFT_COLLISION || side == CollisionBounds.RIGHT_COLLISION) {
            axis = SpeedVector.Axis.Y;
        } else {
            axis = SpeedVector.Axis.X;
        }
        return axis;
    }
    
    private void formListForElement(Sprite sprite, Map storage, ArrayList<Sprite> list) {
        Set keySet = storage.keySet();
        if (keySet.contains(sprite)) {
            Sprite[] valueSprites = (Sprite[]) storage.get(sprite);
            for (Sprite value:valueSprites) {
                if (!list.contains(value)) {
                    list.add(value);
                    formListForElement(value, storage, list);
                }
            }
        }
    }
    
    private ArrayList<Sprite> getSystem(Map storage) {
        ArrayList<Sprite> result = new ArrayList<>();
        // Создание системы связанных соударившихся элементов
        for ( Object keySprite : storage.keySet()) {
            result.add((Sprite)keySprite);
            formListForElement((Sprite)keySprite, storage, result);
            if (result.size() >= 3) {
                return result;
            } else {
                result = new ArrayList<>();
            }
        }
        
        return result;
    }
    
    private void handleSystem(ArrayList<Sprite> system) {
        ArrayList<FieldElement> cloneElements = new ArrayList<>();
        ArrayList<FieldElement> elements = new ArrayList<>();
        HashMap<FieldElement, ArrayList<FieldElement>> elementsAsOne = new HashMap<>();
        for (Sprite sprite:system) {
            FieldElement element = table.getElement(sprite);
            elements.add(element);
            cloneElements.add(element.clone());
        }
        for (FieldElement element:elements) {
            ArrayList<FieldElement> elementArray = new ArrayList<>();
            for (FieldElement other:cloneElements) {
                if (elements.indexOf(element) != cloneElements.indexOf(other)) {
                    elementArray.add(other);
                }
            }
            elementsAsOne.put(element, elementArray);
        }
        for (Map.Entry<FieldElement, ArrayList<FieldElement>> entrySet : elementsAsOne.entrySet()) {
            try {
                FieldElement key = entrySet.getKey();
                ArrayList<FieldElement> values = entrySet.getValue();
                Class classElement = values.get(0).getClass();
                Constructor[] construct =  classElement.getDeclaredConstructors();
                Object otherElement = construct[0].newInstance();
                SpeedVector resultSpeed = new SpeedVector();
                for (FieldElement value: values) {
                    resultSpeed = resultSpeed.sum(value.speed());
                }
                ((FieldElement)otherElement).setSpeed(resultSpeed);
                key.handleCollision((FieldElement)otherElement);
            } catch (InstantiationException ex) {
                Logger.getLogger(CollisionHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(CollisionHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(CollisionHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(CollisionHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void spritesCollided(SpritesCollidedEvent e) {
        ArrayList<Sprite> system;
        FieldElement element = getElement(e.activeSprite());
        FieldElement keyElement;
        // Столкновение произошло с границей
        if (e.passiveSprite() == null) {
            // Падение на нижнию границу элемента, отвечающего за успех игры
            if (element instanceof ChangingGameState && e.side() == CollisionBounds.BOTTOM_COLLISION) {
                ((ChangingGameState)element).handleChangingGameState();
            } else if (element instanceof Bouncing) {
                // Столкновение с разрешенными границами
                element.setRightPosition(e.xBound());
                if (e.xBound() == 0 ) {
                    ((Bouncing)element).handleCollision(getAxis(e.side()), null);
                } else {
                    element.setSpeed(new SpeedVector());
                }
                
            } else {
                //Столкновение не отскакивающих элементов
                element.setRightPosition(e.xBound());
                element.setSpeed(new SpeedVector());
            }   
        } else {
            Map passive = e.passiveSprite();
            // Выявить систему(если шарики в круге)
            system = getSystem(passive);
            // Если есть система
            if (!system.isEmpty()) {
                // Обработать столкновения внутри системы
                handleSystem(system);
                // Удалить спрайты, входящие в систему
                for ( Object keySprite : passive.keySet()) {
                    if (system.contains((Sprite)keySprite)) {
                        passive.remove((Sprite)keySprite);
                    }
                }
            }
            // Обработать спрайты вне системы
            for ( Object keySprite : passive.keySet()) {
                keyElement = table.getElement((Sprite) keySprite);
                Sprite[] valueSprites = (Sprite[]) passive.get(keySprite);
                for (Sprite value:valueSprites) {
                    if (!system.contains(value)) {
                        CollisionRect intersectionRect = CollisionManager.getIntersectionRect(((Sprite)keySprite).getX(), ((Sprite)keySprite).getY(), ((Sprite)keySprite).getWidth(), ((Sprite)keySprite).getHeight(), value.getX(), value.getY(), value.getWidth(), value.getHeight());
                        System.out.println(intersectionRect.width);
                        System.out.println(intersectionRect.height);
                        FieldElement originObject = table.getElement(value);
                        FieldElement cloneKeyElement = keyElement.clone();
                        FieldElement cloneValueElement = originObject.clone();
                        double width = originObject.size().width();
                        double height = originObject.size().height();
                        if (width > keyElement.size().width()) {
                            width = keyElement.size().width();
                        }
                        if (height > keyElement.size().height()) {
                            height = keyElement.size().height();
                        }
                        if (block && intersectionRect.width < width/3 && intersectionRect.height < height/3) {
                            block = false;
                        }
                        if (!block) {
                        originObject.handleCollision(cloneKeyElement);
                        keyElement.handleCollision(cloneValueElement);
                        }
                        if (intersectionRect.width > width/3 && intersectionRect.height > height/3) {
                            block = true;
                        }
                        table.deletePair(cloneKeyElement);
                        table.deletePair(cloneValueElement);
                    }
                }
            }
        }
    }
}
