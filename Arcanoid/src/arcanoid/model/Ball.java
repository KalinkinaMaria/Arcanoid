/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.model;

import arcanoid.events.AttemptStartedEvent;
import arcanoid.events.AttemptStartedListener;
import arcanoid.events.GameStateChangeEvent;
import arcanoid.events.GameStateChangeListener;
import arcanoid.service.Buffer;
import arcanoid.service.SpeedVector;
import java.util.ArrayList;

/**
 * Мяч
 * 
 * @author Елена
 */
public class Ball extends Bouncing implements Bounced, ChangingGameState {

    /** Слушатели падения мяча*/
    private ArrayList<GameStateChangeListener> failListeners = new ArrayList<> ();
    
    public Ball(Buffer table) {
        super(table);
    }
    
    /**
     * Добавить слушателя события падения мяча
     * 
     * @param listener слушатель
     */
    public void addGameStateChangeListener(GameStateChangeListener listener) {
        failListeners.add(listener);
    }
    
    /**
     * Испустить событие о том, что мяч упал за нижнюю грпницу
     */
    private void fireGameStateChange() {
        for (GameStateChangeListener listener: failListeners) {
            listener.fail(new GameStateChangeEvent(this,GameStateChangeEvent.GameStateType.unsuccess));
        }
    }
    
    public Ball clone() {
        Ball ball = new Ball(this.table);
        ball.copy(this);
        return ball;
    }

    @Override
    public void handleChangingGameState() {
        fireGameStateChange();
    }
}
