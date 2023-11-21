package myLib.datastructures.linear;
import myLib.datastructures.nodes.DNode;

/**
 * Class that implements a Doubly Linked List
 * @author chantaeh
 */
public class DLL extends SLL {
    /**
     * Default constructor
     */
    public DLL() {
        super();
    }

    /**
     * Constructor with Node object argument
     * @param newNode   Node to use as head
     */
    public DLL(DNode newNode) {
        super(newNode);
    }

     /**
     * Inserts Node object at head of list
     * @param newNode
     */
    @Override
    public void insertHead(DNode newNode) {
        if (head == null) {     // if list is empty
            head = newNode;
            tail = head;
        } else {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }
        size += 1;
    }

    /**
     * Inserts Node object at the tail of the list
     * @param newNode
     */
    @Override
    public void insertTail(DNode newNode) {
        if (tail == null) {     // if list is empty
            head = newNode;
            tail = head;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
            tail.setNext(null);
        }
        size += 1;
    }

    /**
     * Inserts Node object in the specified position
     * Throws IndexOutOfBoundsException if position is outside of the list
     * @param newNode
     * @param position
     */
    @Override
    public void insert(DNode newNode, int position) throws IndexOutOfBoundsException{
        if (position < 0 || position > this.size) {     // if position is out of bounds
            throw new IndexOutOfBoundsException();
        } else if (position == 0) {
            this.insertHead(newNode);
        } else if (position == this.size) {
            this.insertTail(newNode);
        } else {
            DNode before = head;
            // find the position to insert at
            for (int i = 0; i < position-1; i++) {
                before = before.getNext();
            }
            newNode.setNext(before.getNext());
            before.setNext(newNode);

            newNode.getNext().setPrev(newNode);
            newNode.setPrev(before);

            size += 1;
        }
    }

    /**
     * Inserts Node object in its proper position in a sorted list
     * Checks for list sort validity
     * @param newNode
     */
    @Override
    public void sortedInsert(DNode newNode) {
        // check if list is sorted using SLL isSorted() method
        boolean sorted = super.isSorted();
        if (!sorted) {
            this.sort();
        }

        if (head != null) {     // is list is not empty
            if (newNode.getData() < head.getData()) {
                insertHead(newNode);
            } else if (newNode.getData() > tail.getData()) {
                insertTail(newNode);
            } else {
                DNode before = head;
                for (int i = 0; i < size-1; i++) {
                    // find the location to insert at
                    if (before.getNext().getData() > newNode.getData()) {
                        break;
                    }
                }
                newNode.setNext(before.getNext());
                before.setNext(newNode);

                newNode.getNext().setPrev(newNode);
                newNode.setPrev(before);
                size += 1;
            }
        } else {
            newNode.setNext(null);
            newNode.setPrev(null);
            head = newNode;
            tail = newNode;
            size = 1;
        }
    }

    /**
     * Deletes the head Node
     */
    @Override
    public DNode deleteHead() {
        DNode temp = null;
        if (head != null) {
            temp = new DNode(head.getData());
            if (head.getNext() != null) {
                // If there is > 1 Node in the LL
                head = head.getNext();
                head.setPrev(null);
            } else {    
                // If there is 1 Node in the list
                head = null;
                tail = null;
            }
            size -= 1;  
        }
        return temp;
    }

    /**
     * Deletes the tail Node
     */
    @Override
    public DNode deleteTail() {
        DNode temp = null;
        if (tail != null) {
            temp = new DNode(tail.getData());
            if (head.getNext() != null) {
                // If there is > 1 Node in the LL
                tail = tail.getPrev();
                tail.setNext(null);
            } else {
                // If there is 1 Node in the list
                head = null;
                tail = null;
            }
            size -= 1;
        }
        return temp;
    }

    /**
     * Deletes the target Node if found in the list
     * @param target
     */
    @Override
    public void delete(DNode target) {
        int targetData = target.getData();
        if (head != null) {
            DNode before = head;
            if (head.getNext() != null) {
                boolean foundTarget = false;
                DNode current = head.getNext();
                
                // loop until find target or reach end of list
                while (current != null) {
                    if (current.getData() == targetData) {
                        foundTarget = true;
                        break;
                    }
                    before = current;
                    current = current.getNext();
                }
                
                // if target node found, adjust the prev/next pointers around it
                if (foundTarget) {  
                    current.getPrev().setNext(current.getNext());
                    size -= 1;
                    if (current.getNext() == null) {    // at end of list
                        tail = before;
                    } else {
                        current.getNext().setPrev(current.getPrev());
                    }
                }
            } else {
                if (before.getData() == targetData) {
                    head = null;
                    tail = null;
                    size = 0;
                }
            }
        }
    }
}
