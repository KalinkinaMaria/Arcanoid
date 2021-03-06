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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
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
    public void testHandleCollisionBoucingWithMovingBounced() throws IOException {
        System.out.println("Тестирование столкновения отскакивающего с тем от которого можно отскочить в движении");
        Racket racket = new Racket(table);
        Bouncing ball = new Bouncing(table);
        BufferedImage imageBall = ImageIO.read(new File("img/ball.png"));
        Sprite spriteBall = new Sprite(imageBall, 15, 10);
        BufferedImage imageRacket = ImageIO.read(new File("img/r.png"));
        Sprite spriteRacket = new Sprite(imageRacket, 10, 10);
        SpeedVector vector1 = new SpeedVector(3, 0);
        SpeedVector vector2 = new SpeedVector(10,-5);
        //Установить позиции
        Point position = new Point(15, 10);
        table.addPair(racket, spriteRacket);
        table.addPair(ball, spriteBall);
        // Устанавливаем скорости
        racket.setSpeed(vector1);
        ball.setSpeed(vector2);
        ball.handleManagableCollision(racket);
        assertTrue(ball.speed().equals( new SpeedVector(-9.07, -6.54)));
        assertEquals(ball.position(), position);
    }
    
    /**
     * Тестирование столкновения отскакивающего с углом того от которого можно отскочить в движении
     */
    @Test
    public void testHandleCollisionBoucingWithConerMovingBounced() throws IOException {
        System.out.println("Тестирование столкновения отскакивающего с углом того от которого можно отскочить в движении");
        Racket racket = new Racket(table);
        Bouncing ball = new Ball(table);
        ball.setWeight(1);
        BufferedImage imageBall = ImageIO.read(new File("img/ball.png"));
        Sprite spriteBall = new Sprite(imageBall, 297, 550);
        BufferedImage imageRacket = ImageIO.read(new File("img/r.png"));
        Sprite spriteRacket = new Sprite(imageRacket, 119, 575);
        SpeedVector vector1 = new SpeedVector(-0.28, 0.1);
        SpeedVector vector2 = new SpeedVector(3,0);
        //Установить позиции
        Point position = new Point(297, 550);
        table.addPair(racket, spriteRacket);
        table.addPair(ball, spriteBall);
        // Устанавливаем скорости
        ball.setSpeed(vector1);
        racket.setSpeed(vector2);
        ball.handleManagableCollision(racket);
        assertEquals(ball.speed(), vector1.reflect(SpeedVector.Axis.Z));
        assertEquals(ball.position(), position);
    }
    
    /**
     *Тестирование отскока отскакивающиго от того же отскакивающего
     */
    @Test
    public void testHandleCollisionBouncingWithSameBouncing() throws IOException {
        System.out.println("Тестирование отскока отскакивающиго от того же отскакивающего");
        Bouncing ball1 = new Bouncing(table);
        Bouncing ball2 = new Bouncing(table);
        ball1.setWeight(1);
        ball2.setWeight(1);
        SpeedVector vector1 = new SpeedVector(2, -2);
        SpeedVector vector2 = new SpeedVector(2, 5);
        Point position1 = new Point(297, 550);
        Point position2 = new Point(119, 575);
        BufferedImage imageBall = ImageIO.read(new File("img/ball.png"));
        Sprite spriteBall1 = new Sprite(imageBall, 297, 550);
        Sprite spriteBall2 = new Sprite(imageBall, 119, 575);
        table.addPair(ball1, spriteBall1);
        table.addPair(ball2, spriteBall2);
        // Устанавливаем скорости
        ball1.setSpeed(vector1);
        ball2.setSpeed(vector2);
        Bouncing clone1 = ball1.clone();
        Bouncing clone2 = ball2.clone();
        ball1.handleCollision(0, clone2);
        assertEquals(ball1.speed(), vector2);
        assertEquals(ball1.position(), position1);
        ball2.handleCollision(0, clone1);
        assertEquals(ball2.speed(), vector1);
        assertEquals(ball2.position(), position2);
    }
    
    /**
     * Тестирование отскока отскакивающиго от другого отскакивающего
     */
    @Test
    public void testHandleCollisionBouncingWithAnotherBouncing() throws IOException {
        System.out.println("Тестирование отскока отскакивающиго от другого отскакивающего");
        Bouncing ball1 = new Bouncing(table);
        Bouncing ball2 = new Bouncing(table);
        ball1.setWeight(1);
        ball2.setWeight(2);
        Point position1 = new Point(297, 550);
        Point position2 = new Point(119, 575);
        BufferedImage imageBall = ImageIO.read(new File("img/ball.png"));
        Sprite spriteBall1 = new Sprite(imageBall, 297, 550);
        Sprite spriteBall2 = new Sprite(imageBall, 119, 575);
        table.addPair(ball1, spriteBall1);
        table.addPair(ball2, spriteBall2);
        SpeedVector vector1 = new SpeedVector(2, -2);
        SpeedVector vector2 = new SpeedVector(2, 5);
        // Устанавливаем скорости
        ball1.setSpeed(vector1);
        ball2.setSpeed(vector2);
        Bouncing clone1 = ball1.clone();
        Bouncing clone2 = ball2.clone();
        SpeedVector newSpeedball1 = ball1.countSpeed(ball2).get(0);
        SpeedVector newSpeedball2 = ball1.countSpeed(ball2).get(1);
        ball1.handleCollision(0, clone2);
        assertEquals(ball1.speed(), newSpeedball1);
        assertEquals(ball1.position(), position1);
        ball2.handleCollision(0, clone1);
        assertEquals(ball2.speed(), newSpeedball2);
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
        ball.handleCollision(SpeedVector.Axis.X, position);
        assertEquals(ball.speed(), vector2.reflect(SpeedVector.Axis.X));
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
        ball.handleCollision(SpeedVector.Axis.Y, position);
        assertEquals(ball.speed(), vector1.reflect(SpeedVector.Axis.Y));
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
        Point position = new Point(30, 20);
        // Устанавливаем скорости
        SpeedVector vector1 = new SpeedVector(-5,10);
        ball.setSpeed(vector1);
        ball.handleCollision(SpeedVector.Axis.X, position);
        assertEquals(ball.speed(), vector1.reflect(SpeedVector.Axis.X));
        assertEquals(ball.position(), position);
    }
   
}
