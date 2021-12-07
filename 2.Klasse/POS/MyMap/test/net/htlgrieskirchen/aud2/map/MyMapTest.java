/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.aud2.map;

import net.htlgrieskirchen.aud2.map.MyMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
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
public class MyMapTest {

    public MyMapTest() {
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
     * Test of put method, of class MyMap.
     */
    @Test
    public void testPut() {
        System.out.println("put");

        String key = "foo";
        String value = "bar";

        MyMap myMap = new MyMap();
        Map<String, String> javaMap = new TreeMap<>();

        myMap.put(key, value);
        javaMap.put(key, value);

        assertEquals(myMap.size(), javaMap.size());

        String expResult = javaMap.get(key);
        String result = myMap.get(key);

        assertEquals(expResult, result);

    }

    @Test
    public void nullTestPut() {
        System.out.println("put");

        String key = null;
        String value = null;

        boolean catched = false;
        
        MyMap myMap = new MyMap();
        
        try{
            myMap.put(key, value);
        } catch (NullPointerException ex){
            catched = true;
        }
        
        
        assertTrue(catched);
    }

    @Test
    public void leertestPut() {
        System.out.println("put");

        String key = "";
        String value = "";

        MyMap myMap = new MyMap();
        Map<String, String> javaMap = new TreeMap<>();

        myMap.put(key, value);
        javaMap.put(key, value);

        assertEquals(myMap.size(), javaMap.size());

        String expResult = javaMap.get(key);
        String result = myMap.get(key);

        assertEquals(expResult, result);
    }

    ////////////////////////////////////////////////////////////////////
    /**
     * Test of get method, of class MyMap.
     */
    @Test
    public void testGet() {
        System.out.println("get");

        String key = "foo";
        String value = "bar";

        MyMap myMap = new MyMap();
        Map<String, String> javaMap = new TreeMap<>();

        myMap.put(key, value);
        javaMap.put(key, value);

        assertEquals(myMap.size(), javaMap.size());

        String expResult = javaMap.get(key);
        String result = myMap.get(key);

        assertEquals(expResult, result);
    }

    @Test
    public void nullTestGet() {
        System.out.println("get");

        String key = null;
        String value = null;
        boolean catched = false;

        MyMap myMap = new MyMap();
        
        myMap.put("foo", "bar");
        
        try{
            myMap.get(key);
        }catch(NullPointerException ex){
            catched = true;
        }
        assertTrue(catched);
        
    }

    @Test
    public void leerTestGet() {
        System.out.println("get");

        String key = "";
        String value = "";

        MyMap myMap = new MyMap();
        Map<String, String> javaMap = new TreeMap<>();

        myMap.put(key, value);
        javaMap.put(key, value);

        assertEquals(myMap.size(), javaMap.size());

        String expResult = javaMap.get(key);
        String result = myMap.get(key);

        assertEquals(expResult, result);
    }

///////////////////////////////////////////////////////////////////////
    /**
     * Test of remove method, of class MyMap.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");

        String key = "bar";
        String value = "foo";

        MyMap myMap = new MyMap();
        Map<String, String> javaMap = new TreeMap<>();

        myMap.put(key, value);
        javaMap.put(key, value);

        String tstremove = javaMap.remove(key);
        String remove = myMap.remove(key);

        assertEquals(tstremove, remove);
    }

    @Test
    public void nulltestRemove() {
        System.out.println("remove");

        String key = null;
        String value = null;

        MyMap myMap = new MyMap();
        Map<String, String> javaMap = new TreeMap<>();

        String tstremove = javaMap.remove(key);
        String remove = myMap.remove(key);

        
        boolean catched = false;
        try {
            myMap.put(key, value);
        } catch (NullPointerException ex) {
            catched = true;
        }

        assertTrue(catched);
                
        javaMap.put(key, value);

        
    }

    @Test
    public void leertestRemove() {
        System.out.println("remove");

        String key = "";
        String value = "";

        MyMap myMap = new MyMap();
        Map<String, String> javaMap = new TreeMap<>();

        myMap.put(key, value);
        javaMap.put(key, value);

        String tstremove = javaMap.remove(key);
        String remove = myMap.remove(key);

        assertEquals(tstremove, remove);
    }

    //////////////////////////////////////////////////////////////////
    /**
     * Test of keySet method, of class MyMap.
     */
    @Test
    public void testKeySet() {
        System.out.println("keySet");

        String key = "bar";
        String value = "foo";

        MyMap myMap = new MyMap();
        Map<String, String> javaMap = new TreeMap<>();

        myMap.put(key, value);
        javaMap.put(key, value);

        assertEquals(myMap.size(), javaMap.size());

        Set<String> tstKeySet = javaMap.keySet();
        Set<String> Keyset = myMap.keySet();

        assertEquals(tstKeySet, Keyset);
    }

    @Test
    public void nulltestKeySet() {
        System.out.println("keySet");

        boolean catched = false;

        MyMap myMap = new MyMap();
        Map<String, String> javaMap = new TreeMap<>();

        try{
            myMap.keySet();
        }catch(NullPointerException ex){
            catched = true;
        }
        assertTrue(catched);
    }

    @Test
    public void leertestKeySet() {
        System.out.println("keySet");

        String key = "";
        String value = "";

        MyMap myMap = new MyMap();
        Map<String, String> javaMap = new TreeMap<>();

        myMap.put(key, value);
        javaMap.put(key, value);

        assertEquals(myMap.size(), javaMap.size());

        Set<String> tstKeySet = javaMap.keySet();
        Set<String> Keyset = myMap.keySet();

        assertEquals(tstKeySet, Keyset);
    }

    ///////////////////////////////////////////////////////////////////////
    /**
     * Test of values method, of class MyMap.
     */
    @Test
    public void testValues() {
        System.out.println("values");

        String key = "bar";
        String value = "foo";

        MyMap myMap = new MyMap();
        Map<String, String> javaMap = new TreeMap<>();

        myMap.put(key, value);
        javaMap.put(key, value);

        assertEquals(myMap.size(), javaMap.size());

        Collection<String> tstTestValue = javaMap.values();
        Collection<String> TestValue = myMap.values();

        assertEquals(tstTestValue, TestValue);
    }

    @Test
    public void nulltestValues() {
        System.out.println("values");

        
        boolean catched = false;

        MyMap myMap = new MyMap();
        try{
            myMap.values();
        }catch (NullPointerException ex){
            catched = true;
        }
        
        assertTrue(catched);
    }

    @Test
    public void leertestValues() {
        System.out.println("values");

        String key = "";
        String value = "";

        MyMap myMap = new MyMap();
        Map<String, String> javaMap = new TreeMap<>();

        myMap.put(key, value);
        javaMap.put(key, value);

        assertEquals(myMap.size(), javaMap.size());

        Collection<String> tstTestValue = javaMap.values();
        Collection<String> TestValue = myMap.values();

        assertEquals(tstTestValue, TestValue);
    }

    ////////////////////////////////////////////////////////////////////
    /**
     * Test of entrySet method, of class MyMap.
     */
    @Test
    public void testEntrySet() {
        System.out.println("entrySet");

        String key = "foo";
        String value = "bar";

        MyMap myMap = new MyMap();
        Map<String, String> javaMap = new TreeMap<>();

        myMap.put(key, value);
        javaMap.put(key, value);

        assertEquals(myMap.size(), javaMap.size());

        Set<Map.Entry<String, String>> tstTestEntrySet = javaMap.entrySet();
        Set<MyMap.Entry> TestEntrySet = myMap.entrySet();

        assertEquals(tstTestEntrySet, TestEntrySet);
    }

    @Test
    public void nulltestEntrySet() {
        System.out.println("entrySet");

        boolean catched = false;
        MyMap myMap = new MyMap();
        Map<String, String> javaMap = new TreeMap<>();
        
        try{
            myMap.entrySet();
        }catch(NullPointerException e){
            catched = true;
        }
    }

    @Test
    public void leertestEntrySet() {
        System.out.println("entrySet");

        String key = "";
        String value = "";

        MyMap myMap = new MyMap();
        Map<String, String> javaMap = new TreeMap<>();

        myMap.put(key, value);
        javaMap.put(key, value);

        assertEquals(myMap.size(), javaMap.size());

        Set<Map.Entry<String, String>> tstTestEntrySet = javaMap.entrySet();
        Set<MyMap.Entry> TestEntrySet = myMap.entrySet();

        assertEquals(tstTestEntrySet, TestEntrySet);
    }

    //////////////////////////////////////////////////////////////////////////
    /**
     * Test of containsKey method, of class MyMap.
     */
    @Test
    public void testContainsKey() {
        System.out.println("containsKey");

        String key = "foo";
        String value = "bar";

        MyMap myMap = new MyMap();
        Map<String, String> javaMap = new TreeMap<>();

        myMap.put(key, value);
        javaMap.put(key, value);

        assertEquals(myMap.size(), javaMap.size());

        boolean tstTestContainsKey = javaMap.containsKey(key);
        boolean TestContainsKey = myMap.containsKey(key);

        assertEquals(tstTestContainsKey, TestContainsKey);
    }

    @Test
    public void nulltestContainsKey() {
        System.out.println("containsKey");

        String key = "foo";
        String value = "bar";
        
        boolean catched = false;
        MyMap myMap = new MyMap();
        Map<String, String> javaMap = new TreeMap<>();

        try{
            myMap.containsKey(key);
        }catch(Exception ex){
            catched = true;
        }
        assertTrue(catched);
        
    }

    @Test
    public void leertestContainsKey() {
        System.out.println("containsKey");

        String key = "";
        String value = "";

        MyMap myMap = new MyMap();
        Map<String, String> javaMap = new TreeMap<>();

        myMap.put(key, value);
        javaMap.put(key, value);

        assertEquals(myMap.size(), javaMap.size());

        boolean tstTestContainsKey = javaMap.containsKey(key);
        boolean TestContainsKey = myMap.containsKey(key);

        assertEquals(tstTestContainsKey, TestContainsKey);
    }

    //////////////////////////////////////////////////////////////////////
    /**
     * Test of containsValue method, of class MyMap.
     */
    @Test
    public void testContainsValue() {
        System.out.println("containsValue");

        String key = "bar";
        String value = "foo";

        MyMap myMap = new MyMap();
        Map<String, String> javaMap = new TreeMap<>();

        myMap.put(key, value);
        javaMap.put(key, value);

        assertEquals(myMap.size(), javaMap.size());

        boolean tstContainsValue = javaMap.containsValue(key);
        boolean TestContainsValue = myMap.containsValue(key);

        assertEquals(tstContainsValue, TestContainsValue);
    }

    @Test
    public void nulltestContainsValue() {
        System.out.println("containsValue");

        String key = "foo";
        String value = "bar";
        boolean catched = false;

        MyMap myMap = new MyMap();
        Map<String, String> javaMap = new TreeMap<>();

        try{
            myMap.containsKey(key);
        }catch(NullPointerException ex){
            catched = true;
        }
        
        assertTrue(catched);
    }

    @Test
    public void leertestContainsValue() {
        System.out.println("containsValue");

        String key = "";
        String value = "";

        MyMap myMap = new MyMap();
        Map<String, String> javaMap = new TreeMap<>();

        myMap.put(key, value);
        javaMap.put(key, value);

        assertEquals(myMap.size(), javaMap.size());

        boolean tstContainsValue = javaMap.containsValue(key);
        boolean TestContainsValue = myMap.containsValue(key);

        assertEquals(tstContainsValue, TestContainsValue);
    }

    ////////////////////////////////////////////////////////////////////////
    /**
     * Test of size method, of class MyMap.
     */
    @Test
    public void testSize() {
        System.out.println("size");

        String key = "foo";
        String value = "bar";

        MyMap myMap = new MyMap();
        Map<String, String> javaMap = new TreeMap<>();

        myMap.put(key, value);
        javaMap.put(key, value);

        assertEquals(myMap.size(), javaMap.size());

    }

    @Test
    public void leertestSize() {
        System.out.println("size");

        String key = "";
        String value = "";

        MyMap myMap = new MyMap();
        Map<String, String> javaMap = new TreeMap<>();

        myMap.put(key, value);
        javaMap.put(key, value);
        
        assertEquals(myMap.size(), javaMap.size());
    }

    ////////////////////////////////////////////////////////////////
    /**
     * Test of isEmpty method, of class MyMap.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");

        String key = "";
        String value = "";

        MyMap myMap = new MyMap();
        Map<String, String> javaMap = new TreeMap<>();

        myMap.put(key, value);
        javaMap.put(key, value);

        assertEquals(myMap.size(), javaMap.size());

        boolean tstTestIsEmpty = javaMap.isEmpty();
        boolean TestIsEmpty = myMap.isEmpty();

        assertEquals(tstTestIsEmpty, TestIsEmpty);
    }

    @Test
    public void nulltestIsEmpty() {
        System.out.println("isEmpty");

        
        MyMap myMap = new MyMap();
        Map<String, String> javaMap = new TreeMap<>();

        myMap.put("foo", "bar");
        javaMap.put("foo", "bar");
        
        assertEquals(javaMap.size(), javaMap.size());
    }

    @Test
    public void leertestIsEmpty() {
        System.out.println("isEmpty");

        String key = "";
        String value = "";

        MyMap myMap = new MyMap();
        Map<String, String> javaMap = new TreeMap<>();

        myMap.put(key, value);
        javaMap.put(key, value);

        assertEquals(myMap.size(), javaMap.size());

        boolean tstTestIsEmpty = javaMap.isEmpty();
        boolean TestIsEmpty = myMap.isEmpty();

        assertEquals(tstTestIsEmpty, TestIsEmpty);
    }

}
