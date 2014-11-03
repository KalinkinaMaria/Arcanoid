/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.service;

import arcanoid.model.FieldElement;
import com.golden.gamedev.object.Sprite;
import java.util.HashMap;

/**
 * Буфер
 * 
 * @author Мария
 */
public class Buffer {
    /** Таблица соответствий элемента поля и спрайта */
    private HashMap<FieldElement, Sprite> table;
    
    /**
     * Конструктор
     */
    public Buffer () {
        table = new HashMap<FieldElement, Sprite>();
    }
    
    /**
     * Получить элемент поля
     * @param sprate спрайт
     * @return элемент поля
     */
    public FieldElement getElement(Sprite sprate) {
        
    }
    
    /**
     * Получить спрайт
     * @param element элемент
     * @return спрайт
     */
    public Sprite getSprite(FieldElement element) {
        
    }
    
    /**
     * Добавить пару
     * @param element элемент
     * @param sprite спрайт
     */
    public void addPair(FieldElement element, Sprite sprite) {
        
    }
    
    /**
     * Удалить пару
     * @param element элемент
     */
    public void deletePair(FieldElement element) {
        
    }
}
