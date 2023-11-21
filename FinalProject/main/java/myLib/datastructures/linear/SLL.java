package myLib.datastructures.linear;
import myLib.datastructures.nodes.DNode;

/**
 * Class that manually implements a Singly Linked List
 * @author chantaeh
 */
public class SLL {
    protected DNode head;
    protected DNode tail;
    protected int size;

    /**
     * Default constructor
     */
    public SLL() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Constructor with Node object argument
     * @param newNode   Node to use as head
     */
    public SLL(DNode newNode) {
        this.head = newNode;
        this.tail = newNode;
        size = 1;
    }

    /**
     * Getter method for head
     * @return
     */
    public DNode getHead() {
        return head;
    }

    /**
     * Getter for tail
     * @return
     */
    public DNode getTail() {
        return tail;
    }

    /**
     * Getter for size
     * @return  size of list
     */
    public int getSize() {
        return size;
    }

    /**
     * Inserts Node object at head of list
     * @param newNode
     */
    public void insertHead(DNode newNode) {
        if (head == null) { // if list is empty
            head = newNode;
            tail = head;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
        size += 1;
    }

    /**
     * Inserts Node object at the tail of the list
     * @param newNode
     */
    public void insertTail(DNode newNode) {
        if (tail == null) { // if list is empty
            head = newNode;
            tail = head;
        } else {
            tail.setNext(newNode);
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
    public void insert(DNode newNode, int position) throws IndexOutOfBoundsException{
        if (position < 0 || position > this.size) {
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
     * @param newNode   node to insert
     */
    public void sortedInsert(DNode newNode) {
        // check if list is sorted
        boolean sorted = this.isSorted();
        if (!sorted) {
            this.sort();
        }

        if (head != null) { // if list is not empty
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
            size = 1;
        }
    }

    /**
     * Determines whether the linked list is sorted
     * @return  true if the list is sorted, otherwise false
     */
    protected boolean isSorted() {
        if (head != null) {
            DNode before = head;
            DNode current = head.getNext();
            // compares every element in the list
            while (current != null) {
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
     * Looks up a node in the list. Assumes argument given is a Node object
     * @param target   Target Node object to find
     * @return  The Node object if found, otherwise null
     */
    public DNode search(DNode target) {
        DNode current = head;
        for (int i = 0; i < size; i++) {
            if (current.getData() == target.getData()) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    /**
     * Deletes the head Node
     * @return deleted node
     */
    public DNode deleteHead() {
        DNode temp = null;
        if (head != null) {
            temp = new DNode(head.getData());
            if (head.getNext() != null) {
                // If there is > 1 Node in the LL
                head = head.getNext();
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
     * @return deleted node
     */
    public DNode deleteTail() {
        DNode temp = null;
        if (tail != null) {
            temp = new DNode(tail.getData());
            if (head.getNext() != null) {
                // If there is > 1 Node in the LL
                DNode before = head;
                while (before.getNext() != tail) {
                    before = before.getNext();
                }
                before.setNext(null);
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
     * @param target
     */
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
                
                if (foundTarget) {  
                    before.setNext(current.getNext());
                    size -= 1;
                    if (current.getNext() == null) {
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
    public void sort() {
        // if list is not empty
        if (head != null) {
            DNode unsorted = head.getNext();
            head.setNext(null);
            tail = head;
            size = 1;

            // loop through every element in 'unsorted' list
            while (unsorted != null) { 
                if (unsorted.getData() < head.getData()) {
                    insertHead(new DNode(unsorted.getData()));
                } else if (unsorted.getData() > tail.getData()) {
                    insertTail(new DNode(unsorted.getData()));
                } else {
                    DNode current = head;
                    int i = -1;
                    
                    // find index to insert the node
                    for (i = 0; i < this.size; i++) {
                        if (current.getData() > unsorted.getData()) {
                            break;
                        }
                        current = current.getNext();
                    }
                    insert(new DNode(unsorted.getData()), i);
                }
                unsorted = unsorted.getNext();                
            }

        }


    }

    /**
     * Deletes the whole list
     * (Java garbage collector will delete unreferenced objects in memory)
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Prints the list information to output
     */
    public void print() {
        System.out.println("\nList length: " + this.size);
        System.out.println("Sorted status: " + this.isSorted());

        System.out.print("List content: [ ");

        String res = "";

        DNode current = head;
        for (int i = 0; i < this.size; i++) {
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
