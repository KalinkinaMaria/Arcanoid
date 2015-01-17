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
import com.golden.gamedev.Game;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.collision.CollisionBounds;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Мария
 */
public class Ambiance implements GameFieldChangeListener {
    /** Задний фон */
    private Background background;
    /** Отскакивающие элементы */
    private ArrayList<SpriteGroup> bouncing;
    private SpriteGroup manageBounced;
    private SpriteGroup bounced;

    /** Таблица сталкивающихся элементов */
    private Map<String,String> collidedGroups;
    private Buffer table;
    private HashMap<String, String> images;
    private ArrayList<ViewFieldElement> viewElements;
    private CollisionObjectWithBoundary bounderCollision;
    private CollisionObjectWithObject objectsCollision;
    private ArrayList<CollisionObjectWithBoundary> bouncingCollisions;

    public  Ambiance(Buffer buffer) {
        table = buffer;
        viewElements = new ArrayList<>();
        images = new HashMap<>();
        bouncing = new ArrayList<>();
        bouncingCollisions = new ArrayList<>();
        collidedGroups = new HashMap<>();
        bounced = new SpriteGroup("Bounced");
        manageBounced = new SpriteGroup("manageBounced");
        images.put("Ball", "img/ball.png");
        images.put("Racket", "img/r.png");
    }
    
    @Override
    public void addElement(GameFieldChangeEvent e) {
        SpriteGroup group;
        try {
            BufferedImage image  = ImageIO.read(new File(getImage(e.element)));
            Sprite sprite = new Sprite(image, e.position.x, e.position.y);
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
            table.addPair(e.element, sprite);
            e.element.setPosition(e.position);
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

    public void registerSpriteGroups (PlayField playField) {
        playField.addGroup(bounced);
        playField.addGroup(manageBounced);
        for (SpriteGroup group:bouncing) {
            playField.addGroup(group);
        }
    }
    
    @Override
    public void removeElement(GameFieldChangeEvent e) {
        Sprite sprite = table.getSprite(e.element);
        table.deletePair(e.element);
        bounced.remove(sprite);
        manageBounced.remove(sprite);
        for (SpriteGroup group: bouncing) {
            group.remove(sprite);
        }
    }

    @Override
    public void changeElement(GameFieldChangeEvent e) {
        if (e.type == GameFieldChangeEvent.ChangingType.creation) {
            addElement(e);
        } else {
            removeElement(e);
        }
    }
    
    private String getClassName (FieldElement element) {
        String className = element.getClass().toString();
        className = className.substring(className.lastIndexOf('.') + 1);
        return className;
    }
    
    private String getImage(FieldElement element) {
        return images.get(getClassName(element));
    }
    
    public void setCollisionBounds(PlayField playField, CollisionHandler handler) {
        bounderCollision = new CollisionObjectWithBoundary(0, 0, 808, 640);
        bounderCollision.addSpritesCollidedListener(handler);
        playField.addCollisionGroup(bounced, null, bounderCollision);
        bounderCollision = new CollisionObjectWithBoundary(0, 0, 808, 640);
        bounderCollision.addSpritesCollidedListener(handler);
        playField.addCollisionGroup(manageBounced, null, bounderCollision);
        for (SpriteGroup group : bouncing) {
            CollisionObjectWithBoundary collisionBouncing = new CollisionObjectWithBoundary(78, 0, 628, 640);
            bouncingCollisions.add(collisionBouncing);
            collisionBouncing.addSpritesCollidedListener(handler);
            playField.addCollisionGroup(group, null, collisionBouncing);
        }
    }
    
    public void setCollisionObjects(PlayField playField, CollisionHandler handler) {
        for (Map.Entry<String, String> entrySet : collidedGroups.entrySet()) {
            String key = entrySet.getKey();
            String value = entrySet.getValue();
            if (bounced.getSprites().length != 0 && table.getElement(bounced.getSprites()[0])!=null) {
                if (key.compareTo(getClassName(table.getElement(bounced.getSprites()[0]))) == 0) {
                    if (manageBounced.getSprites().length != 0 && table.getElement(manageBounced.getSprites()[0])!=null) {
                        if (getClassName(table.getElement(manageBounced.getSprites()[0])).compareTo(value) == 0) {
                            objectsCollision = new CollisionObjectWithObject();
                            objectsCollision.addSpritesCollidedListener(handler);
                            playField.addCollisionGroup(bounced, manageBounced, objectsCollision);
                        }
                    }
                    for (SpriteGroup bounce:bouncing) {
                        if (bounce.getSprites().length != 0) {
                            if (getClassName(table.getElement(bounce.getSprites()[0])).compareTo(value) == 0) {
                                objectsCollision = new CollisionObjectWithObject();
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
                            objectsCollision.addSpritesCollidedListener(handler);
                            playField.addCollisionGroup(bounced, manageBounced, objectsCollision);
                        }
                    }
                    for (SpriteGroup bounce:bouncing) {
                        if (bounce.getSprites().length != 0) {
                            if (getClassName(table.getElement(bounce.getSprites()[0])).compareTo(value) == 0) {
                                objectsCollision = new CollisionObjectWithObject();
                                objectsCollision.addSpritesCollidedListener(handler);
                                playField.addCollisionGroup(manageBounced, bounce, objectsCollision);
                            }
                        }
                    }
                }
            }
            for (SpriteGroup bounce:bouncing) {
                if (bounce.getSprites().length != 0) {
                    if (getClassName(table.getElement(bounce.getSprites()[0])).compareTo(key) == 0) {
                        if (bounced.getSprites().length != 0 && table.getElement(bounced.getSprites()[0])!=null) {
                            if (getClassName(table.getElement(bounced.getSprites()[0])).compareTo(value) == 0) {
                                objectsCollision = new CollisionObjectWithObject();
                                objectsCollision.addSpritesCollidedListener(handler);
                                playField.addCollisionGroup(bounced, bounce, objectsCollision);
                            }
                        }
                        if (manageBounced.getSprites().length != 0 && table.getElement(manageBounced.getSprites()[0])!=null) {
                            if (getClassName(table.getElement(manageBounced.getSprites()[0])).compareTo(value) == 0) {
                                objectsCollision = new CollisionObjectWithObject();
                                objectsCollision.addSpritesCollidedListener(handler);
                                playField.addCollisionGroup(manageBounced, bounce, objectsCollision);
                            }
                        }
                        for (SpriteGroup bounce1:bouncing) {
                            if (bounce1.getSprites().length != 0) {
                                if (getClassName(table.getElement(bounce1.getSprites()[0])).compareTo(value) == 0 && bounce != bounce1) {
                                    objectsCollision = new CollisionObjectWithObject();
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
    
    public void setAttemptCollisionBounds(PlayField playField, CollisionHandler handler) {
        for (CollisionObjectWithBoundary collision: bouncingCollisions) {
            playField.removeCollisionGroup(collision);
        }
        
        for (SpriteGroup group : bouncing) {
            CollisionObjectWithBoundary collisionBouncing = new CollisionObjectWithBoundary(0, 0, 808, 600);
            bouncingCollisions.add(collisionBouncing);
            collisionBouncing.addSpritesCollidedListener(handler);
            playField.addCollisionGroup(group, null, collisionBouncing);
        }
    }
    
    public void addCollidedGroupPair(String first, String second) {
        collidedGroups.put(first, second);
    }
    
    public void setConnectionWithGhangingGameStateElement(GameStateChangeListener object) {
        FieldElement element;
        for (ViewFieldElement viewElement:viewElements) {
           element = table.getElement(viewElement.getViewSprite());
           if (element instanceof ChangingGameState) {
               ((ChangingGameState)element).addGameStateChangeListener(object);
           }
        }
    }
}
