/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.events;

import java.util.EventListener;

/**
 * Слушатель падения мяча
 * 
 * @author Мария
 */
public interface BallFallenListener extends EventListener {
    /**
     * Обработать проигрыша
     * 
     * @param e 
     */
    void handleFail(BallFeltEvent e);
    
    /**
     * Обработать событие конца игры
     * 
     * @param e 
     */
    void endGame(BallFeltEvent e);
}
