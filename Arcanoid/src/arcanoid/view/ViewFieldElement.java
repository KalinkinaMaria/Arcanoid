/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.view;

import arcanoid.events.CollisionHandleEndListener;
import com.golden.gamedev.object.Sprite;

/**
 * Изображение элемента поля
 * 
 * @author Мария
 */
public abstract class ViewFieldElement implements CollisionHandleEndListener {
    /** Изображение */
    private Sprite view;
    /** Изображение при столкновении*/
    private Sprite viewInCollision;
    
    /**
     * Конструктор
     * @param view изображение мяча
     * @param viewInCollision изображение мяча при столкновении
     */
    public ViewFieldElement(Sprite view, Sprite viewInCollision) {
        this.view = view;
        this.viewInCollision = viewInCollision;
    }
    
    /**
     * Показать коллизию
     */
    public abstract void showCollision() ;
    
    /**
     * Показать изображение
     */
    public Sprite getViewSprite() {
        return view;
    }
}
