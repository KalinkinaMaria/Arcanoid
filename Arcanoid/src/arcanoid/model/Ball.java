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
public class Ball extends FieldElement implements Bouncing, Bounced{

    @Override
    public void handleCollision (ImpulseOfStrikeForce force) {
        
    }
    
    @Override
    public void handleCollision(Point position, SpeedVector difference) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void handleCollision(enum Axis axis, Point position) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Клонироваться
     * 
     * @return склонированный мяч
     */
    private Ball clone() {
        
    }
}
