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
public class RacketCollisions extends Game implements CollisionHandleEndListener {
    /** Игровое поле */
    private PlayField  playfield;
    /** Группа шарика */
    private SpriteGroup ballGroup;
    /** Группа ракетки */
    private SpriteGroup racketGroup;
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
    public RacketCollisions(Buffer table, int testNumber) {
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
        SpeedVector firstVector;
        SpeedVector secondVector;
        Point firstPoint;
        Point secondPoint;
        playfield = new PlayField();
        // Создание спрайт групп
        ballGroup = new SpriteGroup("Balls");
        racketGroup = new SpriteGroup("Rackets");
        
        playfield.addGroup(ballGroup);
        playfield.addGroup(racketGroup);
        // Создание спрайтов
        BufferedImage ballImage = getImage("img/ball.png");
        BufferedImage racketImage = getImage("img/r.png");
        
        switch (this.testNumber) {
            case 0:
                firstVector = new SpeedVector(-0.5, -1);
                secondVector = new SpeedVector();
                firstPoint = new Point(400, 500);
                secondPoint = new Point(220, 525);
                firstWeigt = 1;
                secondWeigt = 0;
                break;
            case 1:
                firstVector = new SpeedVector(1, -1);
                secondVector = new SpeedVector();
                firstPoint = new Point(280, 480);
                secondPoint = new Point(220, 525);
                firstWeigt = 1;
                secondWeigt = 0;
                break;

        }
        Sprite ball = new Sprite(ballImage, firstPoint.x, firstPoint.y);
        Sprite racket = new Sprite(racketImage, secondPoint.x, secondPoint.y);
        //Заполняем буфер
        Ball ballElement = new Ball(table);
        Racket racketElement = new Racket(table);
        table.addPair(ballElement, ball);
        table.addPair(racketElement, racket);
        ballElement.addCollisionHandleEndListener(this);
        ball.setSpeed(firstVector.x(), secondVector.y());
        // Добавление в спрайт группу и установка коллизии
        ballGroup.add(ball);
        racketGroup.add(racket);
        collision = new CollisionObjectWithObject();
        playfield.addCollisionGroup(ballGroup, racketGroup, collision);
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
                assertEquals(e.firstElement.speed(), new SpeedVector(0.5,1));
                assertEquals(e.secondElement.speed(), new SpeedVector());
                break;
            case 1:
                assertEquals(e.firstElement.speed(), new SpeedVector(1,1));
                assertEquals(e.secondElement.speed(), new SpeedVector());
                break;
        }
    }
}