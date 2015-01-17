/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.model;
import arcanoid.service.Buffer;

/**
 * Элемент роя
 * 
 * @author Елена
 */
public class SwarmElement extends FieldElement {

    /**
     * Конструктор
     * @param table таблица соответствия спрайтов и элементов 
     * @param weight вес роя
     */
    public SwarmElement(Buffer table, double weight) {
        super(table);
        this.weight = weight;
    }

    /**
     * Клонирование элемента
     * @return клонированный элемент
     */
    public SwarmElement clone() {
        SwarmElement element = new SwarmElement(this.table, this.weight);
        element.copy(this);
        return element;
    }
}
