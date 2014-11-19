/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.service;

import arcanoid.model.FieldElement;
import arcanoid.service.SpeedVector.Axis;
import com.golden.gamedev.object.Sprite;
import java.util.ArrayList;
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
    
    /**
     * Тест изменения скоростей при столкновении двух отскакивающих с одинаковой массой
     */
    @Test
    public void testCountSpeedSameWeight() {
        Buffer table = new Buffer();
        FieldElement element1 = new FieldElement(table);
        FieldElement element2 = new FieldElement(table);
        // Устанавливаем вес
        element1.setWeight(1);
        element2.setWeight(1);
        
        table.addPair(element1, new Sprite(10, 10));
        table.addPair(element1, new Sprite(15, 10));
        //Устанавливаем скорость
        SpeedVector vector1 = new SpeedVector(0.5, 1);
        SpeedVector vector2 = new SpeedVector(-0.3, 1);
        element1.setSpeed(vector1);
        element2.setSpeed(vector2);
        ArrayList<SpeedVector> result = new ArrayList<>();
        result.add(vector2);
        result.add(vector1);
        assertEquals(element1.countSpeed(element2), result);
    }
    
    /**
     * Тест изменения скоростей при столкновении двух отскакивающих с разной массой
     */
    @Test
    public void testCountSpeedAnotherWeight() {
        Buffer table = new Buffer();
        FieldElement element1 = new FieldElement(table);
        FieldElement element2 = new FieldElement(table);
        // Устанавливаем вес
        element1.setWeight(1);
        element2.setWeight(3);
        
        table.addPair(element1, new Sprite(10, 10));
        table.addPair(element1, new Sprite(15, 10));
        //Устанавливаем скорость
        SpeedVector vector1 = new SpeedVector(0.5, 1);
        SpeedVector vector2 = new SpeedVector(-0.3, 1);
        element1.setSpeed(vector1);
        element2.setSpeed(vector2);
        ArrayList<SpeedVector> result = new ArrayList<>();
        result.add(new SpeedVector(-0.575, 1));
        result.add(new SpeedVector(0.1, 1));
        assertEquals(element1.countSpeed(element2), result);
    }
    
}
