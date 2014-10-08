/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid;

import arcanoid.events.BallFailedEvent;
import arcanoid.events.BallFailedListener;
import arcanoid.events.GameEndedEvent;
import arcanoid.events.GameEndedListener;

/**
 *
 * @author Мария
 */
public class Arcanoid implements BallFailedListener, GameEndedListener {
    /** Игровое поле*/
    private GameField field;
    /** Игрок*/
    private Player player;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    /**
     * Закончить игру
     */
    public void endGame() {
        
    }

    @Override
    public void fail(BallFailedEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void endGame(GameEndedEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
