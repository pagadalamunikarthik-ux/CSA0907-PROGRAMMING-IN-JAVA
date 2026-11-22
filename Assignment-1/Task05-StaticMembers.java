
public class ParkingStaticDemo {

    // Parking Ticket class
    static class ParkingTicket {

        static int ticketCounter = 100;

        int ticketNumber;
        String vehicleNumber;

        ParkingTicket(String vehicleNumber) {
            ticketCounter++;

            this.ticketNumber = ticketCounter;
            this.vehicleNumber = vehicleNumber;
        }

        void displayTicket() {
            System.out.println("Ticket Number: " + ticketNumber);
            System.out.println("Vehicle Number: " + vehicleNumber);
        }
    }

    // Parking Facility class
    static class ParkingFacility {

        static int totalOccupancy = 0;

        // Static method to display occupancy
        static void displayOccupancy() {
            System.out.println("Total Occupancy: " + totalOccupancy);
        }

        // Create and return a ticket object
        ParkingTicket parkVehicle(String vehicleNumber) {

            ParkingTicket ticket = new ParkingTicket(vehicleNumber);

            totalOccupancy++;

            return ticket;
        }

        // Remove a vehicle
        void leaveParking(ParkingTicket ticket) {

            if (ticket != null) {
                totalOccupancy--;
                System.out.println(ticket.vehicleNumber + " has left the parking.");
            }
        }
    }

    public static void main(String[] args) {

        ParkingFacility parking = new ParkingFacility();

        System.out.println("CAMPUS PARKING SYSTEM");

        ParkingTicket t1 = parking.parkVehicle("TN01AB1234");
        ParkingTicket t2 = parking.parkVehicle("TN02CD5678");

        t1.displayTicket();
        t2.displayTicket();

        ParkingFacility.displayOccupancy();

        parking.leaveParking(t1);

        t1 = null;

        System.out.println("First ticket reference is now null.");

        ParkingFacility.displayOccupancy();
    }
}
