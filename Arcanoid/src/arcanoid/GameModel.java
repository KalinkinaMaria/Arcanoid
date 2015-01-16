/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid;

import arcanoid.events.AttemptStartedEvent;
import arcanoid.events.AttemptStartedListener;
import arcanoid.events.GameFieldChangeEvent;
import arcanoid.events.GameFieldChangeListener;
import arcanoid.events.GameStateChangeEvent;
import arcanoid.events.GameStateChangeListener;
import arcanoid.model.FieldElement;
import arcanoid.model.Racket;
import arcanoid.service.Buffer;
import arcanoid.service.SpeedVector;
import arcanoid.view.Ambiance;
import com.golden.gamedev.object.SpriteGroup;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
    
    public void startGame() {
        field.createInitialAmbiance(this);
    }
    public GameModel(Buffer buffer) {
        field = new GameField(buffer);
        movingElements = new ArrayList<>();
        gameWasStarted = false;
        player = new Player(3);
        
    }
    
    public void createConnectionWithField(GameFieldChangeListener object) {
        field.addGameFieldChangeListener(object);
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
    
    private void fireAttemptEnded(FieldElement element) {
        AttemptStartedEvent event;
        ArrayList<FieldElement> list = new ArrayList<>();
        element.setSpeed(new SpeedVector());
        list.add(element);
        event = new AttemptStartedEvent(this, list);

        for (AttemptStartedListener listener: movingElements) {
            listener.returnToStartPosition(event);
        }
    }
    
    /**
     * Закончить игру
     */
    public void endGame(boolean success) {
        String message;
        if (success) {
            message = "Вы выиграли";
        } else {
            message = "Вы проиграли";
        }
        Frame frame = new Frame("Конец");
        //frame.setVisible(true);
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
    
    @Override
    public void fail(GameStateChangeEvent e) {
        int lives = player.lives() - 1;
        if (lives != 0) {
            player.setLives(lives);
            // Вернуть шарик на место.
            fireAttemptEnded((FieldElement)e.element);
            gameWasStarted = false;
        } else {
            endGame(false);
        }
    }
    
    public HashMap<String, String> getGameData() {
        HashMap<String, String> result = new HashMap <String, String>();
        result.put("Lives", String.valueOf(player.lives()));
        result.put("Score", String.valueOf(player.score()));
        return result;
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
    
    public void registerCollisionRules(Ambiance ambiance) {
        ambiance.addCollidedGroupPair("Racket", "Ball");
        ambiance.addCollidedGroupPair("Ball", "Ball");
    }
}
