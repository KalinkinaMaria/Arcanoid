/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.events;

import arcanoid.model.FieldElement;
import java.util.EventObject;

/**
 * Событие удаления элемента
 * 
 * @author Мария
 */
public class FieldElementRemovedEvent extends EventObject {

    /** Созданный элемент*/
    private FieldElement element;
    /**
     * Конструктор
     * @param source родительский объект
     * @param element созданный элемент
     */
    public FieldElementRemovedEvent(Object source, FieldElement element) {
        super(source);
        this.element = element;
    }
    
}
