/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.events;

/**
 * Слуштель события создания элемента
 * 
 * @author Мария
 */
public interface FieldElementCreatedListener {
    /**
     * Добавить элемент при его создании
     * @param e событие
     */
    public void addElement(FieldElementCreatedEvent e);
}
