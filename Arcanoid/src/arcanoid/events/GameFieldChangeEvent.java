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
public class GameFieldChangeEvent extends EventObject {
    public enum ChangingType {
        creation,
        removing;
    }
    
    /** Созданный элемент*/
    private FieldElement element;
    /** Тип события*/
    private ChangingType type;
    /**
     * Конструктор
     * @param source родительский объект
     * @param element созданный элемент
     */
    public GameFieldChangeEvent(Object source, ChangingType type) {
        super(source);
        this.element = (FieldElement)source;
        this.type = type;
    }
    
}
