/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.view;

import com.golden.gamedev.object.Sprite;

/**
 * Изображение роя
 * 
 * @author Мария
 */
public class ViewSwarm  extends ViewFieldElement{

    /**
     * Конструктор
     * @param view изображение роя
     * @param viewInCollision изображение роя при столкновении
     */
    public ViewSwarm(Sprite view, Sprite viewInCollision) {
        
    }
    
    /**
     * Показать коллизию
     */
    @Override
    public void showCollision() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
