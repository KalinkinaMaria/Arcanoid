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
import java.awt.Point;
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
    /** Слушатели изменения игрового поля*/
    private ArrayList<GameFieldChangeListener> gameFieldChangeListeners = new ArrayList<>();
    
     /** 
     * Добавить слушателя создания элемента
     * @param listener слушатель
     */
    public void addGameFieldChangeListener (GameFieldChangeListener listener) {
        gameFieldChangeListeners.add(listener);
    }
    
    /**
     * Испустить сигнал, что элемент поля создан
     */
    private void fireGameFieldChange(boolean creation, FieldElement element, Point position) {
        GameFieldChangeEvent event;
        if (creation) {
            event = new GameFieldChangeEvent(element, GameFieldChangeEvent.ChangingType.creation, position);
        } else {
            event = new GameFieldChangeEvent(element, GameFieldChangeEvent.ChangingType.removing, position);
        }
        for (GameFieldChangeListener gameListener: gameFieldChangeListeners) {
            gameListener.changeElement(event);
        }
    }
    
    /**
     * Конструктор
     */
    public GameField (Buffer buffer) {
        elements = new ArrayList<>();
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
    public void addElement(FieldElement element, Point position) {
        elements.add(element);
        fireGameFieldChange(true, element, position);
    }
    
    /**
     * Добавить элемент
     * @param element элемент
     */
    public void addElement(FieldElement element) {
        elements.add(element);
        fireGameFieldChange(true, element, null);
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
        fireGameFieldChange(false, element, null);
        elements.remove(element);
    }
    
    /**
     * Создать начальную обстановку
     */
    public void createInitialAmbiance(GameModel model) {
        
            // Создать ракетку.
            // Создать элемент поля.
            Racket racket = new Racket(table);
            model.addAttemptStartedListener(racket);
            addElement(racket, new Point(310, 575));
            for (int i = 0; i < 5; i++) {
                Ball ball = new Ball(table);
                ball.setWeight(1);
                addElement(ball, new Point(388, 550));
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
    
    public void changePositionForAttempt() {
        
        ArrayList<FieldElement> startingElements = getElements("arcanoid.model.Ball");
        int width = (int)startingElements.get(0).size().width();
        int startPos = startingElements.get(0).position().x + width/2;
        int yPos = startingElements.get(0).position().y;
        
        for (int i = 1; i < startingElements.size(); i++) {
            if (i < 3) {
                startingElements.get(i).setPosition(new Point(startPos - i*20 - width*i, yPos ));
            } else {
                startingElements.get(i).setPosition(new Point(startPos + (i-2)*20 + width*(i-2), yPos ));
            }
        }
    }
}
