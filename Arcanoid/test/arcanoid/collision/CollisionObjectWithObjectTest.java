/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.collision;

import arcanoid.service.Buffer;
import arcanoid.service.SpeedVector;
import com.golden.gamedev.GameLoader;
import java.awt.Dimension;
import java.awt.Point;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Мария
 */
public class CollisionObjectWithObjectTest {
    private Buffer table;
    
    public CollisionObjectWithObjectTest() {
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
     * Тест на столкновение мяча и ракетки
     */
    @Test
    public void testCollidedBallWithRacket() {
        System.out.println("Тест на столкновение мяча и ракетки");
        GameLoader game = new GameLoader();
        RacketCollisions test = new RacketCollisions(table);
        test.setFirstSpeed(new SpeedVector(0, 0.3));
        test.setSecondSpeed(new SpeedVector());
        test.setFirstPoint(new Point(280, 480));
        test.setSecondPoint(new Point(220, 525));
        game.setup(test, new Dimension(800,600), false);
        game.start();
    }
    
    /**
     * Тест на столкновение мяча и угла ракетки
     */
    @Test
    public void testCollidedBallWithCornerRacket() {
        System.out.println(" Тест на столкновение мяча и угла ракетки");
        GameLoader game = new GameLoader();
        RacketCollisions test = new RacketCollisions(table);
        test.setFirstSpeed(new SpeedVector(-0.5, 1));
        test.setSecondSpeed(new SpeedVector());
        test.setFirstPoint(new Point(400, 500));
        test.setSecondPoint(new Point(220, 525));
        game.setup(test, new Dimension(800,600), false);
        game.start();
    }
    
    /**
     * Тест на столкновение мяча и кирпича
     */
    @Test
    public void testCollidedBallWithBrick() {
        System.out.println("Тест на столкновение мяча и кирпича");
        GameLoader game = new GameLoader();
        BallWithBricks test = new BallWithBricks(table);
        test.setSpeed(new SpeedVector(0.5, -1));
        test.setFirstPoint(new Point(400, 500));
        test.setSecondPoint(new Point(360, 470));
        test.setThirdPoint(null);
        game.setup(test, new Dimension(800,600), false);
        game.start();
    }
    
    /**
     * Тест на столкновение мяча и двух кирпичей на одной линии
     */
    @Test
    public void testCollidedBallWithTwoHorizontalBrick() {
        System.out.println("Тест на столкновение мяча и двух кирпичей на одной линии");
        GameLoader game = new GameLoader();
        BallWithBricks test = new BallWithBricks(table);
        test.setSpeed(new SpeedVector(0.5, -1));
        test.setFirstPoint(new Point(400, 500));
        test.setSecondPoint(new Point(333, 470));
        test.setThirdPoint(new Point(413, 470));
        game.setup(test, new Dimension(800,600), false);
        game.start();
    }
    
    /**
     * Тест на столкновение мяча и двух кирпичей в углу
     */
    @Test
    public void testCollidedBallWithTwoCornerBrick() {
        System.out.println("Тест на столкновение мяча и двух кирпичей в углу");
        GameLoader game = new GameLoader();
        BallWithBricks test = new BallWithBricks(table);
        test.setSpeed(new SpeedVector(-0.5, 1));
        test.setFirstPoint(new Point(400, 500));
        test.setSecondPoint(new Point(320, 500));
        test.setThirdPoint(new Point(400, 470));
        game.setup(test, new Dimension(800,600), false);

        game.start();
    }
    
    /**
     * Тест мяч пролетает между кирпичами
     */
    @Test
    public void testBallBetweenTwoBrick() {
        System.out.println("Тест мяч пролетает между кирпичами");
        GameLoader game = new GameLoader();
        BallWithBricks test = new BallWithBricks(table);
        test.setSpeed(new SpeedVector(-0.5, 0));
        test.setFirstPoint(new Point(400, 531));
        test.setSecondPoint(new Point(320, 500));
        test.setThirdPoint(new Point(320, 556));
        game.setup(test, new Dimension(800,600), false);
        game.start();
    }
    
    /**
     * Тест на столкновение мяча и двух кирпичей c расстоянием
     */
    @Test
    public void testCollidedBallWithTwoBrickWithDistance() {
        System.out.println("Тест на столкновение мяча и двух кирпичей c расстоянием");
        GameLoader game = new GameLoader();
        BallWithBricks test = new BallWithBricks(table);
        test.setSpeed(new SpeedVector(-0.5, 0));
        test.setFirstPoint(new Point(400, 520));
        test.setSecondPoint(new Point(320, 500));
        test.setThirdPoint(new Point(320, 540));
        game.setup(test, new Dimension(800,600), false);
        game.start();
    }
    
    /**
     * Тест на столкновение мяча и угла кирпича
     */
    @Test
    public void testCollidedBallWithCornerBrick() {
        System.out.println("Тест на столкновение мяча и угла кирпича");
        GameLoader game = new GameLoader();
        BallWithBricks test = new BallWithBricks(table);
        test.setSpeed(new SpeedVector(0.5, -1));
        test.setFirstPoint(new Point(400, 500));
        test.setSecondPoint(new Point(425, 470));
        test.setThirdPoint(null);
        game.setup(test, new Dimension(800,600), false);
        game.start();
    }
    
    /**
     * Тест на столкновение мяча и боковой стороны кирпича
     */
    @Test
    public void testCollidedBallWithSideBrick() {
        System.out.println("Тест на столкновение мяча и боковой стороны кирпича");
        GameLoader game = new GameLoader();
        BallWithBricks test = new BallWithBricks(table);
        test.setSpeed(new SpeedVector(-0.5, 1));
        test.setFirstPoint(new Point(400, 500));
        test.setSecondPoint(new Point(320, 500));
        test.setThirdPoint(null);
        game.setup(test, new Dimension(800,600), false);
        game.start();
    }
    
    /**
     * Тест на столкновение мяча и роя
     */
    @Test
    public void testCollidedBallWithSwarm() {
        System.out.println("Тест на столкновение мяча и роя");
        GameLoader game = new GameLoader();
        game.setup(new BallWithSwarm(table), new Dimension(800,600), false);
        game.start();
    }
    
    /**
     * Тест на столкновение мяча и такого же мяча
     */
    @Test
    public void testCollidedBallWithSameBall() {
        System.out.println("Тест на столкновение мяча и такого же мяча");
        GameLoader game = new GameLoader();
        BallsCollision test = new BallsCollision(table);
        test.setFirstSpeed(new SpeedVector(0.5, 1));
        test.setSecondSpeed(new SpeedVector(-0.4, 0.6));
        test.setThirdSpeed(null);
        test.setFirstPoint(new Point(400, 500));
        test.setSecondPoint(new Point(425, 500));
        test.setThirdPoint(null);
        test.setFirstWeight(1);
        test.setSecondWeight(1);
        test.setThirdWeight(0);
        game.setup(test, new Dimension(800,600), false);
        game.start();
    }
    
    /**
     * Тест на столкновение мяча и другого мяча
     */
    @Test
    public void testCollidedBallWithAnotherBall() {
        System.out.println("Тест на столкновение мяча и другого мяча");
        GameLoader game = new GameLoader();
        
        BallsCollision test = new BallsCollision(table);
        test.setFirstSpeed(new SpeedVector(0.5, 1));
        test.setSecondSpeed(new SpeedVector(-0.4, 0.6));
        test.setThirdSpeed(null);
        test.setFirstPoint(new Point(400, 500));
        test.setSecondPoint(new Point(425, 500));
        test.setThirdPoint(null);
        test.setFirstWeight(1);
        test.setSecondWeight(3);
        test.setThirdWeight(0);
        game.setup(test, new Dimension(800,600), false);
        game.start();
    }
    
    /**
     * Тест на столкновение трех мячей
     */
    @Test
    public void testCollidedTreeBalls() {
        System.out.println("Тест на столкновение трех мячей");
        GameLoader game = new GameLoader();
        
        
        BallsCollision test = new BallsCollision(table);
        test.setFirstSpeed(new SpeedVector(0.5, 1));
        test.setSecondSpeed(new SpeedVector(-0.4, 0.6));
        test.setThirdSpeed(new SpeedVector(0, 0.7));
        test.setFirstPoint(new Point(400, 500));
        test.setSecondPoint(new Point(425, 500));
        test.setThirdPoint(new Point(413, 475));
        test.setFirstWeight(1);
        test.setSecondWeight(1);
        test.setThirdWeight(1);
        game.setup(test, new Dimension(800,600), false);
        game.start();
    }
    
}
