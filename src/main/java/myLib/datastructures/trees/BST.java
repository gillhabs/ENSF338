package myLib.datastructures.trees;
import myLib.datastructures.nodes.TNode;

/**
 * Class that implements a Binary Search Tree (BST)
 * @author gillhabs
 */

public class BST{

    protected TNode root; 

    // Default constructor
    public BST(){ 
        this.root = null; 
    } 

    /*
     * Constructor with integer value argument
     * @param val  value to create tree with 
     */
    public BST(int val) {        
        int balance = 0;
        TNode parent = null;
        TNode left = null;
        TNode right = null;  
        this.root = new TNode(val, balance, parent, left, right);
    }

    /*
     * Constructor with new TNode argument
     * @param obj node to create tree with 
     */
    public BST(TNode obj) {
        this.root = obj;
    }

    /*
     * Getter for root node
     * @return 
     */
    public TNode getRoot() {
        return this.root;
    }

    /*
     * setter for root node
     * @param root node to be made into the root
     */
    public void setRoot(TNode root) {
        this.root = root;
    }

    /*
     * Will insert a node at the end of the tree
     * @param node  node to be added to tree
     */
    public void insert(TNode node) {

        TNode newNode = node;

        // If root is null make the node the root
        if(root == null) {
            this.root = newNode;
            return;
        }

        TNode prevNode = null;
        TNode tempNode = root;

        // While we have not reached the end of the path
        while(tempNode != null) {

            // Go into left subtree
            if(tempNode.getData() > node.getData()) {
                prevNode = tempNode;
                tempNode = tempNode.getLeft();
            }

            // Go into right subtree
            else if(tempNode.getData() < node.getData()) {
                prevNode = tempNode;
                tempNode = tempNode.getRight();
            }
        }

        // Insert node at either left or right
        if(prevNode.getData() > node.getData()) {
            prevNode.setLeft(newNode);
        }
        
        else {
            prevNode.setRight(newNode);
        }   
    }

    /*
     * Will insert a value at the end of the tree in a node
     * @param val  value to be added to tree
     */
    public void insert(int val)  { 
        
        // Create new node with value to be used 
        int balance = 0;
        TNode parent = null;
        TNode left = null;
        TNode right = null;
        TNode newNode = new TNode(val, balance, parent, left, right); 

        // If the root is null make the new node the root
        if(root == null) {
            this.root = newNode;
            return;
        }

        TNode prevNode = null;
        TNode tempNode = root;

        // While we have not reached the end of the path
        while(tempNode != null) {

            // Go into left subtree
            if(tempNode.getData() > val) {
                prevNode = tempNode;
                tempNode = tempNode.getLeft();
            }
            
            // Go into right subtree
            else if(tempNode.getData() < val) {
                prevNode = tempNode;
                tempNode = tempNode.getRight();
            }
        }

        // Insert node at either left or right
        if(prevNode.getData() > val) {
            prevNode.setLeft(newNode);
        }
        
        else {
            prevNode.setRight(newNode);
        }
    }
    

    /*
     * Checks if given value is in the BST or not
     * @param val   integer value to check for
     * @param root  tree root; starting point for checking
     */
    private static boolean contains(TNode root, int val) {
        
        if (root == null) {
            return false;
        }

        else if (root.getData() == val) {
            return true;
        }

        else if (root.getData() > val) {
            return contains(root.getLeft(), val);
        }

        else {
            return contains(root.getRight(), val);
        }
    }

    /*
     * Deleted node whose data is the given integer value
     * @param val   integer value to be deleted
     */
    public void delete(int val) { 

        boolean containsVal = contains(root, val);

        if(containsVal == true) {
            root = delete_Recursive(root, val);
        }

        else {
            System.out.println("Cannot delete node " + val + " because it doesn't exist");
        }
    } 

    /*
     * Recursive function to delete based of the node and value
     * @param node  node that is current the root of the traversal
     * @param val   value that is currently the root value
     * @return
     */
    private TNode delete_Recursive(TNode node, int val)  { 

        // If tree is empty
        if (node == null) { 
            return node; 
        }

        // Traverse left subtree
        if (val < node.getData()) {   
            root.setLeft(delete_Recursive(node.getLeft(), val)); 
        }

        // Traverse right subtree
        else if (val > node.getData()) {
            node.setRight(delete_Recursive(node.getRight(), val)); 
        }

        else  { 

            // Node contains one child
            if (node.getLeft() == null) {
                return node.getRight(); 
                
            }

            else if (node.getRight() == null) {
                return node.getLeft(); 
            }


            int minval = node.getData(); 
            while (node.getLeft() != null)  { 
                minval = node.getLeft().getData(); 
                node = node.getLeft();
            } 
            node.setData(minval); 
        } 


        // Delete the inorder successor 
        node.setRight(delete_Recursive(node.getRight(), node.getData()));

    
        return node;
    } 
    
    /*
     * Search for a node data point based off it's integer value
     * @param val   value we are searching the tree for
     * @return
     */
    public TNode search(int val)  { 
        root = searchRecursively(root, val); 
        return root;
    } 
        
    /*
     * Recursive function to search for a given value in a tree
     * @param root  node that is currently the root of the traversal
     * @param val   integer value that is currently at the root
     * @return  
     */
    private TNode searchRecursively(TNode root, int val)  { 

        // Root is null or val is present at root 
        if (root==null || root.getData() == val) { 
            return root; 
        }

        // val >= root.data
        if (root.getData() > val) {
            return searchRecursively(root.getLeft(), val); 
        }
        // val < root.data
        return searchRecursively(root.getRight(), val); 
    } 

    /*
     * Prints tree values in ascending order
     */
    public void printInOrder() { 
        printInOrder_Recursive(root); 
    } 

    /*
     * Prints values in ascending order throught the use of recursion
     * @param node  node we are currently at in the traversal
     */
    private void printInOrder_Recursive(TNode root) { 
     
        // If the current point is not a dead end
        if (root != null) { 

            // Recursively print data at the point and 
            // call the function for the left and right subtrees
            printInOrder_Recursive(root.getLeft());
            System.out.println(root.toString());
            System.out.print(root.getData() + " "); 
            printInOrder_Recursive(root.getRight()); 
        } 
    }

    /*
    * Print out the tree using a breadth-first approach
    */
    public void printBF() {

        int height = height(root);

        for (int i = 1; i <= height; i++) {
            printAtLevel(root, i);
            System.out.println();
        }
    }
 
    /*
    * Recursivelt getting the height of the tree to help the printBF() function
    * @param root   current root at the point in the traversal
    * @return 
    */
    private int height(TNode root)
    {
        // If we are at the end of our path
        if (root == null) {
            return 0;
        }

        else {

            // Recursively getting the height of the left and right
            // subtrees of the root and updating our return
            int leftHeight = height(root.getLeft());
            int rightHeight = height(root.getRight());
 
            if (leftHeight > rightHeight) {
                return (leftHeight + 1);
            }

            else {
                return (rightHeight + 1);
            }
        }
    }

    /*
     * Prints the given nodes at each height in the tree
     * @param root  point we are currently at in the traversal
     * @param level level of the tree we are currently at in the traversal
     */
    private void printAtLevel(TNode root, int level)
    {
        // If we are at the end of the path
        if (root == null) {
            return;
        }

        // If the level is 1 (top of tree)
        if (level == 1) {
            System.out.print(root.getData() + " ");
        }

        // Else print left and right subtree root data from the current root
        else if (level > 1) {
            printAtLevel(root.getLeft(), level - 1);
            printAtLevel(root.getRight(), level - 1);
        }
    }
} 
