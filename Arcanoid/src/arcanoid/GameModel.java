/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid;

import arcanoid.events.GameFieldChangeEvent;
import arcanoid.events.GameStateChangeEvent;
import arcanoid.events.GameStateChangeListener;
import com.golden.gamedev.object.SpriteGroup;
import java.util.ArrayList;

/**
 *
 * @author Елена
 */
public class GameModel implements GameStateChangeListener {
    /** Игровое поле*/
    private GameField field;
    /** Игрок*/
    private Player player;
    
    public GameModel() {
        field = new GameField();
        field.createInitialAmbiance();
    }
    
    /**
     * Закончить игру
     */
    public void endGame() {
        
    }

    public ArrayList<SpriteGroup> getSpriteGroups() {
        return field.getSpriteGroups();
    }
    
    @Override
    public void fail(GameStateChangeEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void endGame(GameStateChangeEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
