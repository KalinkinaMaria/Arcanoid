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
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
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
    /** Номер теста*/
    private int testNumber;
    
    /**
     * Конструктор
     * @param table Буфер
     */
    public BallsCollision(Buffer table, int testNumber) {
        this.table = table;
        this.testNumber = testNumber;
    }
    
    /**
     * Инициализация ресурсов
     */
    @Override
    public void initResources() {
        double firstWeigt;
        double secondWeigt;
        double thirdWeigt;
        SpeedVector firstVector;
        SpeedVector secondVector;
        SpeedVector thirdVector;
        Point firstPoint;
        Point secondPoint;
        Point thirdPoint;
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

        switch (this.testNumber) {
            case 0:
                firstVector = new SpeedVector(0.5, 1);
                secondVector = new SpeedVector(-0.4, 0.6);
                thirdVector = new SpeedVector(0, 0.7);
                firstPoint = new Point(400, 500);
                secondPoint = new Point(425, 500);
                thirdPoint = new Point(412.5, 475);
                firstWeigt = 1;
                secondWeigt = 1;
                thirdWeigt = 1;
                break;
            case 1:
                firstVector = new SpeedVector(0.5, 1);
                secondVector = new SpeedVector(-0.4, 0.6);
                thirdVector = null;
                firstPoint = new Point(400, 500);
                secondPoint = new Point(425, 500);
                thirdPoint = null;
                firstWeigt = 1;
                secondWeigt = 1;
                thirdWeigt = 0.0;
                break;
            case 2:
                firstVector = new SpeedVector(0.5, 1);
                secondVector = new SpeedVector(-0.4, 0.6);
                thirdVector = null;
                firstPoint = new Point(400, 500);
                secondPoint = new Point(425, 500);
                thirdPoint = null;
                firstWeigt = 1;
                secondWeigt = 3;
                thirdWeigt = 0.0;
                break;
        }
        Sprite ball1 = new Sprite(ballImage, firstPoint.x, firstPoint.y);
        Sprite ball2 = new Sprite(ballImage, secondPoint.x, secondPoint.y);
        Sprite ball3 = new Sprite(ballImage, thirdPoint.x, thirdPoint.y);
        //Заполняем буфер
        Ball ballElement1 = new Ball(table);
        ballElement1.setWeight(firstWeigt);
        Ball ballElement2 = new Ball(table);
        ballElement2.setWeight(secondWeigt);
        Ball ballElement3 = new Ball(table);
        ballElement3.setWeight(thirdWeigt);
        table.addPair(ballElement1, ball1);
        table.addPair(ballElement2, ball2);
        table.addPair(ballElement3, ball3);
        ballElement1.addCollisionHandleEndListener(this);
        ball1.setSpeed(firstVector.x(), firstVector.y());
        ball2.setSpeed(secondVector.x(), secondVector.y());
        ball3.setSpeed(thirdVector.x(), thirdVector.y());
        // Добавление в спрайт группу и установка коллизии
        firstBallGroup.add(ball1);
        secondBallGroup.add(ball2);
        thirdBallGroup.add(ball3);
        collision = new CollisionObjectWithObject();
        playfield.addCollisionGroup(firstBallGroup, secondBallGroup, collision);
        playfield.addCollisionGroup(firstBallGroup, thirdBallGroup, collision);
        playfield.addCollisionGroup(thirdBallGroup, secondBallGroup, collision);
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
        switch (this.testNumber) {
            case 0:
                assertEquals(e.firstElement.speed(), new SpeedVector(0, 0.7));
                assertEquals(e.secondElement.speed(), new SpeedVector(0.5, 1));
                assertEquals(e.thirdElement.speed(), new SpeedVector(-0.4, 0.6));
                break;
            case 1:
                assertEquals(e.firstElement.speed(), new SpeedVector(-0.4, 0.6));
                assertEquals(e.secondElement.speed(), new SpeedVector(0.5, 1));
                assertEquals(e.thirdElement.speed(), null);
                break;
            case 2:
                assertEquals(e.firstElement.speed(), new SpeedVector(0.95, 1.2));
                assertEquals(e.secondElement.speed(), new SpeedVector(0.05, 0.8));
                assertEquals(e.thirdElement.speed(), null);
                break;
    }
