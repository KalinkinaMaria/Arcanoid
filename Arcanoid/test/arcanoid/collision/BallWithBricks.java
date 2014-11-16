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
public class BallWithBricks extends Game implements CollisionHandleEndListener {
    /** Игровое поле */
    private PlayField  playfield;
    /** Группа шарика */
    private SpriteGroup ballGroup;
    /** Группа кирпича */
    private SpriteGroup brickGroup;
    /** Менеджер коллизии */
    private CollisionObjectWithObject collision;
     /** Буфер */
    private Buffer table;
    /** Номер теста*/
    private int testNumber; 
    /** Игровое поле*/
    private GameField field;
    
    /**
     * Конструктор
     * @param table Буфер
     */
    public BallWithBricks (Buffer table, int testNumber) {
        this.table = table;
        this.testNumber = testNumber;
        field = new GameField();
    }
    
    /**
     * Инициализация ресурсов
     */
    @Override
    public void initResources() {
        SpeedVector speedVector;
        Point firstPoint;
        Point secondPoint;
        Point thirdPoint;
        playfield = new PlayField();
        // Создание спрайт групп
        ballGroup = new SpriteGroup("Balls");
        brickGroup = new SpriteGroup("Bricks");
        
        playfield.addGroup(ballGroup);
        playfield.addGroup(brickGroup);
        switch (this.testNumber) {
            case 0:
                speedVector = new SpeedVector(0.5, 1);
                firstPoint = new Point(400, 500);
                secondPoint = new Point(333, 470);
                thirdPoint = new Point(413, 470);
                break;
            case 1:
                speedVector = new SpeedVector(0.5, 1);
                firstPoint = new Point(400, 500);
                secondPoint = new Point(360, 470);
                thirdPoint = new Point(0, 0);
                break;
            case 2:
                speedVector = new SpeedVector(0.5, 1);
                firstPoint = new Point(400, 500);
                secondPoint = new Point(425, 470);
                thirdPoint = new Point(0, 0);
                break;
            case 3:
                speedVector = new SpeedVector(-0.5, 1);
                firstPoint = new Point(400, 500);
                secondPoint = new Point(320, 500);
                thirdPoint = new Point(0, 0);
                break;
            case 4:
                speedVector = new SpeedVector(-0.5, 1);
                firstPoint = new Point(400, 500);
                secondPoint = new Point(320, 500);
                thirdPoint = new Point(400, 470);
                break;

        }
        // Создание спрайтов
        BufferedImage ballImage = getImage("img/ball.png");
        BufferedImage brickImage = getImage("img/brick.png");
        Sprite ball = new Sprite(ballImage, firstPoint.x, firstPoint.y);
        Sprite brick1 = new Sprite(brickImage, secondPoint.x, secondPoint.y);
        Sprite brick2 = new Sprite(brickImage, thirdPoint.x, thirdPoint.y);
        //Заполняем буфер
        Ball ballElement = new Ball(table);
        DestroyableBrick brickElement1 = new DestroyableBrick(table);
        DestroyableBrick brickElement2 = new DestroyableBrick(table);
        table.addPair(ballElement, ball);
        table.addPair(brickElement1, brick1);
        table.addPair(brickElement2, brick2);
        field.addElement(ballElement);
        field.addElement(brickElement1);
        field.addElement(brickElement2);
        ballElement.addCollisionHandleEndListener(this);
        ball.setSpeed(speedVector.x(), speedVector.y());
        // Добавление в игровое поле
        field.addElement();
        // Добавление в спрайт группу и установка коллизии
        ballGroup.add(ball);
        brickGroup.add(brick1);
        brickGroup.add(brick2);
        collision = new CollisionObjectWithObject();
        playfield.addCollisionGroup(ballGroup, brickGroup, collision);
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
                assertEquals(e.firstElement.speed(), new SpeedVector(0.5,-1));
                assertFalse(field.containsElement(e.secondElement));
                assertFalse(field.containsElement(e.thirdElement));
                break;
            case 1:
                assertEquals(e.firstElement.speed(), new SpeedVector(0.5,-1));
                assertFalse(field.containsElement(e.secondElement));
                break;
            case 2:
                assertEquals(e.firstElement.speed(), new SpeedVector(-0.5,-1));
                assertFalse(field.containsElement(e.secondElement));
                break;
            case 3:
                assertEquals(e.firstElement.speed(), new SpeedVector(0.5,1));
                assertFalse(field.containsElement(e.secondElement));
                break;
            case 4:
                assertEquals(e.firstElement.speed(), new SpeedVector(0.5,-1));
                assertFalse(field.containsElement(e.secondElement));
                assertFalse(field.containsElement(e.thirdElement));
                break;
        }
    }
}
