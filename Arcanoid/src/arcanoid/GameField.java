/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid;

import arcanoid.events.GameFieldChangeEvent;
import arcanoid.events.GameFieldChangeListener;
import arcanoid.events.GameStateChangeEvent;
import arcanoid.model.FieldElement;
import arcanoid.service.Buffer;
import java.util.ArrayList;

/**
 * Игровое поле
 * 
 * @author Мария
 */
public class GameField implements GameFieldChangeListener {
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
     * Проверить наличие элемента
     * @param element элемент
     * @return флаг - наличие элемента
     */
    public boolean containsElement(FieldElement element) {
        return elements.contains(element);
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
    public void changeElement(GameFieldChangeEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addElement(GameFieldChangeEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeElement(GameFieldChangeEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
