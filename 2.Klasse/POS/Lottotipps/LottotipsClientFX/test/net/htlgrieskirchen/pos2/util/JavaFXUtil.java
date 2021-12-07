/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos2.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import net.htlgrieskirchen.pos2.lottotipps.client.Client;
import net.htlgrieskirchen.pos2.lottotipps.client.Controller;
import static org.junit.Assert.fail;

/**
 *
 * @author Torsten Welsch
 */
public class JavaFXUtil {
    
    private static final int WAIT_TIME = 100;
    private static Controller controller;

    public static <T> List<T> toList(ObservableList<T> observableList) {
        return observableList.stream().collect(Collectors.toList());
    }
    
    public static <T> T getField(String fieldName, Class<T> fieldType) {
        T result = null;
        
        try {
            Field field = getController().getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            result = (T) accessField(fieldName).get(getController());
        } catch (Exception e) {
            fail("unbale to get field " + fieldName);
        }
        
        return result;
    }
    
    public static <T> void setField(String fieldName, T fieldValue) {
        try {
            accessField(fieldName).set(getController(), fieldValue);
        } catch (Exception e) {
            fail("unbale to set field " + fieldName + " to " + fieldValue.toString());
        }
    }
    
    public static Field accessField(String fieldName) {
        Field result = null;
        
        try {
            result = getController().getClass().getDeclaredField(fieldName);
            result.setAccessible(true);
        } catch (Exception e) {
            fail("unbale to get access to field " + fieldName);
        }
        
        return result;
    }
    
    public static void processEvent(String methodName) {
        try {
            Method method = getMethod(methodName);
            method.invoke(getController(), (Object) null);
        } catch (Exception e) {
            fail("unbale to call method " + methodName);
        }
    }
    
    public static Method getMethod(String methodName) {
        Method result = null;
        
        try {
            result = getController().getClass().getDeclaredMethod(methodName, ActionEvent.class);
            result.setAccessible(true);
        } catch (Exception e) {
            fail("unbale to get access to method " + methodName);
        }
        
        return result;
    }
    
    private static Controller getController() {
        return controller != null ? controller : tryToGetController();
    } 
    
    private static Controller tryToGetController() {
        Controller result = null;
        
        int time = 0;
        new Thread(() -> Client.main(null)).start();
        while (result == null) {
            
            try {
                Thread.sleep(WAIT_TIME);
                time += WAIT_TIME;
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            result = Client.getController();
            
            if (time > 100 * WAIT_TIME) {
                throw new RuntimeException("unable to get controller");
            }
        }
        
        controller = result;
        return result;
    }
    
}
