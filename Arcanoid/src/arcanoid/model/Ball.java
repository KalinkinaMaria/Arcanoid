/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.model;

import arcanoid.service.SpeedVector;
import java.awt.Point;

/**
 * Мяч
 * 
 * @author Елена
 */
public class Ball extends Bouncing implements Bounced {
    
    
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
        
    }
    
    /**
     * Испустить событие о том, что мяч упал за нижнюю грпницу
     */
    private void fireGameStateChange() {
        
    }
    
    
}
