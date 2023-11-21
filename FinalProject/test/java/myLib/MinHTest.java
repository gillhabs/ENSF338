package myLib;

import org.junit.Test;
import static org.junit.Assert.*;
import myLib.datastructures.heap.MinH;

/**
 * Unit test for Min Heap (MinH) class
 * @author chantaeh
 */
public class MinHTest 
{        
    /**
     * Test that MinH class exists
     */
    @Test
    public void testClassName() {
        Class aClass = MinH.class;
        String simpleClassName = aClass.getSimpleName();
        assertEquals("MinH", simpleClassName);
    }

    /**
     * Test that default constructor sets head and tail to null, and size to 0
     */
    @Test
    public void testDefaultConstructor() {
        MinH m = new MinH();
        assertNotNull(m);
        assertEquals(0, m.getSize());
    }

    /**
     * Test constructor given size
     */
    @Test
    public void testConstructorArgSize() {
        MinH m = new MinH(3);
        assertNotNull(m);
        assertTrue(m.isEmpty());
    }

    /**
     * Test constructor given int array
     */
    @Test
    public void testConstructorArgArray() {
        int[] arr = {3, 7, 0, 1, 6};
        MinH m = new MinH(arr);
        assertEquals(arr.length, m.getSize());
        assertFalse(m.isEmpty());
    }

    @Test
    public void testInsert() {
        int[] expected = {2, 5, 10};
        MinH m = new MinH();
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
        MinH m = new MinH();
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
        int[] expected = {2, 5};
        MinH m = new MinH();
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
        MinH m = new MinH();
        boolean actual = m.contains(10);
        assertFalse(actual);
    }

    @Test
    public void testContainsTrue() {
        MinH m = new MinH();
        m.insert(10);
        boolean actual = m.contains(10);
        assertTrue(actual);
    }

    @Test
    public void testClear() {
        MinH m = new MinH();
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