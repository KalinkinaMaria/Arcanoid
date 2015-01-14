/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid;

import arcanoid.events.GameStateChangeEvent;
import arcanoid.events.GameStateChangeListener;
import arcanoid.service.SpeedVector;
import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;
import java.awt.Dimension;
import java.awt.Graphics2D;

/**
 *
 * @author Мария
 */
public class Arcanoid  extends Game {
    private PlayField        playfield;
    private Background       background;
    private GameModel gameModel;
    private int oldMousePosition;
    
    //{ distribute = true; }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new Arcanoid(), new Dimension(800,600), false);
        game.start();
    }

    @Override
    public void initResources() {
        playfield = new PlayField();
        oldMousePosition  = this.getMouseX();
        background = new ImageBackground(getImage("img/background.jpg"), 800, 600);
        playfield.setBackground(background);
        gameModel = new GameModel();
        for (SpriteGroup group:gameModel.getSpriteGroups()) {
            playfield.addGroup(group);
        }
        hideCursor();
    }

    @Override
    public void update(long l) {
        playfield.update(l);
        // Запуск мячика
        if (click() && !gameModel.isGameStarted()) {
            gameModel.startAttempt();
        }
        checkMouseMoving(l);
        //System.out.println(l);
    }

    @Override
    public void render(Graphics2D gd) {
        playfield.render(gd);
    }
    
    public void checkMouseMoving(long l) {
        // Игрок сделал движение мышкой.
        if (this.getMouseX() != oldMousePosition && l != 0) {
            gameModel.processPlayerAction(getMouseSpeed(l));
            oldMousePosition = this.getMouseX();
        }
    }
    
    public SpeedVector getMouseSpeed(long l) {
        SpeedVector result;
        result = new SpeedVector(2*(this.getMouseX() - oldMousePosition)/l, 0);
        return result;
    }
}
