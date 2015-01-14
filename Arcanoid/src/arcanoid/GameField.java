/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid;

import arcanoid.events.GameFieldChangeEvent;
import arcanoid.events.GameFieldChangeListener;
import arcanoid.events.GameStateChangeEvent;
import arcanoid.model.FieldElement;
import arcanoid.service.Buffer;
import com.golden.gamedev.object.Sprite;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.SpriteGroup;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
/**
 * Игровое поле
 * 
 * @author Мария
 */
public class GameField implements GameFieldChangeListener {
    /** Таблица соответствий элемента поля со спрайтом */
    private Buffer table;
    /** Элементы поля */
    private ArrayList<FieldElement> elements;
    private HashMap<String, SpriteGroup> spriteGroups;
    
    /**
     * Конструктор
     */
    public GameField () {
        elements = new ArrayList<>();
        spriteGroups = new HashMap<>();
    }
    
    /**
     * Добавить элемент
     * @param element элемент
     */
    public void addElement(FieldElement element) {
        
    }
    
    /**
     * Проверить наличие элемента
     * @param element элемент
     * @return флаг - наличие элемента
     */
    public boolean containsElement(FieldElement element) {
        return elements.contains(element);
    }
    
    /**
     * Удалить элемент
     * @param element элемент
     */
    public void removeElement(FieldElement element) {
        
    }
    
    public Collection<SpriteGroup> getSpriteGroups() {
        return spriteGroups.values();
    }
    
    /**
     * Создать начальную обстановку
     */
    public void createInitialAmbiance() {
        try {
            SpriteGroup rackets = new SpriteGroup("Rackets");
            SpriteGroup balls = new SpriteGroup("Balls");
            BufferedImage imgRacket = ImageIO.read(new File("img/r.png"));
            Sprite racket = new Sprite(imgRacket, 310, 575);
            BufferedImage imgBall = ImageIO.read(new File("img/ball.png"));
            Sprite ball = new Sprite(imgBall, 381.5, 550);
            rackets.add(racket);
            balls.add(ball);
            spriteGroups.put("Rackets", rackets);
            spriteGroups.put("Balls", balls);
        } catch (IOException ex) {
            Logger.getLogger(GameField.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void startMoving() {
        Sprite[] balls = spriteGroups.get("Balls").getSprites();
        
    }

    @Override
    public void changeElement(GameFieldChangeEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addElement(GameFieldChangeEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeElement(GameFieldChangeEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
