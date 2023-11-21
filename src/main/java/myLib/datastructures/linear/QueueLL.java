package myLib.datastructures.linear;
import myLib.datastructures.nodes.DNode;

/**
 * Class that implements a Queue, based off of a singly linked list
 * @author chantaeh
 */
public class QueueLL extends SLL{
    /**
     * Default constructor
     */
    public QueueLL() {
        super();
    }

    /**
     * Constructor with Node object argument
     * @param newNode   Node to use as head
     */
    public QueueLL(DNode newNode) {
        super(newNode);
    }

    /**
     * Inserts Node object at back of queue
     * @param newNode
     */
    public void enqueue(DNode newNode) {
        super.insertTail(newNode);
    }

    /**
     * Removes and returns the Node at the front of the queue
     * @return deleted node
     */
    public DNode dequeue() {
        return super.deleteHead();
    }

    /**
     * Returns the Node at the front of the queue, without removing it
     * @return
     */
    public DNode peek() {
        return super.getHead();
    }

    /**
     * Not a valid method for queue.
     * @return null
     */
    @Override
    public DNode getHead() {
        return null;
    }

    /**
     * Not a valid method for queue.
     * @return null
     */
    @Override
    public DNode getTail() {
        return null;
    }

    /**
     * Not a valid method for queue.
     * @return null
     */
    @Override
    public DNode deleteHead() {
        return null;
    }

    /**
     * Not a valid method for queue.
     * @return null
     */
    @Override
    public DNode deleteTail() {
        return null;
    }

    /**
     * Not a valid method for queue. Has no effect
     * @param newNode
     */
    @Override
    public void insertHead(DNode newNode) {}

    /**
     * Not a valid method for queue. Has no effect
     * @param newNode
     */
    @Override
    public void insertTail(DNode newNode) {}

    /**
     * Not a valid method for queue. Has no effect
     * @param newNode
     * @param position
     */
    @Override
    public void insert(DNode newNode, int position) {}

    /**
     * Not a valid method for queue. Has no effect
     * @param newNode
     */
    @Override
    public void sortedInsert(DNode newNode) {}

    /**
     * Not a valid method for queue. Has no effect
     * @param newNode
     */
    @Override
    public void delete(DNode target) {}

    /**
     * Not a valid method for queue. Has no effect
     * @param newNode
     */
    @Override
    public void sort() {}

    /**
     * Prints the queue information to output
     */
    @Override
    public void print() {
        System.out.println("\nQueue length: " + this.size);
        System.out.print("Queue content: [ ");
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
