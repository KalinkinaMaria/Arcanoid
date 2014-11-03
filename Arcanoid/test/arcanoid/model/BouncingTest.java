/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.model;

import arcanoid.service.Buffer;
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
public class BouncingTest {
    
    private Buffer table; 
    
    public BouncingTest() {
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
        ball.handleCollision(new Point(15, 10), new SpeedVector(3, 0), racket);
        assertEquals(ball.speed(), new SpeedVector(13,5));
    }
    
    /**
     * Тест для тестирования обработки столкновения мяча с углом движущейся ракетки.
     */
    @Test
    public void testHandleCollisionBallWithConerMovingRacket() {
        System.out.println("Тест для тестирования обработки столкновения мяча с углом движущейся ракетки");
        Racket racket = new Racket(table);
        Ball ball = new Ball(table);
        ball.setWeight(1);
        //Установить позиции
        table.addPair(racket, new Sprite(10, 10));
        table.addPair(ball, new Sprite(30, 10));
        // Устанавливаем скорости
        ball.setSpeed(new SpeedVector(-5, -10));
        racket.setSpeed(new SpeedVector(3,0));
        ball.handleCollision(new Point(30, 10), new SpeedVector(3,0), racket);
        assertEquals(ball.speed(), new SpeedVector(8, 10));
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
        Ball clone2 = ball2.clone();
        ball1.handleCollision(new Point(),new SpeedVector(2, 5), clone2);
        assertEquals(ball1.speed(), new SpeedVector(2, 5));
        ball2.handleCollision(new Point(), new SpeedVector(2, -2), clone1);
        assertEquals(ball2.speed(), new SpeedVector(2, -2));
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
        ball1.handleCollision(new Point(),new SpeedVector(2, 5), clone2);
        assertEquals(ball1.speed(), new SpeedVector(2, 22/3));
        ball2.handleCollision(new Point(), new SpeedVector(2, -2), clone1);
        assertEquals(ball2.speed(), new SpeedVector(2, -1/3));
    }
}
