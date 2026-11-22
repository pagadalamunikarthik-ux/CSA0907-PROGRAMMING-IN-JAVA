
import java.util.*;

class B3 {

    public static void main(String[] args) {

        ArrayList<String> statementList = new ArrayList<>();
        ArrayList<String> preparedList = new ArrayList<>();
        ArrayList<String> batchList = new ArrayList<>();

        // Statement simulation
        long start = System.nanoTime();

        for (int i = 1; i <= 1000; i++) {
            statementList.add("P" + i + ",Student" + i);
        }

        long statementTime = System.nanoTime() - start;

        // PreparedStatement simulation
        start = System.nanoTime();

        for (int i = 1; i <= 1000; i++) {
            preparedList.add("P" + i + ",Student" + i);
        }

        long preparedTime = System.nanoTime() - start;

        // Batch simulation
        start = System.nanoTime();

        for (int i = 1; i <= 1000; i++) {
            batchList.add("P" + i + ",Student" + i);
        }

        long batchTime = System.nanoTime() - start;

        System.out.println("STATEMENT:");
        System.out.println("Records inserted: " + statementList.size());
        System.out.println("Time: " + statementTime + " ns");

        System.out.println("\nPREPARED STATEMENT:");
        System.out.println("Records inserted: " + preparedList.size());
        System.out.println("Time: " + preparedTime + " ns");

        System.out.println("\nBATCH INSERT:");
        System.out.println("Records inserted: " + batchList.size());
        System.out.println("Time: " + batchTime + " ns");

        System.out.println("\nFirst Record:");
        System.out.println(batchList.get(0));

        System.out.println("\nProgram Completed Successfully!");
    }
}
