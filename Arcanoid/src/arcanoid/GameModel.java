/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid;

import arcanoid.events.AttemptStartedEvent;
import arcanoid.events.AttemptStartedListener;
import arcanoid.events.GameFieldChangeEvent;
import arcanoid.events.GameStateChangeEvent;
import arcanoid.events.GameStateChangeListener;
import arcanoid.model.FieldElement;
import arcanoid.model.Racket;
import arcanoid.service.SpeedVector;
import com.golden.gamedev.object.SpriteGroup;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Елена
 */
public class GameModel implements GameStateChangeListener {
    /** Игровое поле*/
    private GameField field;
    /** Игрок*/
    private Player player;
    /** Флаг, о том, что игра началась, т.е. игрок запустил шарик*/
    private boolean gameWasStarted;
    private ArrayList<AttemptStartedListener> movingElements;
    
    public GameModel() {
        field = new GameField();
        movingElements = new ArrayList<>();
        gameWasStarted = false;
        field.createInitialAmbiance(this);
    }
    /** 
     * Добавить слушателя начала попытки
     * @param listener слушатель
     */
    public void addAttemptStartedListener (AttemptStartedListener listener) {
        movingElements.add(listener);
    }
    
    /**
     * Испустить сигнал, что попытка начата
     */
    private void fireAttemptStarted() {
        AttemptStartedEvent event;
        event = new AttemptStartedEvent(this, field.getElements("arcanoid.model.Ball"));

        for (AttemptStartedListener listener: movingElements) {
            listener.startMoving(event);
        }
    }
    /**
     * Закончить игру
     */
    public void endGame() {
        
    }

    public void gameWasStarted() {
        gameWasStarted = true;
    }
    
    public void gameWasEnded() {
        gameWasStarted = false;
    }
    
    public boolean isGameStarted() {
        return gameWasStarted;
    }
    
    public void startAttempt() {
        fireAttemptStarted();
        gameWasStarted = true;
    }
    public Collection<SpriteGroup> getSpriteGroups() {
        return field.getSpriteGroups();
    }
    
    @Override
    public void fail(GameStateChangeEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void endGame(GameStateChangeEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void processPlayerAction(SpeedVector speed) {
        FieldElement racket = field.getElement("arcanoid.model.Racket");
        if (racket != null) {
            racket.setSpeed(speed);
        }
        if (!isGameStarted()) {
            for (FieldElement element: field.getElements("arcanoid.model.Ball")) {
                element.setSpeed(speed);
            }
        }
    }
}
