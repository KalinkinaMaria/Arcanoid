/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.collision;

import arcanoid.GameField;
import arcanoid.events.CollisionHandleEndEvent;
import arcanoid.events.CollisionHandleEndListener;
import arcanoid.model.Ball;
import arcanoid.model.DestroyableBrick;
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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

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
    /** Игровое поле*/
    private GameField field;
    private SpeedVector speedVector;
    private Point firstPoint;
    private Point secondPoint;
    private Point thirdPoint;
    private Ball cloneBall;
    private DestroyableBrick cloneBrick1;
    private DestroyableBrick cloneBrick2;
    /**
     * Конструктор
     * @param table Буфер
     */
    public BallWithBricks (Buffer table) {
        this.table = table;
        field = new GameField(table);
    }
    
    /**
     * Инициализация ресурсов
     */
    @Override
    public void initResources() {
        
        playfield = new PlayField();
        // Создание спрайт групп
        ballGroup = new SpriteGroup("Balls");
        brickGroup = new SpriteGroup("Bricks");
        
        playfield.addGroup(ballGroup);
        playfield.addGroup(brickGroup);
        
        // Создание спрайтов
        BufferedImage ballImage = getImage("img/ball.png");
        BufferedImage brickImage = getImage("img/brick.png");
        Sprite ball = new Sprite(ballImage, firstPoint.x, firstPoint.y);
        Sprite brick1 = new Sprite(brickImage, secondPoint.x, secondPoint.y);
        Sprite brick2 = null;
        if (thirdPoint != null) {
            brick2 = new Sprite(brickImage, thirdPoint.x, thirdPoint.y);
        }
        //Заполняем буфер
        Ball ballElement = new Ball(table);
        DestroyableBrick brickElement1 = new DestroyableBrick(table);
        DestroyableBrick brickElement2 = new DestroyableBrick(table);
        table.addPair(ballElement, ball);
        table.addPair(brickElement1, brick1);
        if (thirdPoint != null) {
            table.addPair(brickElement2, brick2);
        }
        // Добавление в игровое поле
        field.addElement(ballElement);
        field.addElement(brickElement1);
        if (thirdPoint != null) {
            field.addElement(brickElement2);
        }
        ballElement.addCollisionHandleEndListener(this);
        ball.setSpeed(speedVector.x(), speedVector.y());
        // Добавление в спрайт группу и установка коллизии
        ballGroup.add(ball);
        brickGroup.add(brick1);
        if (thirdPoint != null) {
            brickGroup.add(brick2);
        }
        collision = new CollisionObjectWithObject();
        playfield.addCollisionGroup(ballGroup, brickGroup, collision);
        cloneBall = ballElement.clone();
        cloneBrick1 = brickElement1.clone();
        if (thirdPoint != null) {
            cloneBrick2 = brickElement2.clone();
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
        cloneBall.handleCollision(cloneBrick1);
        assertEquals(e.firstElement.speed(), cloneBall.speed());
        assertFalse(field.containsElement(e.secondElement));
        if (thirdPoint != null) {
            assertFalse(field.containsElement(e.thirdElement));
        }
    }
    
    public void setSpeed(SpeedVector speed) {
        speedVector = speed;
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
}
