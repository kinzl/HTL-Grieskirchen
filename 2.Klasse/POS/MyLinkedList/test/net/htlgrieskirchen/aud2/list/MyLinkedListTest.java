package net.htlgrieskirchen.aud2.list;

import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

public class MyLinkedListTest {

    public MyLinkedListTest() {
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

    @Test
    public void testAdd_MyList() {
        System.out.println("add (MyList only)");

        String s = "a";
        
        MyList<String> instance = new MyList<>();
        
        boolean expResult = true;
        boolean result = instance.add(s);
        assertEquals(expResult, result);
    }

    @Test
    public void testAdd_JavaAndMyList() {
        System.out.println("add (Java and MyList)");

        int i1 = 0;
        String s1 = "a";
        int i2 = 1;
        String s2 = "b";
        int i3 = 2;
        String s3 = "C";

        MyList<String> instance = new MyList<>();
        instance.add(i1, s1);
        instance.add(i2, s2);
        instance.add(i3, s3);

        LinkedList ll = new LinkedList();
        ll.add(i1, s1);
        ll.add(i2, s2);
        ll.add(i3, s3);

        assertTrue(instance.size() == ll.size());
        for (int i = 0; i < instance.size(); i++) {
            assertEquals(instance.get(i), ll.get(i));
        }
    }

    @Test
    public void testGet() {
        System.out.println("get");
        
        String s = "b";

        MyList<String> instance = new MyList<>();
        instance.add("a");
        instance.add(s);

        String expResult = s;
        String result = instance.get(1);
        assertEquals(expResult, result);
    }

    @Test
    public void testSet() {
        System.out.println("set");

        int i = 3;
        String s = "g";
        
        MyList<String> instance = new MyList<>();
        instance.add("a");
        instance.add("b");
        instance.add("c");
        instance.add("d");
        instance.add("e");
        instance.set(i, s);

        String expResult = s;
        String result = instance.get(i);
        assertEquals(expResult, result);
    }

    @Test
    public void testContains() {
        System.out.println("contains");
        
        String s = "e";
        
        MyList<String> instance = new MyList<>();
        instance.add("a");
        instance.add("b");
        instance.add("c");
        instance.add("d");
        instance.add(s);
        
        boolean expResult = true;
        boolean result = instance.contains(s);
        assertEquals(expResult, result);
    }

    @Test
    public void testIndexOf() {
        System.out.println("indexOf");
        
        String s = "";
        
        MyList<String> instance = new MyList<>();        
        instance.add("a");
        instance.add("b");
        instance.add("c");
        instance.add("");
        instance.add("e");

        LinkedList ll = new LinkedList();
        ll.add("a");
        ll.add("b");
        ll.add("c");
        ll.add(s);
        ll.add("e");

        int expResult = ll.indexOf(s);
        int result = instance.indexOf(s);
        assertEquals(expResult, result);
    }

    @Test
    public void testSize() {
        System.out.println("size");

        MyList<String> instance = new MyList<>();
        instance.add("a");
        instance.add("b");
        instance.add("c");
        instance.add("d");
        instance.add("e");

        int expResult = 5;
        int result = instance.size();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsEmpty_false() {
        System.out.println("isEmpty (false)");

        MyList<String> instance = new MyList<>();
        instance.add("a");
        instance.add("b");

        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsEmpty_true() {
        System.out.println("isEmpty (true)");
        
        MyList<String> instance = new MyList<>();
        
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    @Test
    public void testRemove_String() {
        System.out.println("remove");

        String s = "a";

        MyList<String> instance = new MyList<>();
        
        boolean expResult = false;
        boolean result = instance.remove(s);
        assertEquals(expResult, result);
    }

    public void testRemove_int() {
        System.out.println("remove");
        
        int i = 0;
        
        MyList<String> instance = new MyList<>();
        
        String expResult = "";
        String result = instance.remove(i);
        assertEquals(expResult, result);
    }

}
