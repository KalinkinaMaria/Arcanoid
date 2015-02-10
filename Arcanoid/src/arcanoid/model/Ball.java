/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.model;

import arcanoid.events.BallFeltEvent;
import arcanoid.events.BallFallenListener;
import arcanoid.service.Buffer;
import com.golden.gamedev.object.Sprite;
import java.util.ArrayList;

/**
 * Мяч
 * 
 * @author Елена
 */
public class Ball extends Bouncing implements Bounced {

    /** Слушатели падения мяча*/
    private ArrayList<BallFallenListener> fallenListeners = new ArrayList<> ();
    
    /**
     * Конструктор
     * @param table буффер
     */
    public Ball(Buffer table) {
        super(table);
    }
    
    /**
     * Испустить событие о том, что мяч упал за нижнюю грпницу
     */
    private void fireBallFell() {
        for (BallFallenListener listener: fallenListeners) {
            listener.handleFail(new BallFeltEvent(this,BallFeltEvent.GameStateType.unsuccess));
        }
    }
    
    /**
     * Клонировать
     * @return клон мяча
     */
    public Ball clone() {
        Ball ball = new Ball(this.table);
        Sprite sprite = table.getSprite(this);
        sprite = new Sprite(sprite.getImage(), sprite.getX(), sprite.getY());
        table.addPair(ball, sprite);
        ball.copy(this);
        return ball;
    }
    
    private class ChangingGameObserver implements ChangingGameState {
        /**
        * Обработать изменение состояния игры
        */
       @Override
       public void handleChangingGameState() {
           fireBallFell();
       }
    
        /**
         * Добавить слушателя события падения мяча
         * 
         * @param listener слушатель
         */
        @Override
        public void addGameStateChangeListener(BallFallenListener listener) {
            fallenListeners.add(listener);
        }
    }
}
