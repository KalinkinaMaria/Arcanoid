/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.model;

import java.util.ArrayList;

/**
 * Обработчик столкновения отскакивающего элемента с тем, от которого отскочить нельзя
 * 
 * @author Елена
 */
public class CollisionHandlerBouncingWithUnbounced {
    /** Упругий элемент*/
    private Bouncing bouncy;
    /** Элементы, от которых упругий не отскочил*/
    private ArrayList unbounced;
    
    /**
     * Обработать столкновение
     */
    public void handleCollision() {
        
    }
}
