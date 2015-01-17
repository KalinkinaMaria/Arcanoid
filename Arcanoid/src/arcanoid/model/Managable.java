/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.model;

import arcanoid.service.Buffer;

/**
 * Управляемый элемент
 * @author Елена
 */
public class Managable extends FieldElement{

    /**
     * Конструктор
     * @param table таблица соответствия спрайтов и элементов
     */
    public Managable(Buffer table) {
        super(table);
    }

    /**
     * Клонирование элемента
     * @return клонированный элемент
     */
    @Override
    public FieldElement clone() {
        Managable element = new Managable(this.table);
        element.copy(this);
        return element;
    }

}
