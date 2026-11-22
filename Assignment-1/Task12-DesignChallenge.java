
public class ParkingDesignChallenge {

    // Interface for one-day parking pass
    interface ParkingPass {
        boolean isValid();
    }

    static class OneDayPass implements ParkingPass {
        public boolean isValid() {
            return true;
        }
    }

    // Parent vehicle class
    static abstract class Vehicle {
        String regNo;

        Vehicle(String regNo) {
            this.regNo = regNo;
        }
    }

    static class Bike extends Vehicle {
        Bike(String regNo) {
            super(regNo);
        }
    }

    static class Car extends Vehicle {
        Car(String regNo) {
            super(regNo);
        }
    }

    static class EV extends Vehicle {
        EV(String regNo) {
            super(regNo);
        }
    }

    static class EBike extends Vehicle {
        EBike(String regNo) {
            super(regNo);
        }
    }

    static class DeliveryVan extends Vehicle {
        DeliveryVan(String regNo) {
            super(regNo);
        }
    }

    // Pricing abstraction
    interface PricingRule {
        double calculateFee(Vehicle vehicle, int minutes, int entryHour);
    }

    static class StandardPricingRule implements PricingRule {

        public double calculateFee(Vehicle vehicle, int minutes, int entryHour) {

            // E-bike has a flat fee
            if (vehicle instanceof EBike) {
                return 25;
            }

            int hours = (minutes + 59) / 60;
            double rate = 0;

            if (vehicle instanceof Bike) {
                rate = 10;
            } else if (vehicle instanceof Car || vehicle instanceof EV) {
                rate = 30;
            } else if (vehicle instanceof DeliveryVan) {

                rate = 30;

                // Double car rate from 9 AM to 5 PM
                if (entryHour >= 9 && entryHour < 17) {
                    rate = 60;
                }
            }

            return hours * rate;
        }
    }

    // Parking ticket
    static class ParkingTicket {
        int ticketNo;
        Vehicle vehicle;
        int floor;
        int slot;
        int minutes;
        int entryHour;
        ParkingPass pass;

        ParkingTicket(int ticketNo, Vehicle vehicle, int floor,
                      int slot, int minutes, int entryHour,
                      ParkingPass pass) {

            this.ticketNo = ticketNo;
            this.vehicle = vehicle;
            this.floor = floor;
            this.slot = slot;
            this.minutes = minutes;
            this.entryHour = entryHour;
            this.pass = pass;
        }
    }

    // Parking facility
    static class ParkingFacility {

        // Five floors with different slot counts
        ParkingTicket[][] slots = {
            new ParkingTicket[2],
            new ParkingTicket[3],
            new ParkingTicket[2],
            new ParkingTicket[4],
            new ParkingTicket[3]
        };

        int ticketCounter = 100;
        PricingRule pricing = new StandardPricingRule();
        double totalCollection = 0;

        void park(Vehicle vehicle, int minutes,
                  int entryHour, ParkingPass pass) {

            // Check duplicate registration
            for (ParkingTicket[] floor : slots) {
                for (ParkingTicket ticket : floor) {
                    if (ticket != null &&
                        ticket.vehicle.regNo.equals(vehicle.regNo)) {

                        System.out.println("Duplicate vehicle: " +
                                           vehicle.regNo);
                        return;
                    }
                }
            }

            // Find a free slot
            for (int f = 0; f < slots.length; f++) {
                for (int s = 0; s < slots[f].length; s++) {

                    if (slots[f][s] == null) {

                        slots[f][s] = new ParkingTicket(
                            ++ticketCounter, vehicle, f, s,
                            minutes, entryHour, pass
                        );

                        System.out.println(vehicle.regNo +
                            " parked at Floor " + (f + 1) +
                            ", Slot " + (s + 1));

                        return;
                    }
                }
            }

            System.out.println("Parking is full.");
        }

        void exit(String regNo) {

            for (int f = 0; f < slots.length; f++) {
                for (int s = 0; s < slots[f].length; s++) {

                    ParkingTicket ticket = slots[f][s];

                    if (ticket != null &&
                        ticket.vehicle.regNo.equals(regNo)) {

                        double fee;

                        if (ticket.pass != null &&
                            ticket.pass.isValid()) {
                            fee = 0;
                        } else {
                            fee = pricing.calculateFee(
                                ticket.vehicle,
                                ticket.minutes,
                                ticket.entryHour
                            );
                        }

                        totalCollection += fee;

                        System.out.println(regNo +
                            " exited. Fee: Rs. " + fee);

                        slots[f][s] = null;
                        return;
                    }
                }
            }

            System.out.println("Vehicle not found: " + regNo);
        }
    }

    public static void main(String[] args) {

        ParkingFacility parking = new ParkingFacility();

        System.out.println("CAMPUS SMART PARKING SYSTEM");
        System.out.println("---------------------------");

        // Normal vehicle
        parking.park(new Bike("TN01AB1111"), 60, 8, null);

        // New E-bike category
        parking.park(new EBike("TN02CD2222"), 120, 10, null);

        // New delivery van category: 10 AM
        parking.park(new DeliveryVan("TN03EF3333"), 120, 10, null);

        // Car with visitor one-day pass
        parking.park(new Car("TN04GH4444"), 180, 11,
                     new OneDayPass());

        System.out.println();

        // Vehicles exit
        parking.exit("TN01AB1111");
        parking.exit("TN02CD2222");
        parking.exit("TN03EF3333");
        parking.exit("TN04GH4444");

        System.out.println("---------------------------");
        System.out.println("Total Collection: Rs. " +
                           parking.totalCollection);
    }
}
