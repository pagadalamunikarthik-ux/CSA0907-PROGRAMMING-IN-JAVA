
import java.io.*;
import java.util.*;

class B1 {

    static String file = "passes.txt";

    // Add a bus pass
    static void add(String id, String reg, String name,
                    int route, double amount) throws IOException {

        FileWriter fw = new FileWriter(file, true);
        fw.write(id + "," + reg + "," + name + "," + route + "," + amount + "\n");
        fw.close();
    }

    // Find a bus pass
    static String[] find(String id) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;

        while ((line = br.readLine()) != null) {

            String[] data = line.split(",", -1);

            if (data.length != 5) {
                continue;
            }

            if (data[0].equals(id)) {
                br.close();
                return data;
            }
        }

        br.close();
        return null;
    }

    // List all passes
    static void list() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;

        while ((line = br.readLine()) != null) {

            String[] data = line.split(",", -1);

            if (data.length == 5) {
                System.out.println(Arrays.toString(data));
            }
        }

        br.close();
    }

    public static void main(String[] args) throws IOException {

        // Create a fresh file for demonstration
        FileWriter fw = new FileWriter(file);
        fw.close();

        add("P1001", "192311001", "Arun", 12, 4500);
        add("P1003", "192311003", "Divya", 7, 3800);

        // Add a damaged record
        fw = new FileWriter(file, true);
        fw.write("P1002,DAMAGED LINE\n");
        fw.close();

        System.out.println("ALL BUS PASSES:");
        list();

        System.out.println("\nSEARCH RESULT:");
        System.out.println(Arrays.toString(find("P1003")));

        System.out.println("\nSEARCH MISSING PASS:");
        System.out.println(Arrays.toString(find("P9999")));

        System.out.println("\nDAMAGED PASS:");
        System.out.println(Arrays.toString(find("P1002")));
    }
}
