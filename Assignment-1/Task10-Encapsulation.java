
public class ParkingEncapsulation {

    // Vehicle class
    static class Vehicle {

        private String vehicleNumber;
        private int duration;

        Vehicle(String vehicleNumber, int duration) {
            setVehicleNumber(vehicleNumber);
            setDuration(duration);
        }

        // Getter
        public String getVehicleNumber() {
            return vehicleNumber;
        }

        // Setter with validation
        public void setVehicleNumber(String vehicleNumber) {
            if (vehicleNumber == null || vehicleNumber.isEmpty()) {
                System.out.println("Invalid vehicle number");
            } else {
                this.vehicleNumber = vehicleNumber;
            }
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            if (duration < 0) {
                System.out.println("Invalid duration");
            } else {
                this.duration = duration;
            }
        }
    }

    // Parking Slot class
    static class ParkingSlot {

        private int slotNumber;
        private boolean occupied;

        ParkingSlot(int slotNumber) {
            this.slotNumber = slotNumber;
            this.occupied = false;
        }

        public int getSlotNumber() {
            return slotNumber;
        }

        public boolean isOccupied() {
            return occupied;
        }

        public void park() {
            occupied = true;
        }
    }

    // Parking Service class
    static class ParkingService {

        public void parkVehicle(Vehicle vehicle, ParkingSlot slot) {

            if (slot.isOccupied()) {
                System.out.println("Slot is already occupied.");
            } else {
                slot.park();

                System.out.println("Vehicle: " + vehicle.getVehicleNumber());
                System.out.println("Duration: " + vehicle.getDuration() + " minutes");
                System.out.println("Slot: " + slot.getSlotNumber());
                System.out.println("Vehicle parked successfully.");
            }
        }
    }

    public static void main(String[] args) {

        Vehicle v1 = new Vehicle("TN01AB1234", 120);

        ParkingSlot s1 = new ParkingSlot(101);

        ParkingService service = new ParkingService();

        System.out.println("CAMPUS PARKING SYSTEM");

        service.parkVehicle(v1, s1);

        System.out.println();

        // Wrapper class and autoboxing
        Integer a = 100;
        Integer b = 100;

        System.out.println("Wrapper comparison: " + a.equals(b));
    }
}
