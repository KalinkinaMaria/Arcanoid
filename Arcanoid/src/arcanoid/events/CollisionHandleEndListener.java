/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.events;

/**
 * Cлушатель конца обработки столкновения
 * 
 * @author Елена
 */
public interface CollisionHandleEndListener {
    /**
     * Проверяет выполнение условия теста
     * 
     * @param e 
     */
    public void checkAssertion(CollisionHandleEndEvent e);
}
