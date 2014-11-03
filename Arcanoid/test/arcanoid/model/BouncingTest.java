/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.model;

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
    
    public BouncingTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
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
        // Устанавливаем скорости
        racket.setSpeed(new SpeedVector());
        ball.setSpeed(new SpeedVector(10,-5));
        ball.handleCollision(racket);
        assertEquals(ball.speed(), new SpeedVector(10,5));
    }
}
