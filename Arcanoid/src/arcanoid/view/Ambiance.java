/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.view;

import arcanoid.events.GameFieldChangeEvent;
import arcanoid.events.GameFieldChangeListener;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.SpriteGroup;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Мария
 */
public class Ambiance implements GameFieldChangeListener {
    /** Задний фон */
    private Background background;
    /** Отскакивающие элементы */
    private ArrayList<SpriteGroup> bouncing;
    /** Препятствия */
    private SpriteGroup obstacles;
    /** Таблица сталкивающихся элементов */
    private Map<Class,Class> collidedGroups;

    @Override
    public void addElement(GameFieldChangeEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeElement(GameFieldChangeEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void changeElement(GameFieldChangeEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
