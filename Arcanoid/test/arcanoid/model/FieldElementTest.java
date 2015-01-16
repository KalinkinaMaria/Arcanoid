/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.model;

import arcanoid.GameField;
import arcanoid.service.Buffer;
import arcanoid.service.SpeedVector;
import com.golden.gamedev.object.Sprite;

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
    private GameField field;
    
    public FieldElementTest() {
        table = new Buffer();
        field = new GameField(table);
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
        field = new GameField(table);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Тест для тестирования обработки столкновения мяча с ракеткой в покое (в центр).
     */
    @Test
    public void testHandleCollisionBallWithStopedRacketInCenter() {
        System.out.println("Тест для тестирования обработки столкновения мяча с ракеткой в покое (мяч попал в середину ракетки)");
        Racket racket = new Racket(table);
        Ball ball = new Ball(table);
        //Установить позиции
        table.addPair(racket, new Sprite(310, 575));
        table.addPair(ball, new Sprite(388, 551.2));
        // Устанавливаем скорости
        racket.setSpeed(new SpeedVector());
        ball.setSpeed(new SpeedVector(0,0.3));
        ball.handleCollision(racket);
        assertEquals(ball.speed(), new SpeedVector(0,-0.3));
    }

    /**
     * Тест для тестирования обработки столкновения мяча с ракеткой в покое (не в центр).
     */
    @Test
    public void testHandleCollisionBallWithStopedRacketInNotCenter() {
        System.out.println("Тест для тестирования обработки столкновения мяча с ракеткой в покое (мяч попал не в середину ракетки)");
        Racket racket = new Racket(table);
        Ball ball = new Ball(table);
        //Установить позиции
        table.addPair(racket, new Sprite(101, 575));
        table.addPair(ball, new Sprite(218, 551));
        // Устанавливаем скорости
        racket.setSpeed(new SpeedVector(0, 0));
        ball.setSpeed(new SpeedVector(-0.19,0.22));
        ball.handleCollision(racket);
        assertEquals(ball.speed(), new SpeedVector(0.13, -0.26));
    }
    
    /**
     * Тест для тестирования обработки столкновения мяча с ракеткой в движении (не в центр).
     */
    @Test
    public void testHandleCollisionBallWithMovingRacketInNotCenter() {
        System.out.println("Тест для тестирования обработки столкновения мяча с ракеткой в движении (мяч попал не в середину ракетки)");
        Racket racket = new Racket(table);
        Ball ball = new Ball(table);
        //Установить позиции
        table.addPair(racket, new Sprite(101, 575));
        table.addPair(ball, new Sprite(218, 551));
        // Устанавливаем скорости
        racket.setSpeed(new SpeedVector(0.4, 0));
        ball.setSpeed(new SpeedVector(-0.19,0.22));
        ball.handleCollision(racket);
        assertEquals(ball.speed(), new SpeedVector(0.13, -0.26));
    }
    
    /**
     * Тест для тестирования обработки столкновения мяча с роем.
     */
    @Test
    public void testHandleCollisionBallWithSwarm() {
        System.out.println("Тест для тестирования обработки столкновения мяча с роем");
        Swarm swarm = new Swarm(table, 1);
        Ball ball = new Ball(table);
        field.addElement(ball);
        // Устанавливаем скорости
        ball.setSpeed(new SpeedVector(10,-10));
        ball.handleCollision(swarm);
        assertFalse(field.containsElement(ball));
    }
    
    /**
     * Тест для тестирования обработки столкновения мяча с кирпичом.
     */
    @Test
    public void testHandleCollisionBallWithBrick() {
        System.out.println("Тест для тестирования обработки столкновения мяча с  кирпичом");
        DestroyableBrick brick = new DestroyableBrick(table);
        Ball ball = new Ball(table);
        Ball cloneBall;
        //Установить позиции
        table.addPair(brick, new Sprite(10, 10));
        table.addPair(ball, new Sprite(15, 20));
        // Устанавливаем скорости
        ball.setSpeed(new SpeedVector(10,5));
        cloneBall = ball.clone();
        ball.handleCollision(brick);
        cloneBall.handleCollision(SpeedVector.Axis.X, brick.position());
        assertEquals(ball.speed(), cloneBall.speed());
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
        table.addPair(racket, new Sprite(310, 575));
        table.addPair(ball, new Sprite(388, 551.2));
        // Устанавливаем скорости
        racket.setSpeed(new SpeedVector());
        ball.setSpeed(new SpeedVector(0,0.3));
        ball.handleCollision(racket);
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
        field.addElement(brick);
        field.addElement(ball);
        //Установить позиции
        table.addPair(brick, new Sprite(10, 10));
        table.addPair(ball, new Sprite(15, 20));
        // Устанавливаем скорости
        ball.setSpeed(new SpeedVector(10,5));
        brick.handleCollision(ball);
        assertFalse(field.containsElement(brick));
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
        field.addElement(brick);
        field.addElement(ball);
        //Установить позиции
        table.addPair(brick, new Sprite(10, 10));
        table.addPair(ball, new Sprite(15, 20));
        // Устанавливаем скорости
        ball.setSpeed(new SpeedVector(10,5));
        brick.handleCollision(ball);
        assertFalse(field.containsElement(brick));
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
        Ball cloneBall;
        ball.setWeight(1);
        //Установить позиции
        table.addPair(brick, new Sprite(10, 10));
        table.addPair(ball, new Sprite(30, 15));
        // Устанавливаем скорости
        ball.setSpeed(new SpeedVector(-5,10));
        cloneBall = ball.clone();
        ball.handleCollision(brick);
        cloneBall.handleCollision(SpeedVector.Axis.Y, brick.position());
        assertEquals(ball.speed(), cloneBall.speed());
    }
    
    /**
     * Тест для тестирования обработки столкновения мяча с углом кирпича.
     */
    @Test
    public void testHandleCollisionBallWithConerBrick() {
        System.out.println("Тест для тестирования обработки столкновения мяча с углом кирпича");
        DestroyableBrick brick = new DestroyableBrick(table);
        Ball ball = new Ball(table);
        Ball cloneBall;
        ball.setWeight(1);
        //Установить позиции
        table.addPair(brick, new Sprite(10, 10));
        table.addPair(ball, new Sprite(30, 20));
        // Устанавливаем скорости
        ball.setSpeed(new SpeedVector(-5,10));
        cloneBall = ball.clone();
        ball.handleCollision(brick);
        ball.handleCollision(SpeedVector.Axis.Z, brick.position());
        assertEquals(ball.speed(), cloneBall.speed());
    }
    
    /**
     * Тест для тестирования обработки столкновения мяча с углом ракетки.
     */
    @Test
    public void testHandleCollisionBallWithConerRacket() {
        System.out.println("Тест для тестирования обработки столкновения мяча с углом ракетки");
        Racket racket = new Racket(table);
        Ball ball = new Ball(table);
        Ball cloneBall;
        ball.setWeight(1);
        //Установить позиции
        table.addPair(racket, new Sprite(10, 10));
        table.addPair(ball, new Sprite(30, 10));
        // Устанавливаем скорости
        ball.setSpeed(new SpeedVector(-5, -10));
        cloneBall = ball.clone();
        ball.handleCollision(racket);
        cloneBall.handleCollision(SpeedVector.Axis.Z, racket.position());
        assertEquals(ball.speed(), cloneBall.speed());
    }
    
    /**
     * Тест для тестирования обработки столкновения мяча с углом движущейся ракетки.
     */
    @Test
    public void testHandleCollisionBallWithConerMovingRacket() {
        System.out.println("Тест для тестирования обработки столкновения мяча с углом движущейся ракетки");
        Racket racket = new Racket(table);
        Ball ball = new Ball(table);
        Ball cloneBall;
        ball.setWeight(1);
        //Установить позиции
        table.addPair(racket, new Sprite(10, 10));
        table.addPair(ball, new Sprite(30, 10));
        // Устанавливаем скорости
        ball.setSpeed(new SpeedVector(-5, -10));
        racket.setSpeed(new SpeedVector(3,0));
        cloneBall = ball.clone();
        ball.handleCollision(racket);
        cloneBall.handleCollision(SpeedVector.Axis.Z, racket.position());
        assertEquals(ball.speed(), cloneBall.speed());
    }
    
    /**
     * Тест для тестирования обработки столкновения мяча с другим мячом той же массы.
     */
    @Test
    public void testHandleCollisionBallWithSameBall() {
        System.out.println("Тест для тестирования обработки столкновения мяча с другим мячом той же массы");
        Ball ball1 = new Ball(table);
        Ball ball2 = new Ball(table);
        ball1.setWeight(1);
        ball2.setWeight(1);
        // Устанавливаем скорости
        ball1.setSpeed(new SpeedVector(2, -2));
        ball2.setSpeed(new SpeedVector(2, 5));
        Ball clone1 = ball1.clone();
        Ball cloneBall1 = ball1.clone();
        Ball cloneBall11 = ball1.clone();
        Ball clone2 = ball2.clone();
        Ball cloneBall2 = ball2.clone();
        ball1.handleCollision(clone2);
        cloneBall1.handleCollision(cloneBall2.position(), cloneBall2);
        assertEquals(ball1.speed(), cloneBall1.speed());
        ball2.handleCollision(clone1);
        cloneBall2.handleCollision(cloneBall11.position(), cloneBall11);
        assertEquals(ball2.speed(), cloneBall2.speed());
    }
    
    /**
     * Тест для тестирования обработки столкновения мяча с другим мячом другой массы массы.
     */
    @Test
    public void testHandleCollisionBallWithAnotherBall() {
        System.out.println("Тест для тестирования обработки столкновения мяча с другим мячом другой массы");
        Ball ball1 = new Ball(table);
        Ball ball2 = new Ball(table);
        ball1.setWeight(1);
        ball2.setWeight(2);
        // Устанавливаем скорости
        ball1.setSpeed(new SpeedVector(2, -2));
        ball2.setSpeed(new SpeedVector(2, 5));
        Ball clone1 = ball1.clone();
        Ball clone2 = ball2.clone();
        Ball cloneBall1 = ball1.clone();
        Ball cloneBall11 = ball1.clone();
        Ball cloneBall2 = ball2.clone();
        ball1.handleCollision(clone2);
        cloneBall1.handleCollision(cloneBall2.position(), cloneBall2);
        assertEquals(ball1.speed(), cloneBall1.speed());
        ball2.handleCollision(clone1);
        cloneBall2.handleCollision(cloneBall11.position(), cloneBall11);
        assertEquals(ball2.speed(), cloneBall2.speed());
    }
    
    /**
     * Тест для тестирования обработки столкновения ракетки с роем.
     */
    @Test
    public void testHandleCollisionRacketWithSwarm() {
        System.out.println("Тест для тестирования обработки столкновения ракетки с роем");
        Racket racket = new Racket(table);
        Swarm swarm = new Swarm(table, 2);
        // Устанавливаем скорости
        racket.setSpeed(new SpeedVector(2, 0));
        racket.handleCollision(swarm);
        assertEquals(racket.speed(), new SpeedVector(2,0));
        swarm.handleCollision(racket);
        assertEquals(swarm.weight(), 2);
    }
}
