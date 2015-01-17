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
import arcanoid.model.Swarm;
import arcanoid.model.SwarmElement;
import arcanoid.service.Buffer;
import arcanoid.service.SpeedVector;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Елена
 */
public class BallWithSwarm extends Game implements CollisionHandleEndListener {
    /** Игровое поле */
    private PlayField  playfield;
    /** Группа шарика */
    private SpriteGroup ballGroup;
    /** Группа кирпича */
    private SpriteGroup swarmGroup;
    /** Менеджер коллизии */
    private CollisionObjectWithObject collision;
    /** Буфер */
    private Buffer table;
    
    /**
     * Конструктор
     * @param table Буфер
     */
    public BallWithSwarm (Buffer table) {
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
        swarmGroup = new SpriteGroup("Swarm");
        
        playfield.addGroup(ballGroup);
        playfield.addGroup(swarmGroup);
        // Создание спрайтов
        BufferedImage ballImage = getImage("img/ball.png");
        BufferedImage swarmImage = getImage("img/ball.png");
        Sprite ball = new Sprite(ballImage, 400, 500);
        Sprite swarm = new Sprite(swarmImage, 425, 500);
        //Заполняем буфер
        Ball ballElement = new Ball(table);
        ballElement.setWeight(1);
        Swarm swarmElement = new Swarm(table, 1);
        SwarmElement swarmElementElement = swarmElement.elements().get(0);
        table.addPair(ballElement, ball);
        table.addPair(swarmElementElement, swarm);
        ballElement.addCollisionHandleEndListener(this);
        ball.setSpeed(0.5, 0);
        // Добавление в спрайт группу и установка коллизии
        ballGroup.add(ball);
        swarmGroup.add(swarm);
        CollisionHandler handler = new CollisionHandler(table);
        handler.addHandleEndListener(this);
        collision = new CollisionObjectWithObject();
        collision.addSpritesCollidedListener(handler);
        playfield.addCollisionGroup(ballGroup, swarmGroup, collision);
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
        assertEquals(e.firstElement.speed(), new SpeedVector());
        assertEquals(e.secondElement.weight(), 2.0);
        this.stop();
    }
}
