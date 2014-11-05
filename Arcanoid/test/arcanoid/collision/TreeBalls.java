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
public class TreeBalls extends Game implements CollisionHandleEndListener {
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
    
    /**
     * Конструктор
     * @param table Буфер
     */
    public TreeBalls(Buffer table) {
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

        Sprite ball1 = new Sprite(ballImage, 400, 500);
        Sprite ball2 = new Sprite(ballImage, 425, 500);
        Sprite ball3 = new Sprite(ballImage, 412.5, 475);
        //Заполняем буфер
        Ball ballElement1 = new Ball(table);
        ballElement1.setWeight(1);
        Ball ballElement2 = new Ball(table);
        ballElement2.setWeight(1);
        Ball ballElement3 = new Ball(table);
        ballElement3.setWeight(1);
        table.addPair(ballElement1, ball1);
        table.addPair(ballElement2, ball2);
        table.addPair(ballElement3, ball3);
        ballElement1.addCollisionHandleEndListener(this);
        ball1.setSpeed(0.5, 1);
        ball2.setSpeed(-0.4, 0.6);
        ball3.setSpeed(0, 0.7);
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
        assertEquals(e.firstElement.speed(), new SpeedVector(0, 0.7));
        assertEquals(e.secondElement.speed(), new SpeedVector(0.5, 1));
        assertEquals(e.thirdElement.speed(), new SpeedVector(-0.4, 0.6));
    }
