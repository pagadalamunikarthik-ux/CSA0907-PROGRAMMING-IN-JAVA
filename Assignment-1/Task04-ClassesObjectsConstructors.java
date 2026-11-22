
public class ParkingTicketDemo {

    // ParkingTicket class
    static class ParkingTicket {

        int ticketNumber;
        String vehicleNumber;
        String entryTime;

        // Default constructor
        ParkingTicket() {
            this(0, "Not Assigned", "Not Recorded");
        }

        // Parameterized constructor
        ParkingTicket(int ticketNumber, String vehicleNumber) {
            this(ticketNumber, vehicleNumber, "10:00 AM");
        }

        // Overloaded constructor
        ParkingTicket(int ticketNumber, String vehicleNumber, String entryTime) {
            this.ticketNumber = ticketNumber;
            this.vehicleNumber = vehicleNumber;
            this.entryTime = entryTime;
        }

        // Copy constructor
        ParkingTicket(ParkingTicket ticket) {
            this(ticket.ticketNumber, ticket.vehicleNumber, ticket.entryTime);
        }

        void displayTicket() {
            System.out.println("Ticket Number: " + ticketNumber);
            System.out.println("Vehicle Number: " + vehicleNumber);
            System.out.println("Entry Time: " + entryTime);
            System.out.println("----------------------");
        }
    }

    public static void main(String[] args) {

        ParkingTicket t1 = new ParkingTicket();

        ParkingTicket t2 =
                new ParkingTicket(101, "TN01AB1234");

        ParkingTicket t3 =
                new ParkingTicket(102, "TN02CD5678", "11:30 AM");

        ParkingTicket t4 = new ParkingTicket(t3);

        System.out.println("PARKING TICKET DETAILS");

        t1.displayTicket();
        t2.displayTicket();
        t3.displayTicket();
        t4.displayTicket();
    }
}
