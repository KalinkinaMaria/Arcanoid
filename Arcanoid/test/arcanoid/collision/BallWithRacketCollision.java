/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.collision;

import arcanoid.events.CollisionHandleEndEvent;
import arcanoid.events.CollisionHandleEndListener;
import arcanoid.service.SpeedVector;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Елена
 */
public class BallWithRacketCollision extends Game implements CollisionHandleEndListener {
    private PlayField  playfield;
    private SpriteGroup ballGroup;
    private SpriteGroup racketGroup;
    private CollisionObjectWithObject collision;
    
    @Override
    public void initResources(Buffer table) {
        playfield = new PlayField();
        // Создание спрайт групп
        ballGroup = new SpriteGroup("Balls");
        racketGroup = new SpriteGroup("Rackets");
        
        playfield.addGroup(ballGroup);
        playfield.addGroup(racketGroup);
        // Создание спрайтов
        BufferedImage ballImage = getImage("img/ball.png");
        BufferedImage racketImage = getImage("img/r.png");
        Sprite ball = new Sprite(ballImage, 280, 480);
        Sprite racket = new Sprite(racketImage, 220, 525);
        //Заполняем буфер
        Ball ballElement = new Ball(table);
        Racket racketElement = new Racket(table);
        table.addPair(ballElement, ball);
        table.addPair(racketElement, racket);
        ballElement.addCollisionHandleEndListener(this);
        ball.setSpeed(1, -1);
        // Добавление в спрайт группу и установка коллизии
        ballGroup.add(ball);
        racketGroup.add(racket);
        collision = new CollisionObjectWithObject();
        playfield.addCollisionGroup(ballGroup, racketGroup, collision);
    }

    @Override
    public void update(long elapsedTime) {
        playfield.update(elapsedTime);
    }

    @Override
    public void render(Graphics2D gd) {
        playfield.render(gd);
    }

    @Override
    public void checkAssertion(CollisionHandleEndEvent e) {
        assertEquals(e.firstElement.speed(), new SpeedVector(1,1));
        assertEquals(e.secondElement.speed(), new SpeedVector());
    }
}
