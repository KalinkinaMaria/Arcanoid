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
public class CollisionObjectWithObjectTest {
    
    public CollisionObjectWithObjectTest() {
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
     * Test of addSpritesCollidedListener method, of class CollisionObjectWithObject.
     */
    @Test
    public void testAddSpritesCollidedListener() {
        System.out.println("addSpritesCollidedListener");
        SpritesCollidedListener element = null;
        CollisionObjectWithObject instance = new CollisionObjectWithObject();
        instance.addSpritesCollidedListener(element);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fireSpritesCollided method, of class CollisionObjectWithObject.
     */
    @Test
    public void testFireSpritesCollided() {
        System.out.println("fireSpritesCollided");
        CollisionObjectWithObject instance = new CollisionObjectWithObject();
        instance.fireSpritesCollided();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of collided method, of class CollisionObjectWithObject.
     */
    @Test
    public void testCollided() {
        System.out.println("collided");
        Sprite sprite = null;
        Sprite sprite1 = null;
        CollisionObjectWithObject instance = new CollisionObjectWithObject();
        instance.collided(sprite, sprite1);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
