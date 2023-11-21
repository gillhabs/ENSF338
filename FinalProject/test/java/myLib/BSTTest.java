package myLib;

import org.junit.Test;
import static org.junit.Assert.*;
import myLib.datastructures.trees.BST;
import myLib.datastructures.nodes.TNode;

/**
 * Unit tests for the BST class
 * @author gillhabs
 */
public class BSTTest {

    // Test classname
    @Test
    public void testClassName() {

        Class aClass = BST.class;
        String simpleClassName = aClass.getSimpleName();
        assertEquals("BST", simpleClassName);
    }

    // Test default constructor BST()
    @Test
    public void testDefaultConstructor() {

        BST bst = new BST();
        assertEquals(null, bst.getRoot());
    }

    // Test BST(int val)
    @Test
    public void testConstructorIntArg() {

        BST bst = new BST(11);

        assertEquals(0, bst.getRoot().getBalance() );
        assertEquals(11, bst.getRoot().getData());
    }

    // Test BST(TNode node)
    @Test
    public void testConstructorNodeArg() {

        TNode expected = new TNode(11, 0, null, null, null);
        BST bst = new BST(expected);

        assertEquals(0, bst.getRoot().getBalance() );
        assertEquals(11, bst.getRoot().getData());
    }

   /*
    * Test getters and setters
    */

    // Test getRoot()
    @Test
    public void testGetRoot() {

        TNode expectedRoot = new TNode(11, 0, null, null, null);
        BST bst = new BST(expectedRoot);

        assertEquals(expectedRoot, bst.getRoot());
    }

    // Test getRoot()
    @Test
    public void testSetRoot() {

        TNode expectedRoot = new TNode(11, 0, null, null, null);
        BST bst = new BST();
        bst.setRoot(expectedRoot);

        assertEquals(expectedRoot, bst.getRoot());
        assertEquals(11, bst.getRoot().getData());
    }

    /*
    * Tests for insertion
    */

    // Insertion into empty tree
    // Using insert(TNode node)
    @Test
    public void testInsertEmptyTreeNode() {

        BST bst = new BST();
        TNode expectedRoot = new TNode(11, 0, null, null, null);
        bst.insert(expectedRoot);

        assertEquals(expectedRoot.getData(), bst.getRoot().getData());
    }

    // Insertion into non-empty tree constructed with BST(int val)
    // Using insert(TNode node)
    @Test
    public void testInsertNonEmptyTreeNode() {

        BST bst = new BST(2);
        TNode expectedChild = new TNode(11, 0, null, null, null);
        bst.insert(expectedChild);

        assertEquals(expectedChild.getData(), bst.getRoot().getRight().getData());
    }

    // Insertion into non-empty tree constructed with BST(TNode node)
    // Using insert(TNode node)
    @Test
    public void testInsertNonEmptyTreeNodeArgNode() {

        TNode expectedRoot = new TNode(10, 0, null, null, null);
        BST bst = new BST(expectedRoot);
        TNode expectedChild = new TNode(11, 0, null, null, null);
        bst.insert(expectedChild);

        assertEquals(expectedChild.getData(), bst.getRoot().getRight().getData());
    }

        // Insertion into empty tree
    // Using insert(int val)
    @Test
    public void testInsertEmptyTreeVal() {

        BST bst = new BST();
        TNode expectedRoot = new TNode(11, 0, null, null, null);
        bst.insert(11);

        assertEquals(expectedRoot.getData(), bst.getRoot().getData());
    }

    // Insertion into non-empty tree constructed with BST(int val)
    // Using insert(int val)
    @Test
    public void testInsertNonEmptyTreeVal() {

        BST bst = new BST(2);
        TNode expectedChild = new TNode(11, 0, null, null, null);
        bst.insert(11);

        assertEquals(expectedChild.getData(), bst.getRoot().getRight().getData());
    }

    // Insertion into non-empty tree constructed with BST(TNode node)
    // Using insert(int val)
    @Test
    public void testInsertNonEmptyTreeNodeArgVal() {

        TNode expectedRoot = new TNode(10, 0, null, null, null);
        BST bst = new BST(expectedRoot);
        TNode expectedChild = new TNode(11, 0, null, null, null);
        bst.insert(11);

        assertEquals(expectedChild.getData(), bst.getRoot().getRight().getData());
    }

    /*
    * Tests for deletion
    */

    // Deletion from empty tree 
    @Test 
    public void testDeleteEmptyTree() {

        BST bst = new BST();
        bst.delete(11);
        BST expectedbst = null;

        assertEquals(expectedbst, bst.getRoot());
    }

    // Deletion from emptry tree constructed with BST(TNode node)
    @Test
    public void testDeleteNonEmptyTreeNode() {

        TNode expectedDeletion = new TNode(2, 0, null, null, null);
        TNode expectedRoot = new TNode(11, 0, null, expectedDeletion, null);
        BST bst = new BST(expectedRoot);
        bst.delete(2);

        assertEquals(expectedRoot.getData(), bst.getRoot().getData());
    }

    // Deletion from emptry tree constructed with BST(int val)
    @Test
    public void testDeleteNonEmptyTreeVal() {

        TNode expectedRoot = new TNode(11, 0, null, null, null);
        BST bst = new BST(11);
        bst.insert(2);
        bst.delete(2);

        assertEquals(expectedRoot.getData(), bst.getRoot().getData());
    }

    /*
    * Tests for searching
    */

    // Search for a value in a empty tree
    @Test 
    public void testSearchEmptyTree() {

        BST bst = new BST();
        TNode actualSearch = bst.search(5);
        TNode expectedSearch = null;

        assertEquals(expectedSearch, actualSearch);
    }

    // Search for a value construced with a TNode arg that 
    // should be a success
    @Test
    public void testSearchNonEmptyTreeNodeSuccess() {

        TNode rootNode = new TNode(11, 0, null, null, null);
        BST bst = new BST(rootNode);

        TNode actualSearch = bst.search(11);
        TNode expectedSearch = rootNode;

        assertEquals(expectedSearch, actualSearch);
        assertEquals(expectedSearch.getData(), actualSearch.getData());
    }

    // Search for a value constructed with an TNode arg that 
    // should be a fail
    @Test
    public void testSearchNonEmptyTreeNodeFail() {

        TNode rootNode = new TNode(11, 0, null, null, null);
        BST bst = new BST(rootNode);

        TNode actualSearch = bst.search(2);
        TNode expectedSearch = null;

        assertEquals(expectedSearch, actualSearch);
    }

    // Search for a value constructed with an int val arg that
    // should be a success
    @Test
    public void testSearchNonEmptyTreeValSuccess() {

        BST bst = new BST(11);

        TNode actualSearch = bst.search(11);
        TNode expectedSearch = new TNode(11, 0, null, null, null);

        assertEquals(expectedSearch.getData(), actualSearch.getData());
    }

    // Search for a value constructed with an inst val arg that
    // should be a fail
    @Test
    public void testSearchNonEmptyTreeValFail() {

        BST bst = new BST(11);

        TNode actualSearch = bst.search(2);
        TNode expectedSearch = null;

        assertEquals(expectedSearch, actualSearch);
    }
}
