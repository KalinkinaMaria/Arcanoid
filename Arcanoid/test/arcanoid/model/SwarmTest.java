/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.model;

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
public class SwarmTest {
    
    public SwarmTest() {
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
     * Тестирование прикрипления элемента к рою, проверка веса
     */
    @Test
    public void testAddElementWeight() {
        Swarm swarm = new Swarm(new Buffer(), 1);
        Swarn swarmOrigin = swarm.copy();
        swarm.setCriticalWeight(4);
        SwarmElement element = new SwarmElement(new Buffer(), 3);
        swarm.addElement(element);
        assertEquals(swarm.weight(), swarmOrigin.weight() + element.weight());
    }
}
