package myLib;

import org.junit.Test;
import static org.junit.Assert.*;
import myLib.datastructures.heap.MaxH;

/**
 * Unit test for Max Heap (MaxH) class
 * @author chantaeh
 */
public class MaxHTest 
{        
    /**
     * Test that MaxH class exists
     */
    @Test
    public void testClassName() {
        Class aClass = MaxH.class;
        String simpleClassName = aClass.getSimpleName();
        assertEquals("MaxH", simpleClassName);
    }

    /**
     * Test that default constructor sets head and tail to null, and size to 0
     */
    @Test
    public void testDefaultConstructor() {
        MaxH m = new MaxH();
        assertNotNull(m);
        assertEquals(0, m.getSize());
    }

    /**
     * Test constructor given size
     */
    @Test
    public void testConstructorArgSize() {
        MaxH m = new MaxH(3);
        assertNotNull(m);
        assertTrue(m.isEmpty());
    }

    /**
     * Test constructor given int array
     */
    @Test
    public void testConstructorArgArray() {
        int[] arr = {3, 7, 0, 1, 6};
        MaxH m = new MaxH(arr);
        assertEquals(arr.length, m.getSize());
        assertFalse(m.isEmpty());
    }

    @Test
    public void testInsert() {
        int[] expected = {10, 5, 2};
        MaxH m = new MaxH();
        m.insert(expected[0]);
        m.insert(expected[1]);
        m.insert(expected[2]);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], (int)m.getElements().get(i));
        }
    }

    @Test
    public void testSort() {
        int[] expected = {2, 5, 8, 10, 12};
        MaxH m = new MaxH();
        m.insert(12);
        m.insert(5);
        m.insert(10);
        m.insert(2);
        m.insert(8);
        m.sort();
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], (int)m.getElements().get(i));
        }
    }

    @Test
    public void testDelete() {
        int[] expected = {5, 2};
        MaxH m = new MaxH();
        m.insert(expected[0]);
        m.insert(expected[1]);
        m.insert(3);
        m.delete(3);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], (int)m.getElements().get(i));
        }
    }

    @Test
    public void testContainsFalse() {
        MaxH m = new MaxH();
        boolean actual = m.contains(10);
        assertFalse(actual);
    }

    @Test
    public void testContainsTrue() {
        MaxH m = new MaxH();
        m.insert(10);
        boolean actual = m.contains(10);
        assertTrue(actual);
    }

    @Test
    public void testClear() {
        MaxH m = new MaxH();
        m.insert(12);
        m.insert(5);
        m.insert(10);
        m.insert(2);
        m.insert(8);
        assertEquals(5, m.getSize());
        m.clear();
        assertEquals(0, m.getSize());
    }



    
}