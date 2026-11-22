
import java.io.*;
import java.util.*;

public class A1 {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        FileWriter fw = new FileWriter("passes.txt");

        fw.write("P1001,192311001,Arun,12,Central Stop,2026-12-31,4500\n");
        fw.write("P1002,192311002,Divya,7,Main Gate,2026-12-31,3800\n");
        fw.write("P1003,192311003,Ravi,12,Bus Stand,2026-12-31,4200\n");

        fw.close();

        System.out.println("1. Add Pass");
        System.out.println("2. Find Pass");
        System.out.println("3. List Passes by Route");

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        sc.nextLine();

        if (choice == 1) {

            System.out.print("Enter Pass ID: ");
            String id = sc.nextLine();

            System.out.print("Enter Register Number: ");
            String reg = sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Route Number: ");
            String route = sc.nextLine();

            System.out.print("Enter Boarding Stop: ");
            String stop = sc.nextLine();

            System.out.print("Enter Validity Date: ");
            String date = sc.nextLine();

            System.out.print("Enter Amount: ");
            String amount = sc.nextLine();

            FileWriter add = new FileWriter("passes.txt", true);

            add.write(id + "," + reg + "," + name + "," + route
                    + "," + stop + "," + date + "," + amount + "\n");

            add.close();

            System.out.println("Pass added successfully!");
        }

        else if (choice == 2) {

            System.out.print("Enter Pass ID to find: ");
            String search = sc.nextLine();

            BufferedReader br = new BufferedReader(
                    new FileReader("passes.txt"));

            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",", -1);

                if (data.length != 7) {
                    continue;
                }

                if (data[0].equals(search)) {
                    System.out.println("Pass Found:");
                    System.out.println(line);
                    found = true;
                    break;
                }
            }

            br.close();

            if (!found) {
                System.out.println("Pass not found!");
            }
        }

        else if (choice == 3) {

            System.out.print("Enter Route Number: ");
            String route = sc.nextLine();

            BufferedReader br = new BufferedReader(
                    new FileReader("passes.txt"));

            String line;
            boolean found = false;

            System.out.println("Passes on Route " + route + ":");

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",", -1);

                if (data.length != 7) {
                    continue;
                }

                if (data[3].equals(route)) {
                    System.out.println(line);
                    found = true;
                }
            }

            br.close();

            if (!found) {
                System.out.println("No passes found!");
            }
        }

        else {
            System.out.println("Invalid choice!");
        }

        sc.close();
    }
}
