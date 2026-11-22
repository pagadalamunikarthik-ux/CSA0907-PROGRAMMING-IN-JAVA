
import java.io.*;
import java.util.*;

class B4 {

    public static void main(String[] args) throws IOException {

        // Create sample bus-pass file
        FileWriter fw = new FileWriter("passes.txt");

        fw.write("P1001,192311001,Arun,12,4500\n");
        fw.write("P1002,192311002,Ravi,8,4000\n");
        fw.write("P1003,DAMAGED LINE\n");
        fw.write("P1004,192311004,Divya,7,3800\n");

        fw.close();

        ArrayList<String> database = new ArrayList<>();

        BufferedReader br = new BufferedReader(
            new FileReader("passes.txt")
        );

        String line;
        int valid = 0;
        int invalid = 0;

        System.out.println("BUS PASS DATA MIGRATION");
        System.out.println("-----------------------");

        while ((line = br.readLine()) != null) {

            String[] data = line.split(",", -1);

            if (data.length != 5) {
                System.out.println("Invalid Record: " + line);
                invalid++;
                continue;
            }

            try {
                Integer.parseInt(data[3]);
                Double.parseDouble(data[4]);

                database.add(line);
                valid++;

            } catch (NumberFormatException e) {
                System.out.println("Invalid Data: " + line);
                invalid++;
            }
        }

        br.close();

        System.out.println("\nMIGRATION REPORT");
        System.out.println("----------------");
        System.out.println("Valid Records: " + valid);
        System.out.println("Invalid Records: " + invalid);
        System.out.println("Migrated Records: " + database.size());

        System.out.println("\nMIGRATED DATA:");

        for (String record : database) {
            System.out.println(record);
        }

        System.out.println("\nFINAL ENGINEERING DECISION");
        System.out.println("--------------------------");
        System.out.println("Text files: Simple storage");
        System.out.println("Serialization: Stores Java objects");
        System.out.println("Database: Better for large shared systems");
        System.out.println("Recommended: Database for 12 campuses");
    }
}
