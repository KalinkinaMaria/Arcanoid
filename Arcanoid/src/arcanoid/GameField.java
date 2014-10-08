/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid;

import arcanoid.buffer.Buffer;
import arcanoid.events.FieldElementCreatedEvent;
import arcanoid.events.FieldElementCreatedListener;
import arcanoid.model.FieldElement;
import java.util.ArrayList;

/**
 * Игровое поле
 * 
 * @author Мария
 */
public class GameField implements FieldElementCreatedListener {
    /** Таблица соответствий элемента поля со спрайтом */
    private Buffer table;
    /** Элементы поля */
    private ArrayList<FieldElement> elements;
    
    /**
     * Конструктор
     */
    public GameField () {
        
    }
    
    /**
     * Добавить элемент
     * @param element элемент
     */
    public void addElement(FieldElement element) {
        
    }
    
    /**
     * Удалить элемент
     * @param element элемент
     */
    public void removeElement(FieldElement element) {
        
    }
    
    /**
     * Создать начальную обстановку
     */
    public void createInitialAmbiance() {
        
    }

    @Override
    public void addElement(FieldElementCreatedEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
