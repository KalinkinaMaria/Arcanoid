/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.model;

import arcanoid.events.BallFallenListener;

/**
 * Элемент, который может изменить состояние игры (проигрыш, например, зависит от него)
 * 
 * @author Елена
 */
public interface ChangingGameState {
    /**
     * Обработать изменение состояния игры
     */
    public void handleChangingGameState();
    /**
     * Добавить слушателя события
     * @param listener слкшатель события
     */
    public void addGameStateChangeListener(BallFallenListener listener);
    
}
