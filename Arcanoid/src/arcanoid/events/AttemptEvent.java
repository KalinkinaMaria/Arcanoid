/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.events;

import arcanoid.model.FieldElement;
import java.util.ArrayList;
import java.util.EventObject;

/**
 * Класс события начала/конца попытки
 * 
 * @author Елена
 */
public class AttemptEvent extends EventObject {
    /** Запускаемые объекты */
    public final ArrayList<FieldElement> pushingObjects;
    
    /**
     * Конструктор
     * 
     * @param source источник
     * @param pushing запускаемые объекты
     */
    public AttemptEvent(Object source, ArrayList<FieldElement> pushing) {
        super(source);
        pushingObjects = pushing;
    }
    
}
