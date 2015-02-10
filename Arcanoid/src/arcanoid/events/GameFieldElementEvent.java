/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.events;

import arcanoid.model.FieldElement;
import java.awt.Point;
import java.util.EventObject;

/**
 * Событие добавления элемента
 * 
 * @author Мария
 */
public class GameFieldElementEvent extends EventObject {
    public enum ChangingType {
        creation,
        removing;
    }
    
    /** Созданный элемент*/
    public FieldElement element;
    /** Позиция элемента, изменившего поле */
    public Point position;
    /** Тип события*/
    public ChangingType type;
    /**
     * Конструктор
     * @param source родительский объект
     * @param type тип события
     * @param position позиция элемента, изменившего поле
     */
    public GameFieldElementEvent(Object source, ChangingType type, Point position) {
        super(source);
        this.element = (FieldElement)source;
        this.type = type;
        this.position = position;
    }
    
}
