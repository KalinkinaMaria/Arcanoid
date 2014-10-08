/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.collision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

/**
 * Контроллер столкновения объекта с объектом
 * 
 * @author Мария
 */
public class CollisionObjectWithObject extends AdvanceCollisionGroup{

    /**
     * Обработка столкновений
     * @param sprite спрайт1
     * @param sprite1 спрайт2
     */
    @Override
    public void collided(Sprite sprite, Sprite sprite1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
