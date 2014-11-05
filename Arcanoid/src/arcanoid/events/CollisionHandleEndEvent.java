/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.events;

/**
 * Событие конца обработки столкновения
 * 
 * @author Елена
 */
public class CollisionHandleEndEvent extends EventObject {
    /** Первый элемент*/
    private FieldElement firstElement;
    /** Второй элемент */
    private FieldElement secondElement;
    /** Третий элемент */
    private FieldElement thirdElement;
    
    /**
     * Конструктор
     * @param source родительский объект
     * @param  firstElement первый элемент
     * @param  secondElement второй элемент
     * @param  thirdElement третий элемент
     */
    public CollisionHandleEndEvent(Object source, FieldElement firstElement, FieldElement secondElement, FieldElement thirdElement) {
        super(source);
        this.firstElement = firstElement;
        this.secondElement = secondElement;
        this.thirdElement = thirdElement;
    }
    
}
