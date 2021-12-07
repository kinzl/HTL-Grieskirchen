/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos2.lottotipps.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import net.htlgrieskirchen.pos2.util.ClientUtil;
import net.htlgrieskirchen.pos2.util.GradingUtil;
import net.htlgrieskirchen.pos2.util.TestUtil;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Torsten Welsch
 */
public class ModelTest {

    public ModelTest() {
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
        ClientUtil.resetFiles();
    }

    @Test
    public void testGetTips() {
        System.out.println("Model::getTips");
        
        Model instance = new Model();
        
        List<Tip> expected = new ArrayList<>();
        List<Tip> result = instance.getTips();
        
        assertEquals(expected, result);
        
        GradingUtil.increaseAchievedPoints();
    }
    
    @Test
    public void testRequestTipsFromServer() {
        System.out.println("Model::requestTipsFromServer");
        
        Model instance = new Model();
        String address = "localhost";
        int numberOfTips = 11;
        
        for (int i = 1; i < 3; i++) { // List::clear?
            instance.requestTipsFromServer(address, numberOfTips);
            List<Tip> result = instance.getTips();
            assertEquals("wrong number of tips in list (" + i + ". run)", numberOfTips, result.size());
            for (Tip tip : result) {
                assertTrue("null tip found", tip != null);
                assertEquals("more or less than 6 numbers in tip", 6, tip.getNumbers().size());
            }
        }
        
        GradingUtil.increaseAchievedPoints();
    }
    
    @Test
    public void testSaveTips() {
        System.out.println("Model::saveTips");

        Model instance = new Model();
        String address = "localhost";
        int numberOfTips = 47;
        
        instance.requestTipsFromServer(address, numberOfTips);
        instance.saveTips();
        
        File file = new File("tips.csv");
        assertTrue("file tips.csv has not been written", file.exists());        
        int counter = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            while (line != null) {
                assertEquals("more or less than 6 numbers in tip in line " + line, 
                        6, line.split(",").length);
                line = br.readLine();
                counter++;
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertEquals("wrong number of tips in tips.csv", numberOfTips, counter);

        GradingUtil.increaseAchievedPoints();
    }
    
    @Test
    public void testLoadTips() {
        System.out.println("Model::loadTips");
        
        Model instance = new Model();
        String address = "localhost";
        int numberOfTips = 11;
        
        instance.requestTipsFromServer(address, numberOfTips);
        instance.saveTips();
        List<Tip> expected = instance.getTips();
        instance.loadTips();
        List<Tip> result = instance.getTips();
        
        TestUtil.assertOrderEquals("loaded tips are not equal to saved tips", 
                expected, result);
        
        GradingUtil.increaseAchievedPoints();
    }
}
