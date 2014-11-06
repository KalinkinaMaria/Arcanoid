/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.service;

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
public class SpeedVectorTest {
    
    public SpeedVectorTest() {
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
     * Тест сумирования нулевого вектора и ненулевого
     */
    @Test
    public void testSumNullOrNotNull() {
        SpeedVector nullVector = new SpeedVector(0, 0);
        SpeedVector notNullVector = new SpeedVector(15, 10);
        SpeedVector result = new SpeedVector(15, 10);
        SpeedVector expResult = notNullVector.sum(nullVector);
        assertEquals(expResult, result);
    }
    
    /**
     * Тест сумирования двух произвольных векторов
     */
    @Test
    public void testSumTwoVectors() {
        SpeedVector vector1 = new SpeedVector(2, 1);
        SpeedVector vector2 = new SpeedVector(5, 3);
        SpeedVector result = new SpeedVector(7, 4);
        SpeedVector expResult = vector1.sum(vector2);
        assertEquals(expResult, result);
    }
    
    /**
     * Тест сумирования одинаковых векторов
     */
    @Test
    public void testSumSameVectors() {
        SpeedVector vector1 = new SpeedVector(2, 1);
        SpeedVector vector2 = new SpeedVector(2, 1);
        SpeedVector result = new SpeedVector(4, 2);
        SpeedVector expResult = vector1.sum(vector2);
        assertEquals(expResult, result);
    }
    
    /**
     * Тест сумирования противоположенных векторов
     */
    @Test
    public void testSumOppositeVectors() {
        SpeedVector vector1 = new SpeedVector(2, 1);
        SpeedVector vector2 = new SpeedVector(-2, -1);
        SpeedVector result = new SpeedVector(0, 0);
        SpeedVector expResult = vector1.sum(vector2);
        assertEquals(expResult, result);
    }
    
    /**
     * Тест отражение вектора с осью X
     */
    @Test
    public void testReflectVectorX() {
        SpeedVector vector1 = new SpeedVector(2, 1);
        SpeedVector result = new SpeedVector(2, -1);
        SpeedVector expResult = vector1.reflect(Axis.X);
        assertEquals(expResult, result);
    }
    
    /**
     * Тест отражение вектора с осью Y
     */
    @Test
    public void testReflectVectorY() {
        SpeedVector vector1 = new SpeedVector(2, 1);
        SpeedVector result = new SpeedVector(-2, 1);
        SpeedVector expResult = vector1.reflect(Axis.Y);
        assertEquals(expResult, result);
    }
    
    /**
     * Тест отражение нулевого вектора
     */
    @Test
    public void testReflectNullVector() {
        SpeedVector vector1 = new SpeedVector(0, 0);
        SpeedVector result = new SpeedVector(0, 0);
        SpeedVector expResult = vector1.reflect(Axis.Y);
        assertEquals(expResult, result);
    }
    
}
