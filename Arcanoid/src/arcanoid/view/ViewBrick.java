/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.view;

import arcanoid.events.FieldElementCollisionEvent;
import com.golden.gamedev.object.Sprite;

/**
 * Изображение кирпича
 * 
 * @author Мария
 */
public class ViewBrick  extends ViewFieldElement{

    /**
     * Конструктор
     * @param view изображение кирпича
     * @param viewInCollision изображение кирпича при столкновении
     */
    public ViewBrick(Sprite view, Sprite viewInCollision) {
        
    }
    
    /**
     * Показать коллизию
     */
    @Override
    public void showCollision() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void paintCollision(FieldElementCollisionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
