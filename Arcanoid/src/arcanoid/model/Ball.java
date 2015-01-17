/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.model;

import arcanoid.events.GameStateChangeEvent;
import arcanoid.events.GameStateChangeListener;
import arcanoid.service.Buffer;
import com.golden.gamedev.object.Sprite;
import java.util.ArrayList;

/**
 * Мяч
 * 
 * @author Елена
 */
public class Ball extends Bouncing implements Bounced, ChangingGameState {

    /** Слушатели падения мяча*/
    private ArrayList<GameStateChangeListener> failListeners = new ArrayList<> ();
    
    /**
     * Конструктор
     * @param table буффер
     */
    public Ball(Buffer table) {
        super(table);
    }
    
    /**
     * Добавить слушателя события падения мяча
     * 
     * @param listener слушатель
     */
    @Override
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

    /**
     * Обработать изменение состояния игры
     */
    @Override
    public void handleChangingGameState() {
        fireGameStateChange();
    }
}
