
import java.io.*;

public class SalesReport {

    // Create sample store files
    static void createFiles() throws IOException {

        FileWriter f1 = new FileWriter("store1.txt");
        f1.write("100\n200\n300\n");
        f1.close();

        FileWriter f2 = new FileWriter("store2.txt");
        f2.write("150\n250\nABC\n");
        f2.close();

        FileWriter f3 = new FileWriter("store3.txt");
        f3.write("400\n500\n");
        f3.close();
    }

    // Method 1: Using try-catch-finally
    static void manualReport() {

        BufferedReader reader = null;
        BufferedWriter writer = null;

        int total = 0;

        try {

            writer = new BufferedWriter(
                new FileWriter("manual_report.txt")
            );

            String[] files = {
                "store1.txt", "store2.txt", "store3.txt"
            };

            for (String file : files) {

                try {
                    reader = new BufferedReader(
                        new FileReader(file)
                    );

                    String line;

                    while ((line = reader.readLine()) != null) {

                        try {
                            total += Integer.parseInt(line);
                        } catch (NumberFormatException e) {
                            System.out.println(
                                "Invalid data in " + file + ": " + line
                            );
                        }
                    }

                    reader.close();
                    reader = null;

                } catch (IOException e) {
                    System.out.println("File error: " + file);
                }
            }

            writer.write("Total Sales: " + total);

        } catch (IOException e) {
            System.out.println("Report error: " + e.getMessage());

        } finally {

            try {
                if (reader != null) {
                    reader.close();
                }

                if (writer != null) {
                    writer.close();
                }

            } catch (IOException e) {
                System.out.println("Error closing files.");
            }
        }

        System.out.println("Manual report completed.");
    }

    // Method 2: Using try-with-resources
    static void automaticReport() {

        int total = 0;

        String[] files = {
            "store1.txt", "store2.txt", "store3.txt"
        };

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("automatic_report.txt"))) {

            for (String file : files) {

                try (BufferedReader reader = new BufferedReader(
                        new FileReader(file))) {

                    String line;

                    while ((line = reader.readLine()) != null) {

                        try {
                            total += Integer.parseInt(line);
                        } catch (NumberFormatException e) {
                            System.out.println(
                                "Invalid data in " + file + ": " + line
                            );
                        }
                    }

                } catch (IOException e) {
                    System.out.println("File error: " + file);
                }
            }

            writer.write("Total Sales: " + total);

        } catch (IOException e) {
            System.out.println("Report error: " + e.getMessage());
        }

        System.out.println("Automatic report completed.");
    }

    public static void main(String[] args) {

        try {
            createFiles();

            System.out.println("MANUAL RESOURCE HANDLING");
            manualReport();

            System.out.println("\nTRY-WITH-RESOURCES");
            automaticReport();

            System.out.println("\nREPORT COMPARISON");

            BufferedReader r1 = new BufferedReader(
                new FileReader("manual_report.txt")
            );

            BufferedReader r2 = new BufferedReader(
                new FileReader("automatic_report.txt")
            );

            System.out.println("Manual: " + r1.readLine());
            System.out.println("Automatic: " + r2.readLine());

            r1.close();
            r2.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
