package myLib;

import org.junit.Test;
import static org.junit.Assert.*;

import myLib.datastructures.linear.QueueLL;
import myLib.datastructures.nodes.DNode;

/**
 * Unit test for Queue class
 * @author chantaeh
 */
public class QueueLLTest 
{        
    /**
     * Test that QueueLL class exists
     */
    @Test
    public void testClassName() {
        Class aClass = QueueLL.class;
        String simpleClassName = aClass.getSimpleName();
        assertEquals("QueueLL", simpleClassName);
    }

    /**
     * Test that default constructor sets head and tail to null, and size to 0
     */
    @Test
    public void testDefaultConstructor() {
        QueueLL list1 = new QueueLL();
        assertNull(list1.peek());
        assertEquals(0, list1.getSize());
    }

    /**
     * Test one argument constructor
     */
    @Test
    public void testConstructorOneArg() {
        DNode expected = new DNode(11);
        QueueLL list1 = new QueueLL(expected);
        assertEquals(expected, list1.peek());
        assertEquals(1, list1.getSize());
    }

    /*
     * Tests for method enqueue()
     */

    @Test
    public void testEnqueueToEmptyList() {
        QueueLL list = new QueueLL();
        int expected = 5;
        list.enqueue(new DNode(expected));
        assertEquals(1, list.getSize());
        assertEquals(expected, list.dequeue().getData());
    }

    @Test
    public void testEnqueueToNonemptyList() {
        int expectedHead = -2;
        int expectedTail = 3;
        QueueLL list = new QueueLL(new DNode(expectedHead));
        list.enqueue(new DNode(expectedTail));
        assertEquals(2, list.getSize());
        assertEquals(expectedHead, list.dequeue().getData());
        assertEquals(expectedTail, list.dequeue().getData());
    }

    /*
     * Tests for dequeue() method
     */

    @Test
    public void testDequeueFromEmptyList() {
        boolean testResult = true;
        QueueLL list = new QueueLL();
        try {
            list.dequeue();
        } catch (Exception e) {
            testResult = false;
        }
        assertTrue("Test deleteHead() from empty list threw an exception.", testResult);
        assertNull(list.dequeue());
        assertEquals(0, list.getSize());
    }


    /*
     * Tests for search() method
     */

    @Test
    public void testSearchFront() {
        DNode expected = new DNode(4);
        QueueLL list = new QueueLL(expected);
        list.enqueue(new DNode(9));
        
        DNode actual = list.search(expected);
        assertEquals(expected.getData(), actual.getData());
    }

    @Test
    public void testSearchMiddle() {
        DNode expected = new DNode(4);
        QueueLL list = new QueueLL();
        list.enqueue(new DNode(0));
        list.enqueue(expected);
        list.enqueue(new DNode(9));
        
        DNode actual = list.search(expected);
        assertEquals(expected.getData(), actual.getData());
    }

    @Test
    public void testSearchEnd() {
        DNode expected = new DNode(5);
        QueueLL list = new QueueLL(new DNode(0));
        list.enqueue(expected);
        
        DNode actual = list.search(expected);
        assertEquals(expected.getData(), actual.getData());
    }

    @Test
    public void testSearchElementDoesNotExist() {
        QueueLL list = new QueueLL(new DNode(0));
        
        DNode actual = list.search(new DNode(-1));
        assertNull(actual);
    }

    @Test
    public void testSearchEmptyList() {
        QueueLL list = new QueueLL();
        
        DNode actual = list.search(new DNode(-1));
        assertNull(actual);
    }

    /*
     * Tests for clear() method
     */

    @Test
    public void testClearEmptyList() {
        boolean testResult = true;
        QueueLL list = new QueueLL();
        try {
            list.clear();
        } catch (Exception e) {
            testResult = false;
        }
        assertTrue("Test clear() on empty list threw an exception.", testResult);
        assertNull(list.dequeue());
        assertEquals(0, list.getSize());
    }

    @Test
    public void testClearNonemptyList() {
        QueueLL list = new QueueLL(new DNode(5));
        list.enqueue(new DNode(3));
        list.enqueue(new DNode(9));
        assertEquals(3, list.getSize());
        list.clear();
        assertNull(list.dequeue());
        assertEquals(0, list.getSize());
    }

    /*
     * Tests for extraneous methods
     * (these should have no effect. 
     * if the method has a return value, it should return null)
     */

     @Test
     public void testExtraneousMethodsReturnNull() {
         int expected = 5;
         QueueLL queue = new QueueLL(new DNode(expected));
         assertNull("getHead() should return null", queue.getHead());
         assertNull("getTail() should return null", queue.getTail());
         assertNull("deleteHead() should return null", queue.deleteHead());
         assertNull("deleteTail() should return null", queue.deleteTail());
         
         // queue should still be in starting state
         assertEquals(1, queue.getSize());
         assertEquals(expected, queue.dequeue().getData());
     }
 
     @Test
     public void testExtraneousMethodsNoReturn() {
         int expected = 5;
         QueueLL queue = new QueueLL(new DNode(expected));
 
         DNode temp = new DNode(11);
         queue.insertHead(temp);
         queue.insertTail(new DNode(0));
         queue.insert(new DNode(0), 0);
         queue.sortedInsert(new DNode(0));
         queue.delete(temp);
         queue.sort();
 
         // queue should still be in starting state
         assertEquals(1, queue.getSize());
         assertEquals(expected, queue.dequeue().getData());
     }


}
