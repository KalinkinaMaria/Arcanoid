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

/**
 *
 * @author Елена
 */
public class BallWithTwoHorizontalBrick extends Game implements CollisionHandleEndListener {
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
    
    /**
     * Конструктор
     * @param table Буфер
     */
    public BallWithTwoHorizontalBrick (Buffer table) {
        this.table = table;
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
        Sprite ball = new Sprite(ballImage, 400, 500);
        Sprite brick1 = new Sprite(brickImage, 332.5, 470);
        Sprite brick2 = new Sprite(brickImage, 412.5, 470);
        //Заполняем буфер
        Ball ballElement = new Ball(table);
        DestroyableBrick brickElement1 = new DestroyableBrick(table);
        DestroyableBrick brickElement2 = new DestroyableBrick(table);
        table.addPair(ballElement, ball);
        table.addPair(brickElement1, brick1);
        table.addPair(brickElement2, brick2);
        ballElement.addCollisionHandleEndListener(this);
        ball.setSpeed(0.5, 1);
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
        assertEquals(e.firstElement.speed(), new SpeedVector(0.5,-1));
        assertEquals(e.secondElement, null);
        assertEquals(e.thirdElement, null);
    }
}