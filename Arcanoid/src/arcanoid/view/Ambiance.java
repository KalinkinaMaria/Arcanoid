/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.view;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.SpriteGroup;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Мария
 */
public class Ambiance extends Game {
    /** Задний фон */
    private Background background;
    /** Отскакивающие элементы */
    private ArrayList<SpriteGroup> bouncing;
    /** Препятствия */
    private SpriteGroup obstacles;
    
    /**
     * Инициализация ресурсов
     */
    @Override
    public void initResources() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Обновить
     * @param l 
     */
    @Override
    public void update(long l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Перерисовать
     * @param gd 
     */
    @Override
    public void render(Graphics2D gd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
