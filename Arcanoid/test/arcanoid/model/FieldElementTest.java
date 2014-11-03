/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.model;

import arcanoid.events.GameFieldChangeListener;
import arcanoid.service.Buffer;
import arcanoid.service.Size;
import arcanoid.service.SpeedVector;
import com.golden.gamedev.object.Sprite;
import java.awt.Point;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Елена
 */
public class FieldElementTest {
    private Buffer table;
    
    public FieldElementTest() {
        table = new Buffer();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        table = new Buffer();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Тест для тестирования обработки столкновения мяча с ракеткой в покое.
     */
    @Test
    public void testHandleCollisionBallWithStopedRacket() {
        System.out.println("Тест для тестирования обработки столкновения мяча с ракеткойв покое");
        Racket racket = new Racket(table);
        Ball ball = new Ball(table);
        //Установить позиции
        table.addPair(racket, new Sprite(10, 10));
        table.addPair(ball, new Sprite(15, 10));
        // Устанавливаем скорости
        racket.setSpeed(new SpeedVector());
        ball.setSpeed(new SpeedVector(10,-5));
        ball.handleCollision(racket);
        assertEquals(ball.speed(), new SpeedVector(10,5));
    }

    /**
     * Тест для тестирования обработки столкновения мяча с ракеткой в движении.
     */
    @Test
    public void testHandleCollisionBallWithMovingRacket() {
        System.out.println("Тест для тестирования обработки столкновения мяча с ракеткой в движении");
        Racket racket = new Racket(table);
        Ball ball = new Ball(table);
        //Установить позиции
        table.addPair(racket, new Sprite(10, 10));
        table.addPair(ball, new Sprite(15, 10));
        // Устанавливаем скорости
        racket.setSpeed(new SpeedVector(3, 0));
        ball.setSpeed(new SpeedVector(10,-5));
        ball.handleCollision(racket);
        assertEquals(ball.speed(), new SpeedVector(13,5));
    }
    
    /**
     * Тест для тестирования обработки столкновения мяча с роем.
     */
    @Test
    public void testHandleCollisionBallWithSwarm() {
        System.out.println("Тест для тестирования обработки столкновения мяча с роем");
        Swarm swarm = new Swarm(table, 1);
        Ball ball = new Ball(table);
        // Устанавливаем скорости
        ball.setSpeed(new SpeedVector(10,-10));
        ball.handleCollision(swarm);
        assertEquals(ball.speed(), new SpeedVector());
    }
    
    /**
     * Тест для тестирования обработки столкновения мяча с кирпичом.
     */
    @Test
    public void testHandleCollisionBallWithBrick() {
        System.out.println("Тест для тестирования обработки столкновения мяча с  кирпичом");
        DestroyableBrick brick = new DestroyableBrick(table);
        Ball ball = new Ball(table);
        //Установить позиции
        table.addPair(brick, new Sprite(10, 10));
        table.addPair(ball, new Sprite(15, 20));
        // Устанавливаем скорости
        ball.setSpeed(new SpeedVector(10,5));
        ball.handleCollision(brick);
        assertEquals(ball.speed(), new SpeedVector(10,-5));
    }
    
    /**
     * Тест для тестирования обработки столкновения ракетки в покое с мячом.
     */
    @Test
    public void testHandleCollisionStopedRacketWithBall() {
        System.out.println("Тест для тестирования обработки столкновения ракетки в покое с мячом");
        Racket racket = new Racket(table);
        Ball ball = new Ball(table);
        //Установить позиции
        table.addPair(racket, new Sprite(10, 10));
        table.addPair(ball, new Sprite(15, 10));
        // Устанавливаем скорости
        racket.setSpeed(new SpeedVector());
        ball.setSpeed(new SpeedVector(10,-5));
        racket.handleCollision(ball);
        assertEquals(racket.speed(), new SpeedVector());
    }

    /**
     * Тест для тестирования обработки столкновения ракетки в движении с мячом.
     */
    @Test
    public void testHandleCollisionMovingRacketWithBall() {
        System.out.println("Тест для тестирования обработки столкновения ракетки в движении с мячом");
        Racket racket = new Racket(table);
        Ball ball = new Ball(table);
        //Установить позиции
        table.addPair(racket, new Sprite(10, 10));
        table.addPair(ball, new Sprite(15, 10));
        // Устанавливаем скорости
        racket.setSpeed(new SpeedVector(3, 0));
        ball.setSpeed(new SpeedVector(10,-5));
        racket.handleCollision(ball);
        assertEquals(racket.speed(), new SpeedVector(3,0));
    }
    
    /**
     * Тест для тестирования обработки столкновения роя с мячом.
     */
    @Test
    public void testHandleCollisionSwarmWithBall() {
        System.out.println("Тест для тестирования обработки столкновения роя с мячом");
        Swarm swarm = new Swarm(table, 1);
        Ball ball = new Ball(table);
        ball.setWeight(1);
        // Устанавливаем скорости
        ball.setSpeed(new SpeedVector(10,-10));
        swarm.handleCollision(ball);
        assertEquals(swarm.weight(), 2);
    }
    
    /**
     * Тест для тестирования обработки столкновения разрушаемого кирпича(без прочности) с мячом.
     */
    @Test
    public void testHandleCollisionBallWithDestroableBrick() {
        System.out.println("Тест для тестирования обработки столкновения разрушаемого кирпича(без прочности) с мячом");
        DestroyableBrick brick = new DestroyableBrick(table);
        Ball ball = new Ball(table);
        ball.setWeight(1);
        //Установить позиции
        table.addPair(brick, new Sprite(10, 10));
        table.addPair(ball, new Sprite(15, 20));
        // Устанавливаем скорости
        ball.setSpeed(new SpeedVector(10,5));
        brick.handleCollision(ball);
        assertEquals(brick, null);
    }
    
    /**
     * Тест для тестирования обработки столкновения разрушаемого кирпича(c прочностью) с мячом.
     */
    @Test
    public void testHandleCollisionBallWithSmallStrengthBrick() {
        System.out.println("Тест для тестирования обработки столкновения разрушаемого кирпича(с прочностью) с мячом");
        DestroyableBrick brick = new DestroyableBrick(table);
        Ball ball = new Ball(table);
        ball.setWeight(1);
        brick.setStrength(1);
        //Установить позиции
        table.addPair(brick, new Sprite(10, 10));
        table.addPair(ball, new Sprite(15, 20));
        // Устанавливаем скорости
        ball.setSpeed(new SpeedVector(10,5));
        brick.handleCollision(ball);
        assertEquals(brick, null);
    }
    
    /**
     * Тест для тестирования обработки столкновения разрушаемого кирпича(уменьшение прочности) с мячом.
     */
    @Test
    public void testHandleCollisionBallWithDecreaseStrengthBrick() {
        System.out.println("Тест для тестирования обработки столкновения разрушаемого кирпича(уменьшение прочности) с мячом");
        DestroyableBrick brick = new DestroyableBrick(table);
        Ball ball = new Ball(table);
        ball.setWeight(1);
        brick.setStrength(15);
        //Установить позиции
        table.addPair(brick, new Sprite(10, 10));
        table.addPair(ball, new Sprite(15, 20));
        // Устанавливаем скорости
        ball.setSpeed(new SpeedVector(10,5));
        brick.handleCollision(ball);
        //TODO посчитать точно
        assertEquals(brick.strength(), 10);
    }
    
    /**
     * Тест для тестирования обработки столкновения мяча с боковой стороной кирпича.
     */
    @Test
    public void testHandleCollisionBallWithSideBrick() {
        System.out.println("Тест для тестирования обработки столкновения мяча с боковой стороной кирпича");
        DestroyableBrick brick = new DestroyableBrick(table);
        Ball ball = new Ball(table);
        ball.setWeight(1);
        //Установить позиции
        table.addPair(brick, new Sprite(10, 10));
        table.addPair(ball, new Sprite(30, 15));
        // Устанавливаем скорости
        ball.setSpeed(new SpeedVector(-5,10));
        ball.handleCollision(brick);
        assertEquals(ball.speed(), new SpeedVector(5, 10));
    }
    
    /**
     * Тест для тестирования обработки столкновения мяча с углом кирпича.
     */
    @Test
    public void testHandleCollisionBallWithConerBrick() {
        System.out.println("Тест для тестирования обработки столкновения мяча с углом кирпича");
        DestroyableBrick brick = new DestroyableBrick(table);
        Ball ball = new Ball(table);
        ball.setWeight(1);
        //Установить позиции
        table.addPair(brick, new Sprite(10, 10));
        table.addPair(ball, new Sprite(30, 20));
        // Устанавливаем скорости
        ball.setSpeed(new SpeedVector(-5,10));
        ball.handleCollision(brick);
        assertEquals(ball.speed(), new SpeedVector(5, 10));
    }
    
    /**
     * Тест для тестирования обработки столкновения мяча с углом ракетки.
     */
    @Test
    public void testHandleCollisionBallWithConerRacket() {
        System.out.println("Тест для тестирования обработки столкновения мяча с углом ракетки");
        Racket racket = new Racket(table);
        Ball ball = new Ball(table);
        ball.setWeight(1);
        //Установить позиции
        table.addPair(racket, new Sprite(10, 10));
        table.addPair(ball, new Sprite(30, 10));
        // Устанавливаем скорости
        ball.setSpeed(new SpeedVector(-5, -10));
        ball.handleCollision(racket);
        assertEquals(ball.speed(), new SpeedVector(5, 10));
    }
}
