/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.collision;

import arcanoid.events.CollisionHandleEndEvent;
import arcanoid.events.CollisionHandleEndListener;
import arcanoid.model.Ball;
import arcanoid.model.Racket;
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

    private SpeedVector firstVector;
    private SpeedVector secondVector;
    private Point firstPoint;
    private Point secondPoint;
    private Ball cloneBall;
    private Racket cloneRacket;
    
    /**
     * Конструктор
     * @param table Буфер
     */
    public RacketCollisions(Buffer table) {
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
        racketGroup = new SpriteGroup("Rackets");
        
        playfield.addGroup(ballGroup);
        playfield.addGroup(racketGroup);
        // Создание спрайтов
        BufferedImage ballImage = getImage("img/ball.png");
        BufferedImage racketImage = getImage("img/r.png");
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
        cloneBall = ballElement.clone();
        cloneRacket = racketElement.clone();
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
        Racket cloneRacket1 = cloneRacket.clone();
        Ball cloneBall1 = cloneBall.clone();
        cloneBall.handleCollision(cloneRacket1);
        cloneRacket.handleCollision(cloneBall1);
        assertEquals(e.firstElement.speed(), cloneBall.speed());
        assertEquals(e.secondElement.speed(), cloneRacket.speed());
    }
    
    public void setFirstSpeed(SpeedVector speed) {
        firstVector = speed;
    }
    
    public void setSecondSpeed(SpeedVector speed) {
        secondVector = speed;
    }
    
    public void setFirstPoint(Point point) {
        firstPoint = point;
    }
    
    public void setSecondPoint(Point point) {
        secondPoint = point;
    }
    

}