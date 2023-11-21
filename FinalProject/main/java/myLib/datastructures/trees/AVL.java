package myLib.datastructures.trees;
import myLib.datastructures.nodes.TNode;

/**
 * Class that implements an AVL tree
 * @author gillhabs
 */

public class AVL extends BST {
    /**
     * Default constructor
     */
    public AVL() {
        super();
    }

    /**
     * Constructor that takes an integer value
     * @param val
     */
    public AVL(int val) {
        super(val);
    }

    /**
     * Constructor that takes a TNode object
     * @param obj
     */
    public AVL(TNode obj) {
        super(obj);
        rebalance(obj);
    }

    /**
     * Returns the height of a node
     * @param node
     * @return
     */
	private int height(TNode node) {
		if (node == null)
			return 0;

		return node.getBalance();
	}

    /**
     * Getter for root
     */
    @Override 
    public TNode getRoot() {
        return this.root;
    }
    
    /**
     * Setter for root
     */
    @Override
    public void setRoot(TNode root) {
        this.root = root;
        rebalance(root);
    }

    /**
     * Performs a right rotation at the given node
     * @param node  current root node for the subtree
     * @return  new root node for the subtree
     */
	private TNode rotateRight(TNode node) {

        // Create temp nodes
		TNode tempNode = node.getLeft();
		TNode tempNode2 = tempNode.getRight();

        // Perform rotation
		tempNode.setRight(node);
		node.setLeft(tempNode2);

        // Update heights
		node.setBalance(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
		tempNode.setBalance(Math.max(height(tempNode.getLeft()), height(tempNode.getRight())) + 1);

		return tempNode;
	}

    /**
     * Performs a left rotation at the given node
     * @param node  current root node for the subtree
     * @return new root node for the subtree
     */
	private TNode rotateLeft(TNode node) {

        // Create temp nodes
		TNode tempNode = node.getRight();
		TNode tempNode2 = tempNode.getLeft();

		// Perform rotation
		tempNode.setLeft(node);
		node.setRight(tempNode2);

		// Update heights
		node.setBalance(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
		tempNode.setBalance(Math.max(height(tempNode.getLeft()), height(tempNode.getRight())) + 1);

		// Return new root
		return tempNode;
	}

    /**
     * Calculates the balance of a node
     * @param node
     * @return
     */
	private int getBalance(TNode node) {
		if (node == null)
			return 0;

		return height(node.getLeft()) - height(node.getRight());
	}

    /**
     * Full tree rebalancing algorithm
     * @param node
     * @return
     */
    private TNode rebalance(TNode node) {

        // Get balance
        int balance = getBalance(node);
 
        // Left Left Case
        if (balance > 1 && getBalance(node.getLeft()) >= 0) {
            return rotateRight(node);
        }
 
        // Left Right Case
        if (balance > 1 && getBalance(node.getLeft()) < 0) {
            root.setLeft(rotateLeft(node.getLeft()));
            return rotateRight(node);
        }
 
        // Right Right Case
        if (balance < -1 && getBalance(node.getRight()) <= 0) {
            return rotateLeft(node);
        }
 
        // Right Left Case
        if (balance < -1 && getBalance(node.getRight()) > 0){
            root.setRight(rotateRight(node.getRight()));
            return rotateLeft(node);
        }

        return node;
 
    }

    /**
     * Inserts a new node into the tree, given an integer
     * @param val integer value to insert
     */
    @Override
    public void insert(int val) {
        root = insertRecursively(root, val);
    }

    /**
     * Inserts a new node into the tree, given the node
     * @param node New node to insert
     */
    @Override
    public void insert(TNode node) {
        root = insertRecursively(root, node.getData());
    }

    /**
     * Recursively inserts a new node into the tree
     * @param node  root node
     * @param val   new value to insert
     * @return
     */
	private TNode insertRecursively(TNode node, int val) {

        // Insert val
		if (node == null) {
			return (new TNode(val, 0, null, null, null));
        }

		if (val < node.getData()) {
			node.setLeft(insertRecursively(node.getLeft(), val));
        }

		else if (val > node.getData()) {
			node.setRight(insertRecursively(node.getRight(), val));
        }

		else {
			return node;
        }
        
        // Update balance
		node.setBalance(Math.max(height(node.getLeft()), height(node.getRight())) + 1);

        return rebalance(node);
	}

    /**
     * Deletes a given node from the tree
     * @param val integer value to delete
     */
    @Override 
    public void delete(int val) {
        root = deleteRecursively(root, val);
    }

    /**
     * Recursively deletes a node
     * @param root  root node
     * @param val   integer data for node to delete
     * @return
     */
    private TNode deleteRecursively(TNode root, int val) 
    {   
        // Delete node
        if (root == null) {
            System.out.println("Cannot delete node " + val + " because it doesn't exist");
            return root;
        }
 
        if (val < root.getData()) {
            root.setLeft(deleteRecursively(root.getLeft(), val));
        }
 
        else if (val > root.getData()) {
            root.setRight(deleteRecursively(root.getRight(), val));
        }
 
        else {
 
            // 0-1 children
            if ((root.getLeft() == null) || (root.getRight() == null)) {

                TNode tempNode = null;

                if (tempNode == root.getLeft()) {
                    tempNode = root.getRight();
                }

                else {
                    tempNode = root.getLeft();
                }
 
                // 0 children
                if (tempNode == null) {
                    tempNode = root;
                    root = null;
                }

                // 1 child
                else {
                    root = tempNode; 
                }
            }

            else {
                TNode tempNode = root.getRight();
 
                while (tempNode.getLeft() != null) {
                    tempNode = tempNode.getLeft();
                }
        
                root.setData(tempNode.getData());
 
                root.setRight(deleteRecursively(root.getRight(), tempNode.getData()));
            }
        }
 
        if (root == null) {
            return root;

        }

        // Update balance
        root.setBalance(Math.max(height(root.getLeft()), height(root.getRight())) + 1);

        return rebalance(root);
      
    }
}




