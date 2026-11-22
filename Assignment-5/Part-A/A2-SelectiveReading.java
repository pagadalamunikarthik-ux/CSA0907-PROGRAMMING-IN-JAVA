
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class A2 {

    public static void main(String[] args) throws IOException {

        String fileName = "attendance.txt";

        // Create a sample attendance file
        FileWriter fw = new FileWriter(fileName);

        for (int i = 1; i <= 100000; i++) {
            fw.write("Student " + i + " Present\n");
        }

        fw.close();

        // 1. Unbuffered reading
        long start = System.currentTimeMillis();

        int count1 = 0;

        FileInputStream fis = new FileInputStream(fileName);

        int data;

        while ((data = fis.read()) != -1) {
            if (data == '\n') {
                count1++;
            }
        }

        fis.close();

        long time1 = System.currentTimeMillis() - start;

        // 2. Buffered reading
        start = System.currentTimeMillis();

        int count2 = 0;

        BufferedInputStream bis =
                new BufferedInputStream(new FileInputStream(fileName));

        while ((data = bis.read()) != -1) {
            if (data == '\n') {
                count2++;
            }
        }

        bis.close();

        long time2 = System.currentTimeMillis() - start;

        // 3. Read the entire file
        start = System.currentTimeMillis();

        List<String> lines = Files.readAllLines(
                Paths.get(fileName));

        int count3 = lines.size();

        long time3 = System.currentTimeMillis() - start;

        // Display results
        System.out.println("UNBUFFERED READING");
        System.out.println("Total Lines: " + count1);
        System.out.println("Time: " + time1 + " ms");

        System.out.println("\nBUFFERED READING");
        System.out.println("Total Lines: " + count2);
        System.out.println("Time: " + time2 + " ms");

        System.out.println("\nREAD ALL LINES");
        System.out.println("Total Lines: " + count3);
        System.out.println("Time: " + time3 + " ms");

        System.out.println("\nFile processing completed!");

    }
}
