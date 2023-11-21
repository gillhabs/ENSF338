package myLib;

import org.junit.Test;
import static org.junit.Assert.*;
import myLib.datastructures.linear.CSLL;
import myLib.datastructures.nodes.DNode;

/**
 * Unit tests for Circular Singly Linked List (CSLL) class
 * @author chantaeh
 */
public class CSLLTest 
{        
    /**
     * Test that CSLL class exists
     */
    @Test
    public void testClassName() {
        Class aClass = CSLL.class;
        String simpleClassName = aClass.getSimpleName();
        assertEquals("CSLL", simpleClassName);
    }

    /**
     * Test that default constructor sets head and tail to null, and size to 0
     */
    @Test
    public void testDefaultConstructor() {
        CSLL list1 = new CSLL();
        assertNull(list1.getHead());
        assertNull(list1.getTail());
        assertEquals(0, list1.getSize());
    }

    /**
     * Test one argument constructor
     */
    @Test
    public void testConstructorOneArg() {
        DNode expected = new DNode(11);
        CSLL list1 = new CSLL(expected);
        assertEquals(expected, list1.getHead());
        assertEquals(expected, list1.getTail());
        assertEquals(1, list1.getSize());
    }

    /*
     * Tests for method insertHead()
     */

    @Test
    public void testInsertHeadToEmptyList() {
        CSLL list = new CSLL();
        int expected = 5;
        list.insertHead(new DNode(expected));
        assertEquals(expected, list.getHead().getData());
        assertEquals(expected, list.getTail().getData());
        assertEquals(1, list.getSize());
    }

    @Test
    public void testInsertHeadToNonemptyList() {
        int expectedHead = -2;
        int expectedTail = 3;
        CSLL list = new CSLL(new DNode(expectedTail));
        list.insertHead(new DNode(expectedHead));
        assertEquals(expectedHead, list.getHead().getData());
        assertEquals(expectedTail, list.getTail().getData());
        assertEquals(2, list.getSize());
    }

    /*
     * Tests for method insertTail()
     */

    @Test
    public void testInsertTailToEmptyList() {
        CSLL list = new CSLL();
        int expected = 5;
        list.insertTail(new DNode(expected));
        assertEquals(expected, list.getHead().getData());
        assertEquals(expected, list.getTail().getData());
        assertEquals(1, list.getSize());
    }

    @Test
    public void testInsertTailToNonemptyList() {
        int expectedHead = -2;
        int expectedTail = 3;
        CSLL list = new CSLL(new DNode(expectedHead));
        list.insertTail(new DNode(expectedTail));
        assertEquals(expectedHead, list.getHead().getData());
        assertEquals(expectedTail, list.getTail().getData());
        assertEquals(2, list.getSize());
    }

    /*
     * Tests for method insert()
     */

    @Test
    public void testInsertToEmptyList() {
        CSLL list = new CSLL();
        int expected = 5;
        list.insert(new DNode(expected), 0);
        assertEquals(expected, list.getHead().getData());
        assertEquals(expected, list.getTail().getData());
        assertEquals(1, list.getSize());
    }

    @Test
    public void testInsertFront() {
        int expectedHead = -2;
        int expectedTail = 3;
        CSLL list = new CSLL(new DNode(expectedTail));
        list.insert(new DNode(expectedHead), 0);
        assertEquals(expectedHead, list.getHead().getData());
        assertEquals(expectedTail, list.getTail().getData());
        assertEquals(2, list.getSize());
    }


    @Test
    public void testInsertMiddle() {
        int[] expected = {8, 5, 1};

        CSLL list = new CSLL();
        list.insertHead(new DNode(1));
        list.insertHead(new DNode(8));
        list.insert(new DNode(5), 1);
        
        DNode current = list.getHead();
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], current.getData());
            current = current.getNext();
        }
        assertEquals(expected.length, list.getSize());
    }

    @Test
    public void testInsertEnd() {
        int expectedHead = -2;
        int expectedTail = 3;
        CSLL list = new CSLL(new DNode(expectedHead));
        list.insert(new DNode(expectedTail), 1);
        assertEquals(expectedHead, list.getHead().getData());
        assertEquals(expectedTail, list.getTail().getData());
        assertEquals(2, list.getSize());
    }

    /*
     * Tests for method sortedInsert()
     */

    @Test
    public void testSortedInsertToEmptyList() {
        CSLL list = new CSLL();
        int expected = 5;
        list.sortedInsert(new DNode(expected));
        assertEquals(expected, list.getHead().getData());
        assertEquals(expected, list.getTail().getData());
        assertEquals(1, list.getSize());
    }

    @Test
    public void testSortedInsertFront() {
        int expectedHead = -2;
        int expectedTail = 3;
        CSLL list = new CSLL(new DNode(expectedTail));
        list.sortedInsert(new DNode(expectedHead));
        assertEquals(expectedHead, list.getHead().getData());
        assertEquals(expectedTail, list.getTail().getData());
        assertEquals(2, list.getSize());
    }

    @Test
    public void testSortedInsertMiddleWithSort() {
        int[] expected = {1, 5, 8};

        CSLL list = new CSLL();
        list.insertHead(new DNode(1));
        list.insertHead(new DNode(8));
        list.sortedInsert(new DNode(5));
        
        DNode current = list.getHead();
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], current.getData());
            current = current.getNext();
        }
        assertEquals(expected.length, list.getSize());
    }

    @Test
    public void testSortedInsertMiddleWithoutSort() {
        int[] expected = {1, 5, 8};

        CSLL list = new CSLL();
        list.insertHead(new DNode(8));
        list.insertHead(new DNode(1));
        list.sortedInsert(new DNode(5));
        
        DNode current = list.getHead();
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], current.getData());
            current = current.getNext();
        }
        assertEquals(expected.length, list.getSize());
    }

    @Test
    public void testSortedInsertEnd() {
        int expectedHead = -2;
        int expectedTail = 3;
        CSLL list = new CSLL(new DNode(expectedHead));
        list.sortedInsert(new DNode(expectedTail));
        assertEquals(expectedHead, list.getHead().getData());
        assertEquals(expectedTail, list.getTail().getData());
        assertEquals(2, list.getSize());
    }

    /*
     * Tests for search() method
     */

    @Test
    public void testSearchFront() {
        DNode expected = new DNode(4);
        CSLL list = new CSLL(expected);
        list.insertTail(new DNode(9));
        
        DNode actual = list.search(expected);
        assertEquals(expected.getData(), actual.getData());

        assertSame(list.getHead(), actual);
    }

    @Test
    public void testSearchMiddle() {
        DNode expected = new DNode(4);
        CSLL list = new CSLL();
        list.insertTail(new DNode(0));
        list.insertTail(expected);
        list.insertTail(new DNode(9));
        
        DNode actual = list.search(expected);
        assertEquals(expected.getData(), actual.getData());
    }

    @Test
    public void testSearchEnd() {
        DNode expected = new DNode(5);
        CSLL list = new CSLL(new DNode(0));
        list.insertTail(expected);
        
        DNode actual = list.search(expected);
        assertEquals(expected.getData(), actual.getData());

        assertSame(list.getTail(), actual);
    }

    @Test
    public void testSearchElementDoesNotExist() {
        CSLL list = new CSLL(new DNode(0));
        
        DNode actual = list.search(new DNode(-1));
        assertNull(actual);
    }

    @Test
    public void testSearchEmptyList() {
        CSLL list = new CSLL();
        
        DNode actual = list.search(new DNode(-1));
        assertNull(actual);
    }

    /*
     * Tests for deleteHead() method
     */

    @Test
    public void testDeleteHeadNonEmptyList() {
        int expected = 4;
        CSLL list = new CSLL(new DNode(8));
        list.insertTail(new DNode(expected));
        list.deleteHead();
        assertEquals(expected, list.getHead().getData());
        assertEquals(expected, list.getTail().getData());
        assertEquals(1, list.getSize());
    }

    @Test
    public void testDeleteHeadNonEmptyListBecomesEmpty() {
        CSLL list = new CSLL(new DNode(8));
        list.deleteHead();
        assertNull(list.getHead());
        assertNull(list.getTail());
        assertEquals(0, list.getSize());
    }

    @Test
    public void testDeleteHeadEmptyList() {
        boolean testResult = true;
        CSLL list = new CSLL();
        try {
            list.deleteHead();
        } catch (Exception e) {
            testResult = false;
        }
        assertTrue("Test deleteHead() from empty list threw an exception.", testResult);
        assertNull(list.getHead());
        assertNull(list.getTail());
        assertEquals(0, list.getSize());
    }

    /*
     * Tests for deleteTail() method
     */

    @Test
    public void testDeleteTailNonEmptyList() {
        int expected = 4;
        CSLL list = new CSLL(new DNode(expected));
        list.insertTail(new DNode(4));
        list.deleteTail();
        assertEquals(expected, list.getHead().getData());
        assertEquals(expected, list.getTail().getData());
        assertEquals(1, list.getSize());
    }

    @Test
    public void testDeleteTailNonEmptyListBecomesEmpty() {
        CSLL list = new CSLL(new DNode(8));
        list.deleteTail();
        assertNull(list.getHead());
        assertNull(list.getTail());
        assertEquals(0, list.getSize());
    }

    @Test
    public void testDeleteTailEmptyList() {
        boolean testResult = true;
        CSLL list = new CSLL();
        try {
            list.deleteTail();
        } catch (Exception e) {
            testResult = false;
        }
        assertTrue("Test deleteTail() from empty list threw an exception.", testResult);
        assertNull(list.getHead());
        assertNull(list.getTail());
        assertEquals(0, list.getSize());
    }

    /*
     * Tests for delete() method
     */

    @Test
    public void testDeleteEmptyList() {
        boolean testResult = true;
        CSLL list = new CSLL();
        try {
            list.delete(new DNode(0));
        } catch (Exception e) {
            testResult = false;
        }
        assertTrue("Test delete() from empty list threw an exception.", testResult);
        assertNull(list.getHead());
        assertNull(list.getTail());
        assertEquals(0, list.getSize());
    }

    @Test
    public void testDeleteFront() {
        CSLL list = new CSLL(new DNode(8));
        list.delete(new DNode(8));
        assertNull(list.getHead());
        assertNull(list.getTail());
        assertEquals(0, list.getSize());
    }

    @Test
    public void testDeleteMiddle() {
        CSLL list = new CSLL(new DNode(8));
        list.insertTail(new DNode(1));
        list.insertTail(new DNode(3));
        list.delete(new DNode(1));
        assertEquals(8, list.getHead().getData());
        assertEquals(3, list.getTail().getData());
        assertEquals(2, list.getSize());
    }

    @Test
    public void testDeleteEnd() {
        CSLL list = new CSLL(new DNode(8));
        list.insertTail(new DNode(1));
        list.delete(new DNode(1));
        assertEquals(list.getHead().getData(), list.getTail().getData());
        assertSame(list.getHead(), list.getTail());
        assertEquals(1, list.getSize());
    }

    @Test
    public void testDeleteElementDoesNotExist() {
        CSLL list = new CSLL(new DNode(8));
        list.delete(new DNode(0));
        assertNotNull(list.getHead());
        assertNotNull(list.getTail());
        assertEquals(1, list.getSize());
    }
    
    /*
     * Tests for sort() method
     */

    @Test
    public void testSortEmptyList() {
        boolean testResult = true;
        CSLL list = new CSLL();
        try {
            list.sort();
        } catch (Exception e) {
            testResult = false;
        }
        assertTrue("Test sort() on empty list threw an exception.", testResult);
        assertNull(list.getHead());
        assertNull(list.getTail());
        assertEquals(0, list.getSize());
    }

    @Test
    public void testSortSortedList() {
        int[] expected = {2, 8};

        CSLL list = new CSLL(new DNode(expected[0]));
        list.insertTail(new DNode(expected[1]));
        list.sort();

        DNode current = list.getHead();
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], current.getData());
            current = current.getNext();
        }
        assertEquals(expected.length, list.getSize());
    }

    @Test
    public void testSortReversedList() {
        int[] expected = {2, 8};

        CSLL list = new CSLL(new DNode(expected[1]));
        list.insertTail(new DNode(expected[0]));
        list.sort();

        DNode current = list.getHead();
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], current.getData());
            current = current.getNext();
        }
        assertEquals(expected.length, list.getSize());
    }

    @Test
    public void testSortUnsortedList() {
        int[] expected = {1, 3, 5, 8};

        CSLL list = new CSLL(new DNode(5));
        list.insertTail(new DNode(8));
        list.insertTail(new DNode(1));
        list.insertTail(new DNode(3));

        list.sort();

        DNode current = list.getHead();
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], current.getData());
            current = current.getNext();
        }
        assertEquals(expected.length, list.getSize());
    }

    /*
     * Tests for clear() method
     */

    @Test
    public void testClearEmptyList() {
        boolean testResult = true;
        CSLL list = new CSLL();
        try {
            list.clear();
        } catch (Exception e) {
            testResult = false;
        }
        assertTrue("Test clear() on empty list threw an exception.", testResult);
        assertNull(list.getHead());
        assertNull(list.getTail());
        assertEquals(0, list.getSize());
    }

    @Test
    public void testClearNonemptyList() {
        CSLL list = new CSLL(new DNode(5));
        list.insertTail(new DNode(3));
        list.insertTail(new DNode(9));
        list.clear();
        assertNull(list.getHead());
        assertNull(list.getTail());
        assertEquals(0, list.getSize());
    }

    /* Test that the list is circular */

    @Test
    public void testCircularOneElement() {
        CSLL list = new CSLL(new DNode(5));
        assertEquals(list.getHead(), list.getTail());
        assertEquals(list.getHead(), list.getTail().getNext());
        assertEquals(list.getTail(), list.getHead().getNext());
    }

    @Test
    public void testCircularManyElements() {
        CSLL list = new CSLL(new DNode(5));
        list.insertTail(new DNode(3));
        list.insertTail(new DNode(9));
        assertEquals(list.getHead(), list.getTail().getNext());
    }

}
