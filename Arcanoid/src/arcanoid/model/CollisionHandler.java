/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.model;

import arcanoid.events.SpritesCollidedEvent;
import arcanoid.events.SpritesCollidedListener;
import arcanoid.service.Buffer;
import arcanoid.service.SpeedVector;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;
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

    private SpeedVector.Axis getAxis(int side) {
        SpeedVector.Axis axis;
        if (side == CollisionBounds.LEFT_COLLISION || side == CollisionBounds.RIGHT_COLLISION) {
            axis = SpeedVector.Axis.Y;
        } else {
            axis = SpeedVector.Axis.X;
        }
        return axis;
    }
    
    @Override
    public void spritesCollided(SpritesCollidedEvent e) {
        FieldElement element = getElement(e.activeSprite());
        if (e.passiveSprite() == null) {
            if (element instanceof Bouncing) {
                ((Bouncing)element).handleCollision(getAxis(e.side()), null);
            }
        }
    }
}
