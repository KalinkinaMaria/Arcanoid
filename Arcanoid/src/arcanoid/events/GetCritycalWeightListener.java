/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.events;

import java.util.EventListener;

/**
 * Слушатель события конца игры
 * 
 * @author Мария
 */
public interface GetCritycalWeightListener extends EventListener {
    /**
     * Обработать событие конца игры
     * 
     * @param e 
     */
    void endGame(GetCritycalWeightEvent e);
    
}
