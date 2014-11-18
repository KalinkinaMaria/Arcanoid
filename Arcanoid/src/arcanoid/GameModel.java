/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid;

/**
 *
 * @author Елена
 */
public class GameModel implements GameFieldChangeListener {
    /** Игровое поле*/
    private GameField field;
    /** Игрок*/
    private Player player;
    
    /**
     * Закончить игру
     */
    public void endGame() {
        
    }

    @Override
    public void fail(GameStateChangeEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void endGame(GetCritycalWeightEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
