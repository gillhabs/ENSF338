import java.security.Key;
import java.util.*;

/**
 * Implementation of a BloomFilter hash table
 * @author gillhabs
 */

public class BloomFilter {

    private class Entry {
        public String key;
        public int value;

        public Entry(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private List<Entry>[] bitset = null;
    private int refreshcount;
    int refreshes = 0;

    public BloomFilter(int bitsetsize, int refreshcount) {
        this.refreshcount = refreshcount;
        bitset = new List[bitsetsize];
        for (int i = 0; i < bitsetsize; i++) {
            bitset[i] = new LinkedList<>();
        }
    }

    public void record(String k) {

        int index = hash(k);
        for (Entry e : bitset[index]) {
            if (e.key.equals(k)) {
                return;
            }
        }

        bitset[index].add(new Entry(k, 1));

        int index2 = hash2(k);
        for (Entry e : bitset[index2]) {
            if (e.key.equals(k)) {
                return;
            }
        }

        bitset[index2].add(new Entry(k, 1));

        int index3 = hash3(k);
        for (Entry e : bitset[index3]) {
            if (e.key.equals(k)) {
                return;
            }
        }

        bitset[index3].add(new Entry(k, 1));


        refreshes += 1;

        if(refreshes == refreshcount){

            for (Entry e : bitset[index]) {

                if (e.key.equals(k)) {
                    e.value = 0;
                }
            }

            refreshes = 0;
        }
    }

    public boolean lookup(String s) {

        int a = 0, b = 0, c = 0;

        int index = hash(s);
        for (Entry e : bitset[index]) {
            if (e.key.equals(s))
                a = e.value;
        }

        int index2 = hash2(s);
        for (Entry e : bitset[index2]) {
            if (e.key.equals(s))
                b = e.value;
        }

        int index3 = hash3(s);
        for (Entry e : bitset[index3]) {
            if (e.key.equals(s))
                c = e.value;
        }

        if((a == 1) && (b == 1) && (c == 1)) {
            System.out.println("Lookup returned true");
            return true;
        }

        else {
            System.out.println("Lookup returned false");
            return false;
        }
    }

    //djb2 Hash Function
    public int hash(String key) {

        int hash = 5381;

        for (int i = 0; i < key.length(); i++) {
            hash = ((hash << 5) + hash) + key.charAt(i);
        }

        return Math.abs(hash % bitset.length);
    }

    //Polynomial Rolling Hash Function
    public int hash2(String key) {

        int hash = 0;
        final char[] s = key.toCharArray();
        long p_pow = 1;
        final int n = s.length;

        for (int i = 0; i < n; i++) {
            hash = (int)(hash + (s[i] - 'a' + 1) * p_pow);
            p_pow = (p_pow * 31) % 100007;
        }

        return Math.abs(hash % bitset.length);
    }

    // Radix Transformation Hash Function
    public int hash3(String key){

        int sum = 0;

        for(int i = 0; i < key.length(); i++) {
            int asciiValue = key.charAt(i);
            sum = sum + asciiValue;
        }

        String sumString = Integer.toString(sum);
        int hash = Integer.parseInt(sumString, 16);

        return Math.abs(hash % bitset.length);  
    }


    public String toString() {

        StringBuilder str = new StringBuilder();

        for (int i = 0; i < bitset.length; i++) {
            str.append(i);
            str.append(": ");

            for ( Entry e : bitset[i] ) {
                str.append("(");
                str.append(e.key);
                str.append(",");
                str.append(e.value);
                str.append(") ");
            }

            str.append("\n");
        }

        return str.toString();
    }

    public static void main(String[] args) {

        BloomFilter bloom = new BloomFilter(1000, 10);
        bloom.record("Hi");
        bloom.record("Hey");
        bloom.record("Hello");
        bloom.record("Heyy");
        bloom.record("Hullo");
        bloom.record("Peep");
        bloom.record("june");
        bloom.record("july");
        bloom.record("march");
    

        bloom.lookup("april");
        bloom.lookup("march");
        
    }
    
}
