/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import com.golden.gamedev.object.Sprite;

/**
 * Изображение ракетки
 * 
 * @author Мария
 */
public class ViewRacket extends ViewFieldElement{

    /**
     * Конструктор
     * @param view изображение ракетки
     * @param viewInCollision изображение ракетки при столкновении
     */
    public ViewRacket(Sprite view, Sprite viewInCollision) {
        
    }
    
    /**
     * Показать коллизию
     */
    @Override
    public void showCollision() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
