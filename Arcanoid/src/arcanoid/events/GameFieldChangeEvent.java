/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.events;

import arcanoid.model.FieldElement;
import java.util.EventObject;

enum ChangingType {
    creation,
    removing;
}

/**
 * Событие добавления элемента
 * 
 * @author Мария
 */
public class GameFieldChangeEvent extends EventObject {
    /** Созданный элемент*/
    private FieldElement element;
    /** Тип события*/
    private ChangingType type;
    /**
     * Конструктор
     * @param source родительский объект
     * @param element созданный элемент
     */
    public GameFieldChangeEvent(Object source, FieldElement element, ChangingType type) {
        super(source);
        this.element = element;
        this.type = type;
    }
    
}
