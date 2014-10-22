/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.events;

import java.util.EventObject;

/**
 * Событие окончания игры
 * 
 * @author Мария
 */
public class GetCritycalWeightEvent extends EventObject {

    /**
     * Конструктор
     * 
     * @param source 
     */
    public GetCritycalWeightEvent(Object source) {
        super(source);
    }
    
}
