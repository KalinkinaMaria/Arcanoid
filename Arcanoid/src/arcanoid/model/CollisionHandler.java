/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.model;

import arcanoid.events.SpritesCollidedEvent;
import arcanoid.events.SpritesCollidedListener;
import arcanoid.service.Buffer;
import com.golden.gamedev.object.Sprite;
import java.util.Map;

/**
 * Обработчик столкновений
 * 
 * @author Елена
 */
public class CollisionHandler implements SpritesCollidedListener {
    /** Таблица соответствий элемента поля со спрайтом */
    private Buffer table;
    
    public CollisionHandler (Buffer table) {
        this.table = table;
    }
    
    /**
     * Определить тип столкновения
     * 
     * @param storage хранилище со столкнувшимися спрайтами
     */
    public void getCollisionType(Map storage) {
        
    }
    
    /**
     * Получить элемент
     * 
     * @param sprite спрайт, который соответствует элементу
     */
    private FieldElement getElement (Sprite sprite) {
        return table.getElement(sprite);
    }

    @Override
    public void spritesCollided(SpritesCollidedEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
