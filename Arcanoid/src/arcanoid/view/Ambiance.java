/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.view;

import arcanoid.collision.CollisionObjectWithBoundary;
import arcanoid.events.GameFieldChangeEvent;
import arcanoid.events.GameFieldChangeListener;
import arcanoid.model.Bounced;
import arcanoid.model.Bouncing;
import arcanoid.model.CollisionHandler;
import arcanoid.model.FieldElement;
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
    /** Препятствия */
    private SpriteGroup obstacles;
    /** Таблица сталкивающихся элементов */
    private Map<Class,Class> collidedGroups;
    private Buffer table;
    private HashMap<String, String> images;
    private ArrayList<ViewFieldElement> viewElements;
    private CollisionObjectWithBoundary bounderCollision;

    public  Ambiance(Buffer buffer) {
        table = buffer;
        viewElements = new ArrayList<>();
        images = new HashMap<>();
        bouncing = new ArrayList<>();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        bounderCollision = new CollisionObjectWithBoundary(0, 0, 600, 800);
        bounderCollision.addSpritesCollidedListener(handler);
        playField.addCollisionGroup(bounced, bounced, bounderCollision);
        for (SpriteGroup group : bouncing) {
            playField.addCollisionGroup(group, group, bounderCollision);
        }
    }
    
}
