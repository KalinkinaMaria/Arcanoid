/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.view;

import arcanoid.events.CollisionHandleEndEvent;
import com.golden.gamedev.object.Sprite;

/**
 * Изображение мяча
 * 
 * @author Мария
 */
public class ViewBall extends ViewFieldElement {

    /**
     * Конструктор
     * @param view изображение кирпича
     * @param viewInCollision изображение кирпича при столкновении
     */
    public ViewBall(Sprite view, Sprite viewInCollision) {
        super(view, viewInCollision);
    }    
    
    /**
     * Показать коллизию
     */
    @Override
    public void showCollision() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }  

    /**
     * Проверяет выполнение условия теста
     * 
     * @param e 
     */
    @Override
    public void checkAssertion(CollisionHandleEndEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
