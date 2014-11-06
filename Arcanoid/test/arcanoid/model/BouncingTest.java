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
import javafx.scene.chart.Axis;
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
     * Тестирование столкновения отскакивающего с тем от которого можно отскочить в движении
     */
    @Test
    public void testHandleCollisionBoucingWithMovingBounced() {
        System.out.println("Тестирование столкновения отскакивающего с тем от которого можно отскочить в движении");
        Racket racket = new Racket(table);
        Bouncing ball = new Bouncing(table);
        SpeedVector vector1 = new SpeedVector(3, 0);
        SpeedVector vector2 = new SpeedVector(10,-5);
        //Установить позиции
        Point position = new Point(15, 10);
        table.addPair(racket, new Sprite(10, 10));
        table.addPair(ball, new Sprite(15, 10));
        // Устанавливаем скорости
        racket.setSpeed(vector1);
        ball.setSpeed(vector2);
        ball.handleCollision(position, vector1, racket);
        assertEquals(ball.speed(), vector2.sum(vector1));
        assertEquals(ball.position(), position);
    }
    
    /**
     * Тестирование столкновения отскакивающего с углом того от которого можно отскочить в движении
     */
    @Test
    public void testHandleCollisionBoucingWithConerMovingBounced() {
        System.out.println("Тестирование столкновения отскакивающего с углом того от которого можно отскочить в движении");
        Racket racket = new Racket(table);
        Bouncing ball = new Ball(table);
        ball.setWeight(1);
        SpeedVector vector1 = new SpeedVector(-5, -10);
        SpeedVector vector2 = new SpeedVector(3,0);
        //Установить позиции
        Point position = new Point(30, 10);
        table.addPair(racket, new Sprite(10, 10));
        table.addPair(ball, new Sprite(30, 10));
        // Устанавливаем скорости
        ball.setSpeed(vector1);
        racket.setSpeed(vector2);
        ball.handleCollision(position, vector2, racket);
        assertEquals(ball.speed(), vector1.reflect(Axis.X));
        assertEquals(ball.position(), position);
    }
    
    /**
     *Тестирование отскока отскакивающиго от того же отскакивающего
     */
    @Test
    public void testHandleCollisionBouncingWithSameBouncing() {
        System.out.println("Тестирование отскока отскакивающиго от того же отскакивающего");
        Bouncing ball1 = new Bouncing(table);
        Bouncing ball2 = new Bouncing(table);
        ball1.setWeight(1);
        ball2.setWeight(1);
        SpeedVector vector1 = new SpeedVector(2, -2);
        SpeedVector vector2 = new SpeedVector(2, 5);
        Point position1 = new Point(10, 10);
        Point position2 = new Point(15, 10);
        
        table.addPair(ball1, new Sprite(10, 10));
        table.addPair(ball2, new Sprite(15, 10));
        // Устанавливаем скорости
        ball1.setSpeed(vector1);
        ball2.setSpeed(vector2);
        Bouncing clone1 = ball1.clone();
        Bouncing clone2 = ball2.clone();
        ball1.handleCollision(new Point(),vector2, clone2);
        assertEquals(ball1.speed(), vector2);
        assertEquals(ball1.position(), position1);
        ball2.handleCollision(new Point(), vector1, clone1);
        assertEquals(ball2.speed(), vector1);
        assertEquals(ball1.position(), position2);
    }
    
    /**
     * Тестирование отскока отскакивающиго от другого отскакивающего
     */
    @Test
    public void testHandleCollisionBouncingWithAnotherBouncing() {
        System.out.println("Тестирование отскока отскакивающиго от другого отскакивающего");
        Bouncing ball1 = new Bouncing(table);
        Bouncing ball2 = new Bouncing(table);
        ball1.setWeight(1);
        ball2.setWeight(2);
        Point position1 = new Point(10, 10);
        Point position2 = new Point(15, 10);
        table.addPair(ball1, new Sprite(10, 10));
        table.addPair(ball2, new Sprite(15, 10));
        SpeedVector vector1 = new SpeedVector(2, -2);
        SpeedVector vector2 = new SpeedVector(2, 5);
        // Устанавливаем скорости
        ball1.setSpeed(vector1);
        ball2.setSpeed(vector2);
        Bouncing clone1 = ball1.clone();
        Bouncing clone2 = ball2.clone();
        ball1.handleCollision(new Point(),vector2, clone2);
        assertEquals(ball1.speed(), ball1.countSpeed(ball2).get(0));
        assertEquals(ball1.position(), position1);
        ball2.handleCollision(new Point(), vector1, clone1);
        assertEquals(ball2.speed(), ball1.countSpeed(ball2).get(1));
        assertEquals(ball2.position(), position2);
    }
    
    // Тесты на отражение
    /**
     * Тест для тестирования обработки столкновения мяча с ракеткой в покое.
     */
    @Test
    public void testHandleCollisionBouncingWithBouncedAxisX() {
        System.out.println("Тест для тестирования обработки столкновения мяча с ракеткойв покое");
        Racket racket = new Racket(table);
        Bouncing ball = new Bouncing(table);
        //Установить позиции
        Point position = new Point(15, 10);
        table.addPair(racket, new Sprite(10, 10));
        table.addPair(ball, new Sprite(15, 10));
        // Устанавливаем скорости
        SpeedVector vector1 = new SpeedVector();
        SpeedVector vector2 = new SpeedVector(10,-5);
        racket.setSpeed(vector1);
        ball.setSpeed(vector2);
        ball.handleCollision(Axis.X, position);
        assertEquals(ball.speed(), vector1.reflect(Axis.X));
        assertEquals(ball.position(), position);
    }
    
    /**
     * Тест для тестирования обработки столкновения мяча с боковой стороной кирпича.
     */
    @Test
    public void testHandleCollisionBouncingWithBouncedAxisY() {
        System.out.println("Тест для тестирования обработки столкновения мяча с боковой стороной кирпича");
        DestroyableBrick brick = new DestroyableBrick(table);
        Bouncing ball = new Bouncing(table);
        ball.setWeight(1);
        //Установить позиции
        Point position = new Point(30, 15);
        table.addPair(brick, new Sprite(10, 10));
        table.addPair(ball, new Sprite(30, 15));
        // Устанавливаем скорости
        SpeedVector vector1 = new SpeedVector(-5,10);
        ball.setSpeed(vector1);
        ball.handleCollision(Axis.Y, position);
        assertEquals(ball.speed(), vector1.reflect(Axis.Y));
        assertEquals(ball.position(), position);
    }
    
    /**
     * Тест для тестирования обработки столкновения мяча с углом кирпича.
     */
    @Test
    public void testHandleCollisionBouncingWithConerBounced() {
        System.out.println("Тест для тестирования обработки столкновения мяча с углом кирпича");
        DestroyableBrick brick = new DestroyableBrick(table);
        Bouncing ball = new Bouncing(table);
        ball.setWeight(1);
        //Установить позиции
        table.addPair(brick, new Sprite(10, 10));
        table.addPair(ball, new Sprite(30, 20));
        Point position = new Point(30, 10);
        // Устанавливаем скорости
        SpeedVector vector1 = new SpeedVector(-5,10);
        ball.setSpeed(vector1);
        ball.handleCollision(Axis.X, position);
        assertEquals(ball.speed(), vector1.reflect(Axis.X));
        assertEquals(ball.position(), position);
    }
   
}
