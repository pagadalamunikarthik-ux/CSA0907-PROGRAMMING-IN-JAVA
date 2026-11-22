import java.util.Scanner;

public class ParkingData {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Jagged array: each floor can have different slots
        String[][] slots = {
            new String[3],
            new String[2],
            new String[4]
        };

        System.out.print("Enter vehicle number: ");
        String vehicleNo = sc.nextLine();

        System.out.print("Enter parking duration in minutes: ");
        int minutes = sc.nextInt();

        // Allocate a free parking slot
        boolean found = false;

        for (int i = 0; i < slots.length; i++) {
            for (int j = 0; j < slots[i].length; j++) {

                if (slots[i][j] == null) {
                    slots[i][j] = vehicleNo;

                    System.out.println("Floor: " + (i + 1));
                    System.out.println("Slot: " + (j + 1));

                    found = true;
                    break;
                }
            }

            if (found) {
                break;
            }
        }

        if (!found) {
            System.out.println("Parking is full.");
        }

        // Calculate parking fee
        int hours = (minutes + 59) / 60;
        double fee = hours * 30.0;

        System.out.println("Vehicle Number: " + vehicleNo);
        System.out.println("Parking Duration: " + minutes + " minutes");
        System.out.println("Parking Fee: Rs. " + fee);

        sc.close();
    }
}
