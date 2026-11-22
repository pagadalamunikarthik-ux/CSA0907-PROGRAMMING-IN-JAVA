
import java.util.*;

public class Q4_MapBenchmark {

    static void test(int size) {

        HashMap<Integer, String> hashMap = new HashMap<>();
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
        Hashtable<Integer, String> hashTable = new Hashtable<>();

        System.out.println("\n======================================");
        System.out.println("Testing with " + size + " medicines");
        System.out.println("======================================");

        // 1. Insert data into all maps
        long start = System.nanoTime();

        for (int i = size; i >= 1; i--) {
            hashMap.put(i, "Medicine" + i);
        }

        long hashInsert = System.nanoTime() - start;

        start = System.nanoTime();

        for (int i = size; i >= 1; i--) {
            treeMap.put(i, "Medicine" + i);
        }

        long treeInsert = System.nanoTime() - start;

        start = System.nanoTime();

        for (int i = size; i >= 1; i--) {
            linkedHashMap.put(i, "Medicine" + i);
        }

        long linkedInsert = System.nanoTime() - start;

        start = System.nanoTime();

        for (int i = size; i >= 1; i--) {
            hashTable.put(i, "Medicine" + i);
        }

        long tableInsert = System.nanoTime() - start;

        // 2. Perform 100000 lookups
        int lookups = 100000;

        start = System.nanoTime();

        for (int i = 0; i < lookups; i++) {
            hashMap.get((i % size) + 1);
        }

        long hashLookup = System.nanoTime() - start;

        start = System.nanoTime();

        for (int i = 0; i < lookups; i++) {
            treeMap.get((i % size) + 1);
        }

        long treeLookup = System.nanoTime() - start;

        start = System.nanoTime();

        for (int i = 0; i < lookups; i++) {
            linkedHashMap.get((i % size) + 1);
        }

        long linkedLookup = System.nanoTime() - start;

        // 3. Measure iteration time
        start = System.nanoTime();

        for (Map.Entry<Integer, String> entry : hashMap.entrySet()) {
            entry.getValue();
        }

        long hashIteration = System.nanoTime() - start;

        start = System.nanoTime();

        for (Map.Entry<Integer, String> entry : treeMap.entrySet()) {
            entry.getValue();
        }

        long treeIteration = System.nanoTime() - start;

        start = System.nanoTime();

        for (Map.Entry<Integer, String> entry : linkedHashMap.entrySet()) {
            entry.getValue();
        }

        long linkedIteration = System.nanoTime() - start;

        // 4. Display only first 10 records
        System.out.println("\nFirst 10 records:");

        System.out.println("HashMap: " + getFirstTen(hashMap));
        System.out.println("TreeMap: " + getFirstTen(treeMap));
        System.out.println("LinkedHashMap: " + getFirstTen(linkedHashMap));

        // 5. Display performance results
        System.out.println("\nPerformance Results (nanoseconds)");

        System.out.println("\nHashMap");
        System.out.println("Insertion: " + hashInsert);
        System.out.println("100000 Lookups: " + hashLookup);
        System.out.println("Iteration: " + hashIteration);

        System.out.println("\nTreeMap");
        System.out.println("Insertion: " + treeInsert);
        System.out.println("100000 Lookups: " + treeLookup);
        System.out.println("Iteration: " + treeIteration);

        System.out.println("\nLinkedHashMap");
        System.out.println("Insertion: " + linkedInsert);
        System.out.println("100000 Lookups: " + linkedLookup);
        System.out.println("Iteration: " + linkedIteration);

        System.out.println("\nHashtable");
        System.out.println("Insertion: " + tableInsert);

        // 6. Sorted array and binarySearch alternative
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = i + 1;
        }

        start = System.nanoTime();

        for (int i = 0; i < lookups; i++) {
            Arrays.binarySearch(array, (i % size) + 1);
        }

        long arraySearch = System.nanoTime() - start;

        System.out.println("\nSorted Array");
        System.out.println("100000 Binary Searches: " + arraySearch);

    }

    // Method to display only first 10 entries
    static String getFirstTen(Map<Integer, String> map) {

        String result = "";
        int count = 0;

        for (Map.Entry<Integer, String> entry : map.entrySet()) {

            if (count == 10) {
                break;
            }

            result += entry.getKey() + "=" + entry.getValue() + ", ";
            count++;
        }

        return result;
    }

    public static void main(String[] args) {

        System.out.println("PHARMACY STOCK MAP BENCHMARK");

        test(1000);
        test(10000);
        test(50000);

        System.out.println("\nBenchmark Completed Successfully!");
    }
}
