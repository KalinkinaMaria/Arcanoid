/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.collision;

import arcanoid.events.SpritesCollidedEvent;
import arcanoid.events.SpritesCollidedListener;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;
import java.util.ArrayList;
import java.util.Map;

/**
 * Контроллер столкновения объекта с объектом
 * 
 * @author Мария
 */
public class CollisionObjectWithObject extends AdvanceCollisionGroup {
    /** Список слушателей события */
    private ArrayList<SpritesCollidedListener> collisionListener = new ArrayList<>();
    
    /**
     * Конструктор
     */
    public CollisionObjectWithObject() {
        super();
        //pixelPerfectCollision = true;
    }
    
    /**
     * Добавление слушателей события о том, что столкнулись спрайты
     * @param element слушатель
     */
    public void addSpritesCollidedListener(SpritesCollidedListener element) {
        collisionListener.add(element);
    }
    
    /**
     * Испустить событие о том, что столкнулись спрайты
     */
    public void fireSpritesCollided(Sprite sprite, Map storage) {
        for (SpritesCollidedListener listener: collisionListener) {
            listener.spritesCollided(new SpritesCollidedEvent(this, sprite,storage));
        }
    }
    
    /**
     * Обработка столкновений
     * @param sprite спрайт1
     * @param sprite1 спрайт2
     */
    @Override
    public void collided(Sprite sprite, Sprite sprite1) {        
        Map map = getStorage();      
        fireSpritesCollided(getSourceSprite(), map);
    }

}
