/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.events;

import java.util.EventObject;

/**
 * Событие падения мяча за границу поля
 * 
 * @author Мария
 */
public class BallFailedEvent extends EventObject {
    
    /** 
     * Конструктор
     * 
     * @param source родительский объект
     */
    public BallFailedEvent(Object source) {
        super(source);
    }
    
}
