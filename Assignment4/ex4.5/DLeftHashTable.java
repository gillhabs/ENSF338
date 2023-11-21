import java.util.LinkedList;

/**
 * Implementation of a D-left hash table
 * @author chantaeh and gillhabs
 */
public class DLeftHashTable {
   private LinkedList<Entry>[] leftTable = null;
   private LinkedList<Entry>[] rightTable = null;

   /**
    * Create a DLeftHashTable with two tables and <buckets> buckets per table
    * @param buckets
    */
   public DLeftHashTable(int buckets) {
      leftTable = new LinkedList[buckets];
      rightTable = new LinkedList[buckets];
      for (int i = 0; i < buckets; i++) {
         leftTable[i] = new LinkedList<>();
         rightTable[i] = new LinkedList<>();
      }
   }

   /**
    * Hash function for left table. Uses a mid-square method for the hash algorithm.
    * @param key
    * @return  Hashed key
    */
   private int leftHash(String key) {
      String hashStr = "";

      for (int i = 0; i < key.length(); i++) {
         hashStr += Integer.toString(key.charAt(i) * key.charAt(i));
      }

      hashStr = hashStr.substring((hashStr.length()/2) - 2, (hashStr.length()/2) + 2);

      return Math.abs(Integer.parseInt(hashStr) % leftTable.length);
   }

   /**
    * Hash function for right table. Uses radix transformation.
    * @param key
    * @return  Hashed key
    */
   private int rightHash(String key) {
      String hashStr = "";

      for (int i = 0; i < key.length(); i++) {
         hashStr += Integer.toString(key.charAt(i));
      }
      if (hashStr.length() > 10) {
         hashStr = hashStr.substring(0, 10);
      }

      return (int)(Long.parseLong(String.valueOf(hashStr), 21) % rightTable.length);
   }

   /**
    * Insert a <key, value> pair in the hash table
    * @param key
    * @param value
    */
   public void insert(String key, int value) {
      int leftIndex = leftHash(key);
      int rightIndex = rightHash(key);

      // System.out.println(leftIndex + "\t" + rightIndex);

      int leftOccupancy = leftTable[leftIndex].size();
      int rightOccupancy = rightTable[rightIndex].size();

      if (leftOccupancy < rightOccupancy) {  // put in left table
         leftTable[leftIndex].add(new Entry(key, value));
      } else {    // put in right table
         rightTable[rightIndex].add(new Entry(key, value));
      }
   }

   /**
    * Lookup <key> into the hash table, and return the corresponding <value> if found
    * When a lookup is performed, the object is searched for in both tables.
    * @param key
    * @return
    */
   public int lookup(String key) {
      int leftIndex = leftHash(key);
      int rightIndex = rightHash(key);

      // Check leftTablle
      for(Entry e: leftTable[leftIndex]) {
         for(int i = 0; i < leftTable[leftIndex].size(); i++){
            if(leftTable[leftIndex].get(i).toString() == key) {
               return Integer.parseInt(leftTable[leftIndex].get(i).toString());
            }
         }
      }
 
      // Then check rightTable 
      for(Entry e: rightTable[rightIndex]) {
         for(int i = 0; i < rightTable[rightIndex].size(); i++){
            if(rightTable[rightIndex].get(i).toString() == key) {
               return Integer.parseInt(rightTable[rightIndex].get(i).toString());
            }
         }
      }

      return -1;
   }


   // public static void main(String[] args) {
   //    DLeftHashTable table = new DLeftHashTable(20);
   //    table.insert("hello", 5);
   //    table.insert("goodbye", 12);
   //    table.insert("real time",35);
   //    table.insert("orange", 13);
   //    table.insert("hello", 8);
   // }
}

class Entry {
   private String key;
   private int val;

   public Entry(String key, int val) {
      this.key = key;
      this.val = val;
   }

   public String getKey() {
      return key;
   }

   public int getVal() {
      return val;
   }
}
