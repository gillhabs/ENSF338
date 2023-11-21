package myLib.datastructures.linear;
import myLib.datastructures.nodes.DNode;

/**
 * Class that implements a Circular Linked List, based of the DLL class
 * @author chantaeh
 */
public class CDLL extends DLL{
    /**
     * Default constructor
     */
    public CDLL() {
        super();
    }

    /**
     * Constructor with Node object argument
     * @param newNode   Node to use as head
     */
    public CDLL(DNode newNode) {
        super(newNode);
        head.setNext(head);
        head.setPrev(head);
    }

    /**
     * Inserts Node object at head of list.
     * @param newNode
     */
    @Override
    public void insertHead(DNode newNode) {
        if (head == null) {
            head = newNode;
            tail = head;
            head.setNext(head);
            head.setPrev(head);
        } else {
            head.setPrev(newNode);
            tail.setNext(newNode);
            
            newNode.setNext(head);
            newNode.setPrev(tail);
            
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
        if (tail == null) {
            head = newNode;
            tail = head;
            head.setNext(head);
            head.setPrev(head);
        } else {
            tail.setNext(newNode);
            head.setPrev(newNode);

            newNode.setPrev(tail);
            newNode.setNext(head);

            tail = newNode;
        }
        size += 1;
    }

    /* insert() method is from DLL */

    /**
     * Inserts Node object in its proper position in a sorted list
     * Checks for list sort validity
     * @param newNode
     */
    @Override
    public void sortedInsert(DNode newNode) {
        // check if list is sorted using isSorted() method
        boolean sorted = this.isSorted();
        if (!sorted) {
            this.sort();
        }

        // if list is not empty
        if (head != null) { 
            if (newNode.getData() < head.getData()) {
                insertHead(newNode);
            } else if (newNode.getData() > tail.getData()) {
                insertTail(newNode);
            } else {    // insertion is not at head or tail
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
            newNode.setNext(newNode);
            newNode.setPrev(newNode);
            head = newNode;
            tail = newNode;
            size = 1;
        }
    }

    /**
     * Determines whether the linked list is sorted
     * @return  true if the list is sorted, otherwise false
     */
    @Override
    protected boolean isSorted() {
        // If list is not empty
        if (head != null) {
            DNode before = head;
            DNode current = head.getNext();
            
            // compares every element in the list
            while (current != head) {
                if (before.getData() > current.getData()){
                    return false;
                }
                before = current;
                current = current.getNext();
            }
        }
        return true;
    }

    /**    
     * Applies insertion sort to the list, starting from the head
     */
    @Override
    public void sort() {
        if (head != null) {
            DNode unsorted = head.getNext();
            int numNodes = size;
            
            head.setNext(head);
            head.setPrev(head);
            tail = head;
            size = 1;

            // loop through every node in 'unsorted' list
            for (int i = 1; i < numNodes; i++) {
                if (unsorted.getData() < head.getData()) {
                    insertHead(new DNode(unsorted.getData()));
                } else if (unsorted.getData() > tail.getData()) {
                    insertTail(new DNode(unsorted.getData()));
                } else {
                    DNode current = head;
                    int j = -1;
                    
                    // find index to insert the node at
                    for (j = 0; j < this.size; j++) {
                        if (current.getData() > unsorted.getData()) {
                            break;
                        }
                        current = current.getNext();
                    }
                    insert(new DNode(unsorted.getData()), j);
                }
                unsorted = unsorted.getNext();                
            }

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
            if (size > 1) {   // If there is > 1 Node in the LL
                head = head.getNext();
                tail.setNext(head);
                head.setPrev(tail);
            } else {        // If there is 1 Node in the list
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
            if (size > 1) {
                // If there is > 1 Node in the LL
                tail = tail.getPrev();
                tail.setNext(head);
                head.setPrev(tail);
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
            if (size > 1) {
                boolean foundTarget = false;
                DNode current = head.getNext();
                // loop until find target or reach end of list
                while (current != head) {
                    if (current.getData() == targetData) {
                        foundTarget = true;
                        break;
                    }
                    before = current;
                    current = current.getNext();
                }
                
                // Delete the node if found, and adjust prev/next pointers
                if (foundTarget) {  
                    current.getPrev().setNext(current.getNext());
                    size -= 1;
                    if (current.getNext() == head) {    
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
