/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid;

import arcanoid.events.GameFieldChangeEvent;
import arcanoid.events.GameFieldChangeListener;
import arcanoid.events.GameStateChangeEvent;
import arcanoid.model.Ball;
import arcanoid.model.FieldElement;
import arcanoid.model.Racket;
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
    public GameField (Buffer buffer) {
        elements = new ArrayList<>();
        spriteGroups = new HashMap<>();
        table = buffer;
    }
    
    public FieldElement getElement(String className) {
        Class foundClass;
        try {
            foundClass = Class.forName(className);
            for (FieldElement element:elements) {
                if (element.getClass() == foundClass) {
                    return element;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GameField.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<FieldElement> getElements(String className) {
        Class foundClass;
        ArrayList<FieldElement> elements = new ArrayList<>();
        try {
            foundClass = Class.forName(className);
            for (FieldElement element:this.elements) {
                if (element.getClass() == foundClass) {
                    elements.add(element);
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GameField.class.getName()).log(Level.SEVERE, null, ex);
        }
        return elements;
    }
    
    /**
     * Добавить элемент
     * @param element элемент
     */
    public void addElement(FieldElement element) {
        elements.add(element);
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
    public void createInitialAmbiance(GameModel model) {
        try {
            // Создать ракетку.
            // Сщздать спрайт.
            SpriteGroup rackets = new SpriteGroup("Rackets");
            BufferedImage imgRacket = ImageIO.read(new File("img/r.png"));
            Sprite racketSprite = new Sprite(imgRacket, 310, 575);
            rackets.add(racketSprite);
            // Создать элемент поля.
            Racket racket = new Racket(table);
            table.addPair(racket, racketSprite);
            // Создать мячи.
            SpriteGroup balls = new SpriteGroup("Balls");
            BufferedImage imgBall = ImageIO.read(new File("img/ball.png"));
            Sprite ballSprite = new Sprite(imgBall, 381.5, 550);
            Ball ball = new Ball(table);
            table.addPair(ball, ballSprite);
            balls.add(ballSprite);
            model.addAttemptStartedListener(racket);
            // Запомнить группы спрайтов.
            spriteGroups.put("Rackets", rackets);
            spriteGroups.put("Balls", balls);
            addElement(racket);
            addElement(ball);
        } catch (IOException ex) {
            Logger.getLogger(GameField.class.getName()).log(Level.SEVERE, null, ex);
        }
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
