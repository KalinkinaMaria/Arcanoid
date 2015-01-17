/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.model;

import arcanoid.service.Buffer;

/**
 * Кирпич
 * 
 * @author Елена
 */
public abstract class Brick extends FieldElement implements Bounced {
    /**
     * Конструктор 
     * @param table буффер
     */
    public Brick(Buffer table) {
        super(table);
    }
}
