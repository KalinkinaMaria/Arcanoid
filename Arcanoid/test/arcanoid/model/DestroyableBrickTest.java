/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.model;

import arcanoid.service.Buffer;
import arcanoid.service.SpeedVector;
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
public class DestroyableBrickTest {
    
    public DestroyableBrickTest() {
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
     * Тестирование изменения прочности кирпича, delta положительна
     */
    @Test
    public void testChangePositiveDelta() {
        DestroyableBrick brick = new DestroyableBrick(new Buffer());
        brick.setStrength(10);
        brick.changeStrength(4);
        assertEquals(brick.strength(), 14);
    }
    /**
     * Тестирование изменения прочности кирпича, delta отрицательна
     */
    @Test
    public void testChangeNegativeDelta() {
        DestroyableBrick brick = new DestroyableBrick(new Buffer());
        brick.setStrength(10);
        brick.changeStrength(-4);
        assertEquals(brick.strength(), 6);
    }
    /**
     * Тестирование изменения прочности кирпича, delta ноль
     */
    @Test
    public void testChangeNullDelta() {
        DestroyableBrick brick = new DestroyableBrick(new Buffer());
        brick.setStrength(10);
        brick.changeStrength(0);
        assertEquals(brick.strength(), 10);
    }
}
