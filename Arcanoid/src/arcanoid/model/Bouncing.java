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
public interface Bouncing {
    /**
     * Обработать столкновение
     * 
     * @param position позиция удара
     * @param difference приращение скорости
     */
    public void handleCollision (Point position, SpeedVector difference);
    
    /**
     * Обработать столкновение
     * 
     * @param axis ось
     * @param position позиция удара 
     */
    public void handleCollision (enum Axis axis, Point position);
}
