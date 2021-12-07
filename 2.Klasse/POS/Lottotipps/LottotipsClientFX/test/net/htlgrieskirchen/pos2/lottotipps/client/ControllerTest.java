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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import net.htlgrieskirchen.pos2.util.GradingUtil;
import net.htlgrieskirchen.pos2.util.JavaFXUtil;
import net.htlgrieskirchen.pos2.util.TestUtil;
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
public class ControllerTest {
    
    public ControllerTest() {
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

    @Test
    public void testObjectVariables() {
        System.out.println("Controller.objectVariables");
        
        JavaFXUtil.getField("textFieldAddress", TextField.class);
        JavaFXUtil.getField("comboBoxNumberOfTips", ComboBox.class);
        JavaFXUtil.getField("listViewTips", ListView.class);
        JavaFXUtil.getField("model", Model.class);
                
        assertNotNull(JavaFXUtil.getField("model", Model.class));
        
        GradingUtil.increaseAchievedPoints();
    }
    
    @Test
    public void testObjectMethods() {
        System.out.println("Controller.objectMethods");
        
        JavaFXUtil.getMethod("handleMenuItemLoad");
        JavaFXUtil.getMethod("handleMenuItemSave");
        JavaFXUtil.getMethod("handleMenuItemQuit");
        JavaFXUtil.getMethod("handleComboBoxAction");
     
        GradingUtil.increaseAchievedPoints();
    }
    
    @Test
    public void testHandleMenuItemLoad() {
        System.out.println("Controller::handleMenuItemLoad");
        
        Model instance = new Model();
        String address = "localhost";
        int numberOfTips = 47;
        
        instance.requestTipsFromServer(address, numberOfTips);
        instance.saveTips();
        List<Tip> expected = instance.getTips();
        JavaFXUtil.processEvent("handleMenuItemLoad");
        List<Tip> result = instance.getTips();
        
        TestUtil.assertOrderEquals("loaded tips are not equal to saved tips", 
                expected, result);
        
        GradingUtil.increaseAchievedPoints();
    }
    
    @Test
    public void testHandleMenuItemSave() {
        System.out.println("Controller::handleMenuItemSave");
        
        Model instance = JavaFXUtil.getField("model", Model.class);
        String address = "localhost";
        int numberOfTips = 11;
        
        instance.requestTipsFromServer(address, numberOfTips);
        JavaFXUtil.processEvent("handleMenuItemSave");
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
    public void testHandleComboBoxAction() {
        System.out.println("Controller::handleComboBoxAction");
        
        String address = "localhost";
        int numberOfTips = 7;
        
        TextField textFieldAddress = JavaFXUtil.getField("textFieldAddress", TextField.class);
        textFieldAddress.setText(address);
        ComboBox comboBoxNumberOfTips = JavaFXUtil.getField("comboBoxNumberOfTips", ComboBox.class);
        comboBoxNumberOfTips.setValue(numberOfTips);
        JavaFXUtil.processEvent("handleComboBoxAction");
        ListView<Tip> listViewTips = JavaFXUtil.getField("listViewTips", ListView.class);
        List<Tip> result = new ArrayList<>(listViewTips.getItems());
        assertEquals("wrong number of tips in list", numberOfTips, result.size());
        for (Tip tip : result) {
            assertTrue("null tip found", tip != null);
            assertEquals("more or less than 6 numbers in tip", 6, tip.getNumbers().size());
        }
        
        GradingUtil.increaseAchievedPoints();
    }

    @Test
    public void testAlertExistance() {
        System.out.println("CHECK ALERTS MANUALLY!");
        
        GradingUtil.increaseAchievedPoints();
    }
    
}
