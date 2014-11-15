/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.collision;

import arcanoid.events.SpritesCollidedListener;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Sprite;
import java.awt.Dimension;
import java.nio.Buffer;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
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
        game.setup(new BallWithRacketCollision(table), new Dimension(800,600), false);
        game.start();
    }
    
    /**
     * Тест на столкновение мяча и угла ракетки
     */
    @Test
    public void testCollidedBallWithCornerRacket() {
        System.out.println(" Тест на столкновение мяча и угла ракетки");
        GameLoader game = new GameLoader();
        game.setup(new RacketCollisions(table), new Dimension(800,600), false);
        game.start();
    }
    
    /**
     * Тест на столкновение мяча и кирпича
     */
    @Test
    public void testCollidedBallWithBrick() {
        System.out.println("Тест на столкновение мяча и кирпича");
        GameLoader game = new GameLoader();
        game.setup(new BallWithBricks(table, 1), new Dimension(800,600), false);
        game.start();
    }
    
    /**
     * Тест на столкновение мяча и двух кирпичей на одной линии
     */
    @Test
    public void testCollidedBallWithTwoHorizontalBrick() {
        System.out.println("Тест на столкновение мяча и двух кирпичей на одной линии");
        GameLoader game = new GameLoader();
        game.setup(new BallWithBricks(table, 0), new Dimension(800,600), false);
        game.start();
    }
    
    /**
     * Тест на столкновение мяча и двух кирпичей в углу
     */
    @Test
    public void testCollidedBallWithTwoCornerBrick() {
        System.out.println("Тест на столкновение мяча и двух кирпичей в углу");
        GameLoader game = new GameLoader();
        game.setup(new BallWithBricks(table, 4), new Dimension(800,600), false);
        game.start();
    }
    
    /**
     * Тест на столкновение мяча и угла кирпича
     */
    @Test
    public void testCollidedBallWithCornerBrick() {
        System.out.println("Тест на столкновение мяча и угла кирпича");
        GameLoader game = new GameLoader();
        game.setup(new BallWithBricks(table, 2), new Dimension(800,600), false);
        game.start();
    }
    
    /**
     * Тест на столкновение мяча и боковой стороны кирпича
     */
    @Test
    public void testCollidedBallWithSideBrick() {
        System.out.println("Тест на столкновение мяча и боковой стороны кирпича");
        GameLoader game = new GameLoader();
        game.setup(new BallWithBricks(table, 3), new Dimension(800,600), false);
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
        game.setup(BallWithSameBall(table), new Dimension(800,600), false);
        game.start();
    }
    
    /**
     * Тест на столкновение мяча и другого мяча
     */
    @Test
    public void testCollidedBallWithAnotherBall() {
        System.out.println("Тест на столкновение мяча и другого мяча");
        GameLoader game = new GameLoader();
        game.setup(new BallWithAnotherBall(table), new Dimension(800,600), false);
        game.start();
    }
    
    /**
     * Тест на столкновение трех мячей
     */
    @Test
    public void testCollidedTreeBalls() {
        System.out.println("Тест на столкновение трех мячей");
        GameLoader game = new GameLoader();
        game.setup(new BallsCollision(table), new Dimension(800,600), false);
        game.start();
    }
    
}
