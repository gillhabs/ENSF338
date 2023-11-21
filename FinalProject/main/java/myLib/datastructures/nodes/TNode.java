package myLib.datastructures.nodes;

/**
 * Node for BSTand AVL classes
 * @author chantaeh
 */

public class TNode {

    private int data;
    private int balance;
    private TNode left;
    private TNode right;
    private TNode parent;


    public TNode() {
        this.data = 0;
        this.balance = 0;
        this.parent = null;
        this.left = null;
        this.right = null;  
    }

    /*
     * Constructor
     * @param data  integer used in field
     * @param balance  balance of nose
     * @param P parnet node
     */
    public TNode(int data, int balance, TNode P, TNode L, TNode R) {
        this.data = data;
        this.balance = balance;
        this.parent = P;
        this.left = L;
        this.right = R;
    }

    /*
     * Getter for root data
     * @return
     */
    public int getData() {
        return this.data;
    }

    /*
     * Getter for balance data
     * @return
     */
    public int getBalance() {
        return this.balance;
    }

    /*
     * Getter for left subtree
     * @return
     */
    public TNode getLeft() {
        return this.left;
    }

    /*
     * Getter for right subtree
     * @return
     */
    public TNode getRight() {
        return this.right;
    }

    /*
    * Getter for the parent node
    * @return
    */
    public TNode getParent() {
        return this.parent;
    }

    /*
     * Setter for left subtree
     * @param left TNode at replacement
     */
    public void setLeft(TNode left) {
        this.left = left;
    }

    /* 
     * Setter for right subtree root
     * @param right right TNode at replacement
     */
    public void setRight(TNode right) {
        this.right = right;
    }

    /*
     * Setter for integer data
     * @param val   value to replace data with
     */
    public void setData(int val) {
        this.data = val;
    }

    /*
     * Setter for setBalabce) 
     * @param val   valut to be expedited
     * 
     */
    public void setBalance(int val) {
        this.balance = val;
    }

    /*
     * Prints the data of a certain node to the terminal in string form
     */
    public String toString() {
        String result = "";
        result.concat(String.valueOf(data));
        return result;
    }

    /*
     * Prints data of value, balance, parent left and right in the node
     */
    public void print() {
        System.out.println("Data is " + data);
        System.out.println("Balance is " + balance);
        if(left == null && right == null && parent == null){
            System.out.println("This node has no children ");
        }
        
        if(left != null) {
            System.out.println("Data of left TNode is " + left.getData());
        }
        else{}

        if(right != null) {
            System.out.println("Data of right TNode is " + right.getData());
        }
        else{}

        if(parent != null) {
            System.out.println("Data of parent TNode is " + parent.getData()); 
        }
    }
}


