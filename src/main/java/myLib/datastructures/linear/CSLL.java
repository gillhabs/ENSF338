package myLib.datastructures.linear;
import myLib.datastructures.nodes.DNode;

/**
 * Class that implements a circular singly LL, based off of a singly LL
 * @author chantaeh
 */
public class CSLL extends SLL{
    /**
     * Default constructor
     */
    public CSLL() {
        super();
    }

    /**
     * Constructor with Node object argument
     * @param newNode   Node to use as head
     */
    public CSLL(DNode newNode) {
        super(newNode);
        head.setNext(head);
    }

    /**
     * Inserts Node object at head of list
     * @param newNode
     */
    @Override
    public void insertHead(DNode newNode) {
        if (head == null) { // if list is empty
            head = newNode;
            head.setNext(head);
            tail = head;
        } else {
            newNode.setNext(head);
            head = newNode;
            tail.setNext(head);
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
            head.setNext(head);
            tail = head;
        } else {
            tail.setNext(newNode);
            tail = newNode;
            tail.setNext(head);
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
            insertHead(newNode);
        } else if (position == this.size) {
            insertTail(newNode);
        } else {
            // find position to insert at
            DNode before = head;
            for (int i = 0; i < position-1; i++) {
                before = before.getNext();
            }
            newNode.setNext(before.getNext());
            before.setNext(newNode);
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
        // check if list is sorted
        boolean sorted = this.isSorted();
        if (!sorted) {
            this.sort();
        }

        if (head != null) {     // if list is not empty
            if (newNode.getData() < head.getData()) {
                insertHead(newNode);
            } else if (newNode.getData() > tail.getData()) {
                insertTail(newNode);
            } else {
                DNode before = head;
                while (before != tail) {
                    // find the location to insert at
                    if (before.getNext().getData() > newNode.getData()) {
                        break;
                    }
                }
                newNode.setNext(before.getNext());
                before.setNext(newNode);
                size += 1;
            }
        } else {
            head = newNode;
            tail = newNode;
            head.setNext(head);
            size = 1;
        }
    }

    /**
     * Determines whether the linked list is sorted
     * @return  true if the list is sorted, otherwise false
     */
    @Override
    protected boolean isSorted() {
        if (head != null) {
            DNode before = head;
            DNode current = head.getNext();
            // compares every element in the list to check if the list is sorted
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
     * Deletes the head Node
     * @return deleted node
     */
    @Override
    public DNode deleteHead() {
        DNode temp = null;
        if (head != null) {
            temp = new DNode(head.getData());
            if (size > 1) {     // If there is > 1 Node in the LL
                head = head.getNext();
                tail.setNext(head);
            } else {    // If there is 1 Node in the list
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
                DNode before = head;
                while (before.getNext() != tail) {
                    before = before.getNext();
                }
                before.setNext(head);
                tail = before;
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
     * @param target Node to find
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
                
                // if target node is found, delete it and fix the next pointers
                if (foundTarget) {  
                    before.setNext(current.getNext());
                    size -= 1;
                    if (current.getNext() == head) {
                        tail = before;
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

    /**
     * Applies insertion sort to the list, starting from the head
     */
    @Override
    public void sort() {
        // if the list is not empty
        if (head != null) {
            DNode unsorted = head.getNext();
            int numNodes = size;

            head.setNext(head);
            tail = head;
            size = 1;

            // loop for every element in the 'unsorted' list
            for (int i = 1; i < numNodes; i++) {
                if (unsorted.getData() < head.getData()) {
                    insertHead(new DNode(unsorted.getData()));
                } else if (unsorted.getData() > tail.getData()) {
                    insertTail(new DNode(unsorted.getData()));
                } else {
                    DNode current = head;
                    int j = -1;

                    // find index to insert the node
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
}
