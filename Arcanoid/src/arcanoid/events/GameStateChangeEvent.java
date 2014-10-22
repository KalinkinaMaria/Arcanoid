/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.events;

import java.util.EventObject;

enum GameStateType {
    success,
    unsuccess;
}

/**
 * Событие падения мяча за границу поля
 * 
 * @author Мария
 */
public class GameStateChangeEvent extends EventObject {
    
    private GameStateType type;
    /** 
     * Конструктор
     * 
     * @param source родительский объект
     */
    public GameStateChangeEvent(Object source,GameStateType type) {
        super(source);
        this.type = type;
    }
    
}
