package myLib.datastructures.linear;
import myLib.datastructures.nodes.DNode;

/**
 * Class that implements a Stack, based off of a singly linked list
 * @author chantaeh
 */
public class StackLL extends SLL{
    /**
     * Default constructor
     */
    public StackLL() {
        super();
    }

     /**
     * Constructor with Node object argument
     * @param newNode   Node to use as head
     */
    public StackLL(DNode newNode) {
        super(newNode);
    }

    /**
     * Inserts Node object at top of stack
     * @param newNode
     */
    public void push(DNode newNode) {
        super.insertTail(newNode);
    }

    /**
     * Deletes the head node
     * @return deleted node
     */
    public DNode pop() {
        return super.deleteTail();
    }

    /**
     * Not a valid method for stack. 
     * @return null
     */
    @Override
    public DNode getHead() {
        return null;
    }

    /**
     * Not a valid method for stack. 
     * @return null
     */
    @Override
    public DNode getTail() {
        return null;
    }

    /**
     * Not a valid method for stack. 
     * @return null
     */
    @Override
    public DNode deleteHead() {
        return null;
    }

    /**
     * Not a valid method for stack. 
     * @return null
     */
    @Override
    public DNode deleteTail() {
        return null;
    }

    /**
     * Not a valid method for stack. Has no effect
     * @param newNode
     */
    @Override
    public void insertHead(DNode newNode) {}

    /**
     * Not a valid method for stack. Has no effect
     * @param newNode
     */
    @Override
    public void insertTail(DNode newNode) {}

    /**
     * Not a valid method for stack. Has no effect
     * @param newNode
     * @param position
     */
    @Override
    public void insert(DNode newNode, int position) {}

    /**
     * Not a valid method for stack. Has no effect
     * @param newNode
     */
    @Override
    public void sortedInsert(DNode newNode) {}

    /**
     * Not a valid method for stack. Has no effect
     * @param target
     */
    @Override
    public void delete(DNode target) {}

    /**
     * Not a valid method for stack. Has no effect
     */
    @Override
    public void sort() {}

    /**
     * Prints the stack information to output
     */
    @Override
    public void print() {
        System.out.println("\nQueue length: " + this.size);
        System.out.print("Stack content: [ ");
        String res = "";

        DNode current = head;
        while (current != null) {
            res += current.getData() + ", ";
            current = current.getNext();
        }
        if (!res.equals("")) {
            res = res.substring(0, res.length()-2);
        }

        res += " ]";
        System.out.println(res);
    }
}
