/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.collision;

import arcanoid.events.SpritesCollidedListener;
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
public class CollisionObjectWithBoundaryTest {
    
    public CollisionObjectWithBoundaryTest() {
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
     * Test of addSpritesCollidedListener method, of class CollisionObjectWithBoundary.
     */
    @Test
    public void testAddSpritesCollidedListener() {
        System.out.println("addSpritesCollidedListener");
        SpritesCollidedListener element = null;
        CollisionObjectWithBoundary instance = null;
        instance.addSpritesCollidedListener(element);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of collided method, of class CollisionObjectWithBoundary.
     */
    @Test
    public void testCollided() {
        System.out.println("collided");
        Sprite sprite = null;
        CollisionObjectWithBoundary instance = null;
        instance.collided(sprite);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}