package myLib.datastructures.nodes;

/**
 * Node for Singly Linked List (SLL) and Doubly Linked List (DLL) classes & subclasses
 * @author chantaeh
 */
public class DNode {
    private int data;
    private DNode next;
    private DNode prev;

    /**
     * Constructor
     * @param data  Data to put in the node
     */
    public DNode(int data) {
        this.data = data;
        next = null;
        prev = null;
    }

    /**
     * Getter for node data
     * @return  node's data
     */
    public int getData() {
        return data;
    }
    
    /**
     * Getter for next node
     * @return  node's next node
     */
    public DNode getNext() {
        return next;
    }

    /**
     * Setter for next node
     * @param next  
     */
    public void setNext(DNode next) {
        this.next = next;
    }

    /**
     * Getter for previous node
     * @return  node's prev node
     */
    public DNode getPrev() {
        return prev;
    }

    /**
     * Setter for previous node
     * @param prev
     */
    public void setPrev(DNode prev) {
        this.prev = prev;
    }
}
