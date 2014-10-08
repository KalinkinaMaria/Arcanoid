/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.events;

import arcanoid.model.FieldElement;
import java.util.EventObject;

/**
 * Событие добавления элемента
 * 
 * @author Мария
 */
public class FieldElementCreatedEvent extends EventObject {
    /** Созданный элемент*/
    private FieldElement element;
    /**
     * Конструктор
     * @param source родительский объект
     * @param element созданный элемент
     */
    public FieldElementCreatedEvent(Object source, FieldElement element) {
        super(source);
    }
    
}
