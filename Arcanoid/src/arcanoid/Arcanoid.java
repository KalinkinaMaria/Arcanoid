/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid;

import arcanoid.events.AttemptStartedEvent;
import arcanoid.events.AttemptStartedListener;
import arcanoid.events.GameStateChangeEvent;
import arcanoid.events.GameStateChangeListener;
import arcanoid.model.CollisionHandler;
import arcanoid.service.Buffer;
import arcanoid.service.SpeedVector;
import arcanoid.view.Ambiance;
import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;
import java.awt.Dimension;
import java.awt.Graphics2D;

/**
 *
 * @author Мария
 */
public class Arcanoid  extends Game implements AttemptStartedListener {
    private PlayField        playfield;
    private Background       background;
    private GameModel gameModel;
    private int oldMousePosition;
    private Ambiance ambiance;
    private CollisionHandler collisionHandler;
    
    //{ distribute = true; }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new Arcanoid(), new Dimension(1000,600), false);
        game.start();
    }

    @Override
    public void initResources() {
        // Создать буффер для связи логики и отображения.
        Buffer buffer = new Buffer();
        playfield = new PlayField();
        ambiance = new Ambiance(buffer);
        gameModel = new GameModel(buffer);
        gameModel.createConnectionWithField(ambiance);
        gameModel.startGame();
        oldMousePosition  = this.getMouseX();
        background = new ImageBackground(getImage("img/1.jpg"), 1000, 600);
        playfield.setBackground(background);
        gameModel.addAttemptStartedListener(this);
        ambiance.registerSpriteGroups(playfield);
        hideCursor();
        collisionHandler = new CollisionHandler(buffer);
        gameModel.registerCollisionRules(ambiance);
        ambiance.setCollisionBounds(playfield, collisionHandler);
        ambiance.setCollisionObjects(playfield, collisionHandler);
        ambiance.setConnectionWithGhangingGameStateElement(gameModel);
    }

    @Override
    public void update(long l) {
        playfield.update(l);
        // Запуск мячика
        if (click() && !gameModel.isGameStarted()) {
            gameModel.startAttempt();
            ambiance.setAttemptCollisionBounds(playfield, collisionHandler);
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

    @Override
    public void startMoving(AttemptStartedEvent e) {
        
    }

    @Override
    public void returnToStartPosition(AttemptStartedEvent e) {
        CollisionManager[] collisionGroups = playfield.getCollisionGroups();
        for (CollisionManager manager: collisionGroups) {
            playfield.removeCollisionGroup(manager);
        }
        ambiance.setCollisionBounds(playfield, collisionHandler);
        ambiance.setCollisionObjects(playfield, collisionHandler);
    }
}
