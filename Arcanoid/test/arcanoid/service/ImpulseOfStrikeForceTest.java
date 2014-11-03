/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.service;

import arcanoid.model.FieldElement;
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
        element.setSpeed(new SpeedVector(3,4));
        double expResult = impulse.count(element);
        double result = 27.5;
        assertEquals(expResult, result, 0.0);
    }
    
    /**
     * Test of countImpulsrOfStrikeForce method, of class ImpulseOfStrikeForce.
     */
    @Test
    public void testCountImpulsrOfStrikeForce() {
        System.out.println("countImpulsrOfStrikeForce");
        FieldElement element = null;
        double expResult = 0.0;
        double result = ImpulseOfStrikeForce.count(element);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
