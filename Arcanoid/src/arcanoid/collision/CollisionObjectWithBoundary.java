/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.collision;

import arcanoid.events.SpritesCollidedListener;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;
import java.util.ArrayList;

/**
 * Контроллер столкновения объекта с границей
 * 
 * @author Мария
 */
public class CollisionObjectWithBoundary extends CollisionBounds{

    /** Список слушателей события */
    private ArrayList<SpritesCollidedListener> collisionListener = new ArrayList<>();
    
    /**
     * Добавление слушателей события о том, что столкнулись спрайты
     * @param element слушатель
     */
    public void addSpritesCollidedListener(SpritesCollidedListener element) {
        
    }
    
    /**
     * Испустить событие о том, что столкнулись спрайты
     */
    public void fireSpritesCollided() {
        
    }
    
    /**
     * Конструктор
     * @param i
     * @param i1
     * @param i2
     * @param i3 
     */
    public CollisionObjectWithBoundary(int i, int i1, int i2, int i3) {
        super(i, i1, i2, i3);
    }

    /**
     * Обработка столкновений
     * @param sprite спрайт
     */
    @Override
    public void collided(Sprite sprite) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
