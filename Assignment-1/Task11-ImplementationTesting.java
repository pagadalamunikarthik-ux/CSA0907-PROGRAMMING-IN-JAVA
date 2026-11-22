
public class ParkingSystemTest {

    // Vehicle parent class
    static class Vehicle {
        String regNo;
        String type;

        Vehicle(String regNo, String type) {
            this.regNo = regNo;
            this.type = type;
        }

        double getRate() {
            if (type.equals("Bike")) {
                return 10;
            } else if (type.equals("Car")) {
                return 30;
            } else if (type.equals("EV")) {
                return 30;
            }
            return 0;
        }
    }

    // Parking ticket class
    static class ParkingTicket {
        int ticketNo;
        Vehicle vehicle;
        int slot;
        int minutes;

        ParkingTicket(int ticketNo, Vehicle vehicle, int slot, int minutes) {
            this.ticketNo = ticketNo;
            this.vehicle = vehicle;
            this.slot = slot;
            this.minutes = minutes;
        }

        double calculateFee() {
            int hours = (minutes + 59) / 60;
            return hours * vehicle.getRate();
        }
    }

    // Parking facility class
    static class ParkingFacility {

        ParkingTicket[] tickets = new ParkingTicket[3];
        int ticketCounter = 100;
        double totalCollection = 0;

        boolean isValidType(String type) {
            return type.equals("Bike") ||
                   type.equals("Car") ||
                   type.equals("EV");
        }

        boolean isDuplicate(String regNo) {
            for (ParkingTicket t : tickets) {
                if (t != null && t.vehicle.regNo.equals(regNo)) {
                    return true;
                }
            }
            return false;
        }

        ParkingTicket enter(String regNo, String type, int slot, int minutes) {

            if (!isValidType(type)) {
                System.out.println("Invalid vehicle type: " + type);
                return null;
            }

            if (slot < 0 || slot >= tickets.length) {
                System.out.println("Invalid slot number: " + slot);
                return null;
            }

            if (minutes < 0) {
                System.out.println("Invalid parking duration.");
                return null;
            }

            if (isDuplicate(regNo)) {
                System.out.println("Duplicate registration: " + regNo);
                return null;
            }

            if (tickets[slot] != null) {
                System.out.println("Slot " + slot + " is occupied.");
                return null;
            }

            Vehicle v = new Vehicle(regNo, type);

            ParkingTicket ticket =
                    new ParkingTicket(++ticketCounter, v, slot, minutes);

            tickets[slot] = ticket;

            System.out.println(regNo + " parked in slot " + slot);

            return ticket;
        }

        void exit(int slot) {

            if (slot < 0 || slot >= tickets.length) {
                System.out.println("Invalid exit slot: " + slot);
                return;
            }

            if (tickets[slot] == null) {
                System.out.println("No vehicle in slot " + slot);
                return;
            }

            ParkingTicket ticket = tickets[slot];

            double fee = ticket.calculateFee();

            totalCollection += fee;

            System.out.println(ticket.vehicle.regNo +
                    " exited. Fee: Rs. " + fee);

            tickets[slot] = null;
        }
    }

    public static void main(String[] args) {

        ParkingFacility parking = new ParkingFacility();

        System.out.println("CAMPUS SMART PARKING SYSTEM");
        System.out.println("---------------------------");

        // Normal operations
        parking.enter("TN01AB1111", "Bike", 0, 60);
        parking.enter("TN02CD2222", "Car", 1, 120);
        parking.enter("TN03EF3333", "EV", 2, 90);

        System.out.println();

        // Boundary case: last free slot is now occupied
        parking.enter("TN04GH4444", "Car", 2, 60);

        // Invalid cases
        parking.enter("TN01AB1111", "Bike", 2, 60);
        parking.enter("TN05IJ5555", "Bus", 2, 60);
        parking.exit(10);

        System.out.println();

        // Vehicles leaving
        parking.exit(0);
        parking.exit(1);
        parking.exit(2);

        System.out.println();

        // Zero-minute parking
        parking.enter("TN06KL6666", "Bike", 0, 0);
        parking.exit(0);

        System.out.println("---------------------------");
        System.out.println("Total Daily Collection: Rs. " +
                parking.totalCollection);
    }
}
