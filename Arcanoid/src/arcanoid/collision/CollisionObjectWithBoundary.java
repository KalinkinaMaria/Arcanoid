/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.collision;

import arcanoid.events.SpritesCollidedEvent;
import arcanoid.events.SpritesCollidedListener;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;
import java.util.ArrayList;

/**
 * Контроллер столкновения объекта с границей
 * 
 * @author Мария
 */
public class CollisionObjectWithBoundary extends CollisionBounds {

    /** Список слушателей события */
    private ArrayList<SpritesCollidedListener> collisionListener = new ArrayList<>();
    
    /**
     * Добавление слушателей события о том, что столкнулись спрайты
     * @param element слушатель
     */
    public void addSpritesCollidedListener(SpritesCollidedListener element) {
        collisionListener.add(element);
    }
    
    /**
     * Получить сторону столкновения
     * 
     * @return сторону столкновения
     */
    public int getSide() {
        int side;
        if(isCollisionSide(TOP_COLLISION)) {
            side = TOP_COLLISION;
        } else if(isCollisionSide(BOTTOM_COLLISION)) {
            side = BOTTOM_COLLISION;
        } else if(isCollisionSide(RIGHT_COLLISION)) {
            side = RIGHT_COLLISION;
        } else {
            side = LEFT_COLLISION;
        }
        return side;
    }
    
    /**
     * Испустить событие о том, что столкнулись спрайты
     * 
     * @param sprite спрайт, который столкнулся с границей
     */
    private void fireSpritesCollided(Sprite sprite) {
        for (SpritesCollidedListener listener: collisionListener) {
            listener.spritesCollided(new SpritesCollidedEvent(this, sprite, getSide(), this.getBoundary().x));
        }
    }
    
    /**
     * Конструктор
     * @param x координата x
     * @param y координата y
     * @param width ширина
     * @param height высота
     */
    public CollisionObjectWithBoundary(int x, int y, int width, int height) {
        super(x, y, width, height);
        
    }

    /**
     * Обработка столкновений
     * @param sprite спрайт
     */
    @Override
    public void collided(Sprite sprite) {
        fireSpritesCollided(sprite); 
    }

}
