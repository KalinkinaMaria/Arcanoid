/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.service;

import arcanoid.model.FieldElement;
import com.golden.gamedev.object.Sprite;
import java.util.Collection;
import java.util.HashMap;

/**
 * Буфер
 * Хранит соответствие элемента и спрайта
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
     * @param sprite спрайт
     * @return элемент поля
     */
    public FieldElement getElement(Sprite sprite) {
        Collection<FieldElement> keys;
        Sprite currentSprite;
        // Поиск соответствующего спрайту элемента
        keys = table.keySet();
        for (FieldElement key : keys) {
            currentSprite = table.get(key);
            if (currentSprite.equals(sprite)) {
                return key;
            }
        }
        return null;
    }
    
    /**
     * Получить спрайт
     * @param element элемент
     * @return спрайт
     */
    public Sprite getSprite(FieldElement element) {
        return table.get(element);
    }
    
    /**
     * Добавить пару
     * @param element элемент
     * @param sprite спрайт
     */
    public void addPair(FieldElement element, Sprite sprite) {
        if (element != null && sprite != null) {
            table.put(element, sprite);
        }
    }
    
    /**
     * Удалить пару
     * @param element элемент
     */
    public void deletePair(FieldElement element) {
        table.remove(element);
    }
}
