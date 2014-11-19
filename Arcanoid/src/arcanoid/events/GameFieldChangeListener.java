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
public interface GameFieldChangeListener {
    /**
     * Обработать изменение элементов в игровом поле
     * @param e событие 
     */
    public void changeElement(GameFieldChangeEvent e);
    /**
     * Добавить элемент при его создании
     * @param e событие
     */
    private void addElement(GameFieldChangeEvent e);
    /**
     * Удалить элемент при его создании
     * @param e событие
     */
    private void removeElement(GameFieldChangeEvent e);
}
