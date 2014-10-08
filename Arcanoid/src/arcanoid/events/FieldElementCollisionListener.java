/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.events;

import java.util.EventListener;

/**
 * Слушатель столкновения элементов
 * 
 * @author Мария
 */
public interface FieldElementCollisionListener extends EventListener{
    /**
     * Отрисовать столкновение
     * @param e событие
     */
    void paintCollision(FieldElementCollisionEvent e);
}
