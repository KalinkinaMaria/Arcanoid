/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.model;

import arcanoid.service.ImpulseOfStrikeForce;

/**
 * Разрушаемый кирпич
 * 
 * @author Елена
 */
public class DestroyableBrick extends Brick implements Bounced {
    
    
    @Override
    public void handleCollision (ImpulseOfStrikeForce force) {
        
    }
}
