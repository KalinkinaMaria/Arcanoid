/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.model;

import arcanoid.service.SpeedVector;
import java.awt.Point;

/**
 * Элемент, который может отскочить
 * 
 * @author Елена
 */
public class Bouncing extends FieldElement{
    
    public Bouncing (Buffer table) {
        super(table);
    }
    
    /**
     * Обработать столкновение
     * 
     * @param position позиция удара
     * @param difference приращение скорости
     */
    protected void handleCollision (Point position, FieldElement element) {
        
    }
    
    /**
     * Обработать столкновение
     * 
     * @param axis ось
     * @param position позиция удара 
     */
    protected void handleCollision (enum Axis axis, Point position) {
        
    }
}
