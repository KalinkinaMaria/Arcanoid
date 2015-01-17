/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.events;

import java.util.EventListener;

/**
 * Класс слушателей начала/конца попытки
 * 
 * @author Елена
 */
public interface AttemptListener extends EventListener {
    /**
     * Начать движение
     * 
     * @param e событие начала попытки
     */
    public void startMoving(AttemptEvent e);
    
    /**
     * Вернуться на начальную точку
     * 
     * @param e событие конца попытки
     */
    public void returnToStartPosition(AttemptEvent e);
    
    /**
     * Закончить игру
     * 
     * @param success результат игры
     */
    public void endGame(boolean success);
}
