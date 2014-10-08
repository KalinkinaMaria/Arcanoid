/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.collision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;

/**
 * Контроллер столкновения объекта с границей
 * 
 * @author Мария
 */
public class CollisionObjectWithBoundary extends CollisionBounds{

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
