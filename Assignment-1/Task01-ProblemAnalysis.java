class Vehicle {
    String vehicleNumber;
    String vehicleType;

    Vehicle(String number, String type) {
        vehicleNumber = number;
        vehicleType = type;
    }

    void displayVehicle() {
        System.out.println("Vehicle Number: " + vehicleNumber);
        System.out.println("Vehicle Type: " + vehicleType);
    }
}

class ParkingSlot {
    int slotNumber;
    boolean isOccupied;

    ParkingSlot(int number) {
        slotNumber = number;
        isOccupied = false;
    }

    void parkVehicle() {
        if (isOccupied == false) {
            isOccupied = true;
            System.out.println("Slot " + slotNumber + " is allocated.");
        } else {
            System.out.println("Slot is already occupied.");
        }
    }
}

class ParkingTicket {
    int ticketNumber;
    String entryTime;

    ParkingTicket(int number, String time) {
        ticketNumber = number;
        entryTime = time;
    }

    void displayTicket() {
        System.out.println("Ticket Number: " + ticketNumber);
        System.out.println("Entry Time: " + entryTime);
    }
}

public class CampusParking {
    public static void main(String[] args) {

        Vehicle v1 = new Vehicle("TN01AB1234", "Car");

        ParkingSlot s1 = new ParkingSlot(101);

        ParkingTicket t1 = new ParkingTicket(1, "10:00 AM");

        System.out.println("CAMPUS SMART PARKING SYSTEM");

        v1.displayVehicle();

        s1.parkVehicle();

        t1.displayTicket();
    }
}
