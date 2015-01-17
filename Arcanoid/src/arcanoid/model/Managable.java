/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.model;

import arcanoid.service.Buffer;

/**
 *
 * @author Елена
 */
public class Managable extends FieldElement{

    public Managable(Buffer table) {
        super(table);
    }

    @Override
    public FieldElement clone() {
        Managable element = new Managable(this.table);
        element.copy(this);
        return element;
    }

}
