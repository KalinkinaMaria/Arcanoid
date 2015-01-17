/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.collision;

import arcanoid.events.CollisionHandleEndEvent;
import arcanoid.events.CollisionHandleEndListener;
import arcanoid.model.Ball;
import arcanoid.model.CollisionHandler;
import arcanoid.model.FieldElement;
import arcanoid.service.Buffer;
import arcanoid.service.SpeedVector;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Елена
 */
public class BallsCollision extends Game implements CollisionHandleEndListener {
    /** Игровое поле */
    private PlayField  playfield;
    /** Группа шарика */
    private SpriteGroup firstBallGroup;
    /** Группа шарика второго */
    private SpriteGroup secondBallGroup;
    /** Группа шарика третьего */
    private SpriteGroup thirdBallGroup;
    /** Менеджер коллизии */
    private CollisionObjectWithObject collision;
    /** Буфер */
    private Buffer table;
    double firstWeigt;
    double secondWeigt;
    double thirdWeigt;
    SpeedVector firstVector;
    SpeedVector secondVector;
    SpeedVector thirdVector;
    Point firstPoint;
    Point secondPoint;
    Point thirdPoint;
    Ball clone1;
    Ball clone2;
    Ball clone3;
    
    /**
     * Конструктор
     * @param table Буфер
     */
    public BallsCollision(Buffer table) {
        this.table = table;
    }
    
    /**
     * Инициализация ресурсов
     */
    @Override
    public void initResources() {
        playfield = new PlayField();
        // Создание спрайт групп
        firstBallGroup = new SpriteGroup("firstBalls");
        secondBallGroup = new SpriteGroup("secondBalls");
        thirdBallGroup = new SpriteGroup("thirdBalls");
        
        playfield.addGroup(firstBallGroup);
        playfield.addGroup(secondBallGroup);
        playfield.addGroup(thirdBallGroup);
        // Создание спрайтов
        BufferedImage ballImage = getImage("img/ball.png");
        Sprite ball1 = new Sprite(ballImage, firstPoint.x, firstPoint.y);
        Sprite ball2 = new Sprite(ballImage, secondPoint.x, secondPoint.y);
        Sprite ball3 = null;
        if (thirdPoint != null) {
            ball3 = new Sprite(ballImage, thirdPoint.x, thirdPoint.y);
        }
        //Заполняем буфер
        Ball ballElement1 = new Ball(table);
        ballElement1.setWeight(firstWeigt);
        Ball ballElement2 = new Ball(table);
        ballElement2.setWeight(secondWeigt);
        Ball ballElement3 = new Ball(table);
        ballElement3.setWeight(thirdWeigt);
        table.addPair(ballElement1, ball1);
        table.addPair(ballElement2, ball2);
        if (thirdPoint != null) {
            table.addPair(ballElement3, ball3);
        }
        ballElement1.addCollisionHandleEndListener(this);
        ball1.setSpeed(firstVector.x(), firstVector.y());
        ball2.setSpeed(secondVector.x(), secondVector.y());
        if (thirdPoint != null) {
            ball3.setSpeed(thirdVector.x(), thirdVector.y());
        }
        // Добавление в спрайт группу и установка коллизии
        firstBallGroup.add(ball1);
        secondBallGroup.add(ball2);
        if (thirdPoint != null) {
            thirdBallGroup.add(ball3);
        }
        CollisionHandler handler = new CollisionHandler(table);
        handler.addHandleEndListener(this);
        collision = new CollisionObjectWithObject();
        collision.addSpritesCollidedListener(handler);
        playfield.addCollisionGroup(firstBallGroup, secondBallGroup, collision);
        clone1 = ballElement1.clone();
        clone2 = ballElement2.clone();
        
        if (thirdPoint != null) {
            playfield.addCollisionGroup(firstBallGroup, thirdBallGroup, collision);
            playfield.addCollisionGroup(thirdBallGroup, secondBallGroup, collision);
            clone3 = ballElement3.clone();
        }
    }

    /**
     * Обновить
     * @param elapsedTime время обновления
     */
    @Override
    public void update(long elapsedTime) {
        playfield.update(elapsedTime);
    }

    /**
     * Отрисовка
     * @param gd графика
     */
    @Override
    public void render(Graphics2D gd) {
        playfield.render(gd);
    }

    /**
     * Проверка условия тестов
     * @param e событие
     */
    @Override
    public void checkAssertion(CollisionHandleEndEvent e) {
        Ball clone12 = clone1.clone();
        Ball clone22 = clone2.clone();
        if (thirdPoint == null) {
            clone1.handleCollision(clone2);
            clone22.handleCollision(clone12);
            assertEquals(e.firstElement.speed(), clone1.speed());
            assertEquals(e.secondElement.speed(), clone22.speed());
        } else {
            Ball clone13 =  clone1.clone();
            Ball clone23 = clone2.clone();
            Ball clone31 = clone3.clone();
            Ball clone32 = clone3.clone();
            Ball clone33 = clone3.clone();
            Ball ball23 = new Ball(table);
            ball23.setSpeed(clone22.speed().sum(clone31.speed()));
            ball23.setWeight(clone22.weight()+clone31.weight());
            clone1.handleCollision(ball23);
            Ball ball13 = new Ball(table);
            ball13.setSpeed(clone12.speed().sum(clone32.speed()));
            ball13.setWeight(clone12.weight()+clone32.weight());
            clone2.handleCollision(ball13);
            Ball ball12 = new Ball(table);
            ball12.setSpeed(clone23.speed().sum(clone33.speed()));
            ball12.setWeight(clone23.weight()+clone33.weight());
            clone3.handleCollision(ball12);
            assertEquals(e.firstElement.speed(), clone1.speed());
            assertEquals(e.secondElement.speed(), clone2.speed());
            assertEquals(e.secondElement.speed(), clone3.speed());
        }
        this.stop();
    }
    
    public void setFirstSpeed(SpeedVector speed) {
        firstVector = speed;
    }
    
    public void setSecondSpeed(SpeedVector speed) {
        secondVector = speed;
    }
    
    public void setThirdSpeed(SpeedVector speed) {
        thirdVector = speed;
    }
    
    public void setFirstPoint(Point point) {
        firstPoint = point;
    }
    
    public void setSecondPoint(Point point) {
        secondPoint = point;
    }
    
    public void setThirdPoint(Point point) {
        thirdPoint = point;
    }
    
    public void setFirstWeight(double weight) {
        firstWeigt = weight;
    }
    
    public void setSecondWeight(double weight) {
        secondWeigt = weight;
    }
    
    public void setThirdWeight(double weight) {
       thirdWeigt = weight;
    }
}
