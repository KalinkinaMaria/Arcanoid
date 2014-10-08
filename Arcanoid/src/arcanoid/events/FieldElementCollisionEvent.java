/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.events;

import arcanoid.model.FieldElement;
import java.util.EventObject;

/**
 * Событие столкновения элементов
 * @author Мария
 */
public class FieldElementCollisionEvent extends EventObject {
    /** Элемент столкновения */
    private FieldElement element;
    /** Тип столкновения */
    private int collisionType;
    
    /**
     * Конструктор
     * @param source родительский объект
     * @param element элемент столкновения
     * @param collisionType тип столкновения
     */
    public FieldElementCollisionEvent(Object source, FieldElement element, int collisionType) {
        super(source);
    }
    
    /**
     * Получить тип столкновения
     * @return тип столкновения
     */
    public int collisionType() {
        
    }
    
    /**
     * Получить элемент столкновения
     * @return элемент столкновения
     */
    public FieldElement fieldElement() {
        
    }
}
