/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.model;

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
 * @author Мария
 */
public class RacketTest {
    
    public RacketTest() {
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
     * Тест толкания мяча
     */
    @Test
    public void testPushBall() {
        Buffer table = new Buffer();
        
        Racket racket = new Racket(table);
        Ball ball = new Ball(table);
        
        table.addPair(racket, new Sprite(10, 10));
        table.addPair(ball, new Sprite(70, 30));
        
        ball.setWeight(2);
        racket.pushBall(ball);
        //Нужно где то завести какую то константу, которая будет хранить начальный вектор скорости
        assertEquals(ball.speed(), new SpeedVector(0,5));
        
    }
}
