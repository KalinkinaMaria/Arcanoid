/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.model;

import arcanoid.buffer.Buffer;
import arcanoid.service.ImpulseOfStrikeForce;
import arcanoid.service.SpeedVector;

/**
 * Разрушаемый кирпич
 * 
 * @author Елена
 */
public class DestroyableBrick extends Brick implements Bounced {

    public DestroyableBrick(double weight, SpeedVector speed, int subtype, Buffer table) {
        super(weight, speed, subtype, table);
    }
    

}
