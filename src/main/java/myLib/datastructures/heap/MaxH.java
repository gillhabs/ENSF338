package myLib.datastructures.heap;

import java.util.Vector;


/**
 * Implements a max heap
 * @chantaeh
 */
public class MaxH{
    private Vector<Integer> elements;

    /**
     * Default constructor
     */
    public MaxH() {
        elements = new Vector<>();
    }

    /**
     * Overload constructor initializes vector to a size passed as arg
     * @param size
     */
    public MaxH(int size) {
        elements = new Vector<>(size);
    }

    /**
     * Overload constructor creates a heap from an array and
     * stores it in elements
     * @param arr
     */
    public MaxH(int[] arr) {
        elements = heapify(arr);
    }

    /**
     * @return size of vector containing elements
     */
    public int getSize() {
        return elements.size();
    }

    /**
     * @return  vector of elements
     */
    public Vector<Integer> getElements() {
        return elements;
    }

    /**
     * Checks if heap is empty
     * @return true if vector is empty, false otherwise
     */
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    /**
     * Clears elements of the vector
     */
    public void clear() {
        elements.clear();
    }

    /**
     * Searches for the value "i" in the heap
     * @param i     value to find
     * @return  true if found, false otherwise
     */
    public boolean contains(Integer i) {
        return elements.contains(i);
    }

    /**
     * Inserts the value key to the vector and maintains heap properties
     * @param key
     */
    public void insert(Integer key) {
        elements.add(key);
        heapifyUp(elements.size()-1);
    }

    /**
     * Removes the value key from the vector and maintains heap properties
     * @param key  Value to remove
     */
    public void delete(Integer key) {
        if (elements.contains(key)) {
            int i = 0;
            for (i = 0; i < elements.size(); i++) {
                if (elements.get(i) == key) {
                    break;
                }
            }
            elements.remove(i);
            heapifyDown(i);
        }
    }

    /**
     * Applies heapsort to the vector content
     */
    public void sort() {
        if (elements.size() > 1) {
            int size = elements.size();
            for (int i = size-1; i > 0; i--) {
                swap(0, i);
                int[] unsorted = new int[i]; 
                for (int j = 0; j < i; j++) {
                    unsorted[j] = elements.get(j);
                }
                Vector<Integer> heapified = heapify(unsorted);
                for (int j = 0; j < i; j++) {
                    elements.set(j, heapified.get(j));
                }
            }
        }
    }

    /**
     * displays the content of the heap vector over 2 lines. 
     * First line is the index of the parent of each element. 
     * Second line are the elements themselves
     */
    public void print() {
        String indexes = "";
        String content = "";

        for (int i = 0; i < elements.size(); i++) {
            int parent = i==0 ? -1 : (int)((i - 1) / 2);
            indexes += parent + "\t";
            content += elements.get(i) + "\t";
        }

        System.out.println(indexes);
        System.out.println();
        System.out.println(content);
        System.out.println();
    }

    /**
     * Returns parent of element[i]
     * @param i
     * @return
     */
    private int parent(int i) {
        return elements.get((int)((i - 1) / 2));   
    }

    /**
     * Returns left child of elements[i]
     * @param i
     * @return
     */
    private int left(int i) {
        return elements.get((2 * i) + 1);
    }

    /**
     * Returns right child of elements[i]
     * @param i
     * @return
     */
    private int right(int i) {
        return elements.get((2 * i) + 2);
    }

    /**
     * Swaps contents of indicies x and y
     * @param x
     * @param y
     */
    private void swap(int x, int y) {
        Integer temp = elements.get(x);
        elements.set(x, elements.get(y));
        elements.set(y, temp);
    }

    /**
     * Heapification process after deletion
     * @param i
     */
    private void heapifyDown(int i) {
        if (i == 0) {   // value to remove is at top of heap
            while (((elements.get(i) < right(i)) && (right(i) < elements.size()))) {
                swap(i, right(i));
                i = right(i);
            }
        }
    }

    /**
     * Heapification process after insertion
     * @param i
     */
    private void heapifyUp(int i) {
        if (i != 0) {
            while ((elements.get(i) > parent(i)) && (i > -1) && (i < elements.size())) {
                swap(i, (int)((i - 1) / 2));
                i = (int)((i - 1) / 2);
            }
        }
    }

    /**
     * Takes in an array of values and returns a valid heap
     * @param array
     * @return
     */
    private Vector<Integer> heapify(int[] array) {
        MaxH heap = new MaxH();
        for(int num: array) {
            heap.insert(num);
        }
        return heap.getElements();
    }

    public static void main(String[] args) {
        // Test default constructor and insert method
        System.out.println("Test default constructor and insert().");
        MaxH m = new MaxH();
        m.insert(5);
        m.insert(10);
        m.insert(2);

        // Test print method. Expected: 2   10    5
        System.out.println("Test print(). Expected result: 10 5 2");
        m.print();
        
        // Test sort method. Expected: 2    5   10
        System.out.println("Test sort(). Expected result: 2 5 10");
        m.sort();
        m.print();

        // Test delete method. Expected: 2     5
        System.out.println("Test delete(10). Expected result: 2     5");
        m.delete(10);
        m.print();
        
        System.out.println("--------------------");

        // Test constructor given size as an argument. Test getSize() and isEmpty().
        System.out.println("Test constructor given an integer size as an argument.");
        MaxH m1 = new MaxH(3);
        System.out.println("Test isEmpty(). Expected: true      Actual: " + m1.isEmpty());

        // Test contains()
        System.out.println("Test contains() on invalid object. Expected: false   Actual: " + m1.contains(5));
        m1.insert(5);
        System.out.println("Insert an item and test contains(). Expected: true   Actual: " + m1.contains(5));


        System.out.println("\n--------------------\n");
        // Test constructor given an integer array.
        System.out.println("Test constructor given an integer array as an argument. Expected result: 7 6 0 1 3");
        int[] arr = {3, 7, 0, 1, 6};
        MaxH m2 = new MaxH(arr);
        m2.print();

        // Test sort method. Expected: 0 1 3 6 7
        System.out.println("Test sort(). Expected result: 0 1 3 6 7");
        m2.sort();
        m2.print();

        // Test clear() method.
        System.out.println("Test clear(). Expected result: \\n\\n");
        m2.clear();
        m2.print();
    
    }
}