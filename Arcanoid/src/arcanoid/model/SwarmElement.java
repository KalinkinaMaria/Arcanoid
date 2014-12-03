/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.model;

import arcanoid.service.Buffer;
import arcanoid.service.ImpulseOfStrikeForce;
import arcanoid.service.SpeedVector;

/**
 * Элемент роя
 * 
 * @author Елена
 */
public class SwarmElement extends FieldElement {

    public SwarmElement(Buffer table, double weight) {
        super(table);
        this.weight = weight;
    }

    public SwarmElement clone() {
        SwarmElement element = new SwarmElement(this.table, this.weight);
        element.copy(this);
        return element;
    }
}
