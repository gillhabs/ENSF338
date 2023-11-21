package myLib;

import org.junit.Test;
import static org.junit.Assert.*;
import myLib.datastructures.linear.StackLL;
import myLib.datastructures.nodes.DNode;

/**
 * Unit test for Stack (StackLL) class
 * @author chantaeh
 */
public class StackLLTest 
{        
    /**
     * Test that StackLL class exists
     */
    @Test
    public void testClassName() {
        Class aClass = StackLL.class;
        String simpleClassName = aClass.getSimpleName();
        assertEquals("StackLL", simpleClassName);
    }

    /**
     * Test that default constructor sets head and tail to null, and size to 0
     */
    @Test
    public void testDefaultConstructor() {
        StackLL stack1 = new StackLL();
        assertNull(stack1.pop());
        assertEquals(0, stack1.getSize());
    }

    /**
     * Test one argument constructor
     */
    @Test
    public void testConstructorOneArg() {
        int expected = 11;
        StackLL stack1 = new StackLL(new DNode(expected));
        assertEquals(1, stack1.getSize());
        assertEquals(expected, stack1.pop().getData());
        assertEquals(0, stack1.getSize());
    }

    /*
     * Tests for methods push() and pop()
     */

    @Test
    public void testPushToEmptyStack() {
        StackLL stack = new StackLL();
        int expected = 5;
        stack.push(new DNode(expected));
        assertEquals(1, stack.getSize());
        assertEquals(expected, stack.pop().getData());
    }

    @Test
    public void testPushToNonEmptyStack() {
        int expectedHead = -2;
        int expectedTail = 3;
        StackLL stack = new StackLL(new DNode(expectedTail));
        stack.push(new DNode(expectedHead));
        assertEquals(2, stack.getSize());
        assertEquals(expectedHead, stack.pop().getData());
    }

    /*
     * Tests for search() method
     */

    @Test
    public void testSearchFront() {
        DNode expected = new DNode(4);
        StackLL stack = new StackLL(new DNode(9));
        stack.push(expected);
        
        DNode actual = stack.search(expected);
        assertEquals(expected.getData(), actual.getData());
    }

    @Test
    public void testSearchMiddle() {
        DNode expected = new DNode(4);
        StackLL stack = new StackLL();
        stack.push(new DNode(0));
        stack.push(expected);
        stack.push(new DNode(9));
        
        DNode actual = stack.search(expected);
        assertEquals(expected.getData(), actual.getData());
    }

    @Test
    public void testSearchEnd() {
        DNode expected = new DNode(5);
        StackLL stack = new StackLL(expected);
        stack.push(new DNode(0));
        
        DNode actual = stack.search(expected);
        assertEquals(expected.getData(), actual.getData());
    }

    @Test
    public void testSearchElementDoesNotExist() {
        StackLL stack = new StackLL(new DNode(0));
        
        DNode actual = stack.search(new DNode(-1));
        assertNull(actual);
    }

    @Test
    public void testSearchEmptyStack() {
        StackLL stack = new StackLL();
        
        DNode actual = stack.search(new DNode(-1));
        assertNull(actual);
    }

    /*
     * Tests for clear() method
     */

    @Test
    public void testClearEmptyStack() {
        boolean testResult = true;
        StackLL stack = new StackLL();
        try {
            stack.clear();
        } catch (Exception e) {
            testResult = false;
        }
        assertTrue("Test clear() on empty stack threw an exception.", testResult);
        assertNull(stack.pop());
        assertEquals(0, stack.getSize());
    }

    @Test
    public void testClearNonemptyStack() {
        StackLL stack = new StackLL(new DNode(5));
        stack.push(new DNode(3));
        stack.push(new DNode(9));
        stack.clear();
        assertNull(stack.pop());
        assertEquals(0, stack.getSize());
    }

    /*
     * Tests for extraneous methods
     * (these should have no effect. 
     * if the method has a return value, it should return null)
     */

    @Test
    public void testExtraneousMethodsReturnNull() {
        int expected = 5;
        StackLL stack = new StackLL(new DNode(expected));
        assertNull("getHead() should return null", stack.getHead());
        assertNull("getTail() should return null", stack.getTail());
        assertNull("deleteHead() should return null", stack.deleteHead());
        assertNull("deleteTail() should return null", stack.deleteTail());
        
        // stack should still be in starting state
        assertEquals(1, stack.getSize());
        assertEquals(expected, stack.pop().getData());
    }

    @Test
    public void testExtraneousMethodsNoReturn() {
        int expected = 5;
        StackLL stack = new StackLL(new DNode(expected));

        DNode temp = new DNode(11);
        stack.insertHead(temp);
        stack.insertTail(new DNode(0));
        stack.insert(new DNode(0), 0);
        stack.sortedInsert(new DNode(0));
        stack.delete(temp);
        stack.sort();

        // stack should still be in starting state
        assertEquals(1, stack.getSize());
        assertEquals(expected, stack.pop().getData());
    }


}
