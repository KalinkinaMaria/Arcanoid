/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.model;

import arcanoid.service.Buffer;

/**
 * Разрушаемый кирпич
 * 
 * @author Елена
 */
public class DestroyableBrick extends Brick implements Bounced {

    /** Прочность*/
    protected int strength;
    
    public DestroyableBrick(Buffer table) {
        super(table);
    }
    
    /**
     * Задать прочность кирпича
     * @param strength прочность
     */
    public void setStrength(int strength) {
        
    }
    
    /**
     * Получить прочность
     * @return прочность
     */
    public int strength() {
        return strength;
    }
    
    public DestroyableBrick clone() {
        DestroyableBrick brick = new DestroyableBrick(this.table);
        brick.copy(this);
        return brick;
    }
}
