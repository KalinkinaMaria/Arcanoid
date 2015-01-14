/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.service;

import arcanoid.model.Ball;
import arcanoid.model.FieldElement;
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
public class ImpulseOfStrikeForceTest {
    
    public ImpulseOfStrikeForceTest() {
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
     * Тест нахождения импульса элемента
     */
    @Test
    public void testCount() {
        ImpulseOfStrikeForce impulse = new ImpulseOfStrikeForce();
        Buffer table = new Buffer();
        FieldElement element;
        element = new Ball(table);
        element.setWeight(5.5);
        table.addPair(element, new Sprite(10, 10));
        element.setSpeed(new SpeedVector(3,4));
        double expResult = impulse.count(element);
        double result = 27.5;
        assertEquals(expResult, result, 0.0);
    }
    
}
