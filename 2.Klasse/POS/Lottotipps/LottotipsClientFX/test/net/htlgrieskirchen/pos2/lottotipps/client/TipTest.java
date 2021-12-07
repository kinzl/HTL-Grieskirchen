/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos2.lottotipps.client;

import java.util.Arrays;
import java.util.List;
import net.htlgrieskirchen.pos2.util.GradingUtil;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Torsten Welsch
 */
public class TipTest {
    
    private final List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
    
    public TipTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
        GradingUtil.computeGrading();
    }
    
    @Before
    public void setUp() {
        GradingUtil.increaseMaxPoints();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getNumbers method, of class Tip.
     */
    @Test
    public void testGetNumbers() {
        System.out.println("Tip::getNumbers");
        
        Tip instance = new Tip(numbers);
        
        List<Integer> expected = numbers;
        List<Integer> result = instance.getNumbers();

        assertEquals(expected, result);
        
        GradingUtil.increaseAchievedPoints();
    }

    /**
     * Test of toString method, of class Tip.
     */
    @Test
    public void testToString() {
        System.out.println("Tip::toString");
        
        Tip instance = new Tip(numbers);
        
        String expected = "1,2,3,4,5,6";
        String result = instance.toString();

        assertEquals(expected, result);
        
        GradingUtil.increaseAchievedPoints();
    }

    /**
     * Test of fromString method, of class Tip.
     */
    @Test
    public void testFromString() {
        System.out.println("Tip::fromString");
        
        String expected = "1,2,3,4,5,6";
        Tip result = Tip.fromString(expected);
        
        assertEquals(expected, result.toString());

        expected = "47,11,4,7,1,1";
        result = Tip.fromString(expected);
        
        assertEquals(expected, result.toString());
        
        GradingUtil.increaseAchievedPoints();
    }
    
}
