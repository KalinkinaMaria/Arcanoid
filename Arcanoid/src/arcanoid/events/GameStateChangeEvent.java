/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.events;

import arcanoid.model.ChangingGameState;
import java.util.EventObject;



/**
 * Событие падения мяча за границу поля
 * 
 * @author Мария
 */
public class GameStateChangeEvent extends EventObject {
   
    public enum GameStateType {
        success,
        unsuccess;
    }
    
    /** Элемент, изменяющий игру*/
    public ChangingGameState element;
    
    /** Тип события*/
    private GameStateType type;
    
    /** 
     * Конструктор
     * 
     * @param source родительский объект
     */
    public GameStateChangeEvent(Object source,GameStateType type) {
        super(source);
        this.type = type;
        element = (ChangingGameState)source;
    }
    
}
