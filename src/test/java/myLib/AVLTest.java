package myLib;

import org.junit.Test;
import static org.junit.Assert.*;
import myLib.datastructures.trees.AVL;
import myLib.datastructures.nodes.TNode;

/**
 * Unit tests for the AVL class
 * @author gillhabs
 */
public class AVLTest {

    // Test classname
    @Test
    public void testClassName() {

        Class aClass = AVL.class;
        String simpleClassName = aClass.getSimpleName();
        assertEquals("AVL", simpleClassName);
    }

    // Test default constructor BST()
    @Test
    public void testDefaultConstructor() {

        AVL avl = new AVL();
        assertEquals(null, avl.getRoot());
    }

    // Test AVL(int val)
    @Test
    public void testConstructorIntArg() {

        AVL avl = new AVL(11);

        assertEquals(0, avl.getRoot().getBalance() );
        assertEquals(11, avl.getRoot().getData());
    }

    // Test AVL(TNode node)
    @Test
    public void testConstructorNodeArg() {

        TNode expected = new TNode(11, 0, null, null, null);
        AVL avl = new AVL(expected);

        assertEquals(0, avl.getRoot().getBalance() );
        assertEquals(11, avl.getRoot().getData());
    }

    // Test AVL(TNode node) with rebalancing
    @Test
    public void testConstructorNodeArgRebalance() {

        TNode leftChild = new TNode(2, 0, null, null, null);
        TNode expected = new TNode(11, 0, null, leftChild, null);
        AVL avl = new AVL(expected);

        assertEquals(0, avl.getRoot().getBalance() );
        assertEquals(11, avl.getRoot().getData());
        assertEquals(2, avl.getRoot().getLeft().getData());
    }


   /*
    * Test getters and setters
    */

    // Test getRoot()
    @Test
    public void testGetRoot() {

        TNode expectedRoot = new TNode(11, 0, null, null, null);
        AVL avl = new AVL(expectedRoot);

        assertEquals(expectedRoot, avl.getRoot());
    }

    // Test setRoot()
    @Test
    public void testSetRoot() {

        TNode expectedRoot = new TNode(11, 0, null, null, null);
        AVL avl = new AVL();
        avl.setRoot(expectedRoot);

        assertEquals(expectedRoot, avl.getRoot());
        assertEquals(11, avl.getRoot().getData());
    }

    // Test setRoot() with rebalancing
    @Test
    public void testSetRootRebalance() {

        TNode leftChild = new TNode(1, 0, null, null, null);
        TNode rightChild = new TNode(2, 0, null, null, null);
        TNode expectedRoot = new TNode(11, 0, null, leftChild, rightChild);
        AVL avl = new AVL();
        avl.setRoot(expectedRoot);

        assertEquals(expectedRoot, avl.getRoot());
        assertEquals(11, avl.getRoot().getData());
        assertEquals(leftChild.getData(), avl.getRoot().getLeft().getData());
        assertEquals(rightChild.getData(), avl.getRoot().getRight().getData());
    }

    /*
    * Tests for insertion
    */

    // Insertion into empty tree
    // Using insert(TNode node)
    @Test
    public void testInsertEmptyTreeNode() {

        AVL avl = new AVL();
        TNode expectedRoot = new TNode(11, 0, null, null, null);
        avl.insert(expectedRoot);

        assertEquals(expectedRoot.getData(), avl.getRoot().getData());
    }

    // Insertion into non-empty tree constructed with AVL(int val)
    // Using insert(TNode node)
    @Test
    public void testInsertNonEmptyTreeNode() {

        AVL avl = new AVL(2);
        TNode expectedChild = new TNode(11, 0, null, null, null);
        avl.insert(expectedChild);

        assertEquals(expectedChild.getData(), avl.getRoot().getRight().getData());
    }

    // Insertion into non-empty tree constructed with AVL(TNode node)
    // Using insert(TNode node)
    @Test
    public void testInsertNonEmptyTreeNodeArgNode() {

        TNode expectedRoot = new TNode(10, 0, null, null, null);
        AVL avl = new AVL(expectedRoot);
        TNode expectedChild = new TNode(11, 0, null, null, null);
        avl.insert(expectedChild);

        assertEquals(expectedChild.getData(), avl.getRoot().getRight().getData());
    }

    // Insertion into non-empty tree constructed with AVL(TNode node)
    // Using insert(TNode node) when rebalancing is needed
    @Test
    public void testInsertNonEmptyTreeNodeArgNodeRebalance() {

        TNode leftChild = new TNode(2, 0, null, null, null);
        TNode expectedRoot = new TNode(10, 0, null, leftChild, null);
        AVL avl = new AVL(expectedRoot);
        TNode expectedChild = new TNode(11, 0, null, null, null);
        avl.insert(expectedChild);

        assertEquals(expectedChild.getData(), avl.getRoot().getRight().getData());
        assertEquals(leftChild.getData(), avl.getRoot().getLeft().getData());
    }
    
    // Insertion into empty tree
    // Using insert(int val)
    @Test
    public void testInsertEmptyTreeVal() {

        AVL avl = new AVL();
        TNode expectedRoot = new TNode(11, 0, null, null, null);
        avl.insert(11);

        assertEquals(expectedRoot.getData(), avl.getRoot().getData());
    }

    // Insertion into non-empty tree constructed with AVL(int val)
    // Using insert(int val)
    @Test
    public void testInsertNonEmptyTreeVal() {

        AVL avl = new AVL(2);
        TNode expectedChild = new TNode(11, 0, null, null, null);
        avl.insert(11);

        assertEquals(expectedChild.getData(), avl.getRoot().getRight().getData());
    }

    // Insertion into non-empty tree constructed with AVL(TNode node)
    // Using insert(int val)
    @Test
    public void testInsertNonEmptyTreeNodeArgVal() {

        TNode expectedRoot = new TNode(10, 0, null, null, null);
        AVL avl = new AVL(expectedRoot);
        TNode expectedChild = new TNode(11, 0, null, null, null);
        avl.insert(11);

        assertEquals(expectedChild.getData(), avl.getRoot().getRight().getData());
    }


    /*
    * Tests for deletion
    */

    // Deletion from empty tree 
    @Test 
    public void testDeleteEmptyTree() {

        AVL avl = new AVL();
        avl.delete(11);
        AVL expectedavl = null;

        assertEquals(expectedavl, avl.getRoot());
    }

    // Deletion from emptry tree constructed with AVL(TNode node)
    @Test
    public void testDeleteNonEmptyTreeNode() {

        TNode expectedDeletion = new TNode(2, 0, null, null, null);
        TNode expectedRoot = new TNode(11, 0, null, expectedDeletion, null);
        AVL avl = new AVL(expectedRoot);
        avl.delete(2);

        assertEquals(expectedRoot.getData(), avl.getRoot().getData());
    }

    // Deletion from emptry tree constructed with AVL(int val)
    @Test
    public void testDeleteNonEmptyTreeVal() {

        TNode expectedRoot = new TNode(11, 0, null, null, null);
        AVL avl = new AVL(11);
        avl.insert(2);
        avl.delete(2);

        assertEquals(expectedRoot.getData(), avl.getRoot().getData());
    }

    /*
    * Tests for searching
    */

    // Search for a value in a empty tree
    @Test 
    public void testSearchEmptyTree() {

        AVL avl = new AVL();
        TNode actualSearch = avl.search(5);
        TNode expectedSearch = null;

        assertEquals(expectedSearch, actualSearch);
    }

    // Search for a value construced with a TNode arg that 
    // should be a success
    @Test
    public void testSearchNonEmptyTreeNodeSuccess() {

        TNode rootNode = new TNode(11, 0, null, null, null);
        AVL avl = new AVL(rootNode);

        TNode actualSearch = avl.search(11);
        TNode expectedSearch = rootNode;

        assertEquals(expectedSearch, actualSearch);
        assertEquals(expectedSearch.getData(), actualSearch.getData());
    }

    // Search for a value constructed with an TNode arg that 
    // should be a fail
    @Test
    public void testSearchNonEmptyTreeNodeFail() {

        TNode rootNode = new TNode(11, 0, null, null, null);
        AVL avl = new AVL(rootNode);

        TNode actualSearch = avl.search(2);
        TNode expectedSearch = null;

        assertEquals(expectedSearch, actualSearch);
    }

    // Search for a value constructed with an int val arg that
    // should be a success
    @Test
    public void testSearchNonEmptyTreeValSuccess() {

        AVL avl = new AVL(11);

        TNode actualSearch = avl.search(11);
        TNode expectedSearch = new TNode(11, 0, null, null, null);

        assertEquals(expectedSearch.getData(), actualSearch.getData());
    }

    // Search for a value constructed with an inst val arg that
    // should be a fail
    @Test
    public void testSearchNonEmptyTreeValFail() {

        AVL avl = new AVL(11);

        TNode actualSearch = avl.search(2);
        TNode expectedSearch = null;

        assertEquals(expectedSearch, actualSearch);
    }
}
