
public class ParkingAbstractionDemo {

    // Interface for electric vehicles
    interface Chargeable {

        double chargingFee(int kWh);

        default void showChargingMessage() {
            System.out.println("Vehicle supports charging.");
        }

        static void chargingInfo() {
            System.out.println("Charging rate: Rs. 12 per kWh");
        }
    }

    // Interface for payment
    interface Payable {

        void makePayment(double amount);
    }

    // Abstract parent class
    static abstract class Vehicle {

        String vehicleNumber;

        Vehicle(String vehicleNumber) {
            this.vehicleNumber = vehicleNumber;
        }

        abstract double calculateFee(int hours);

        void display() {
            System.out.println("Vehicle Number: " + vehicleNumber);
        }
    }

    // Car class
    static class Car extends Vehicle implements Payable {

        Car(String vehicleNumber) {
            super(vehicleNumber);
        }

        @Override
        double calculateFee(int hours) {
            return hours * 30;
        }

        @Override
        public void makePayment(double amount) {
            System.out.println("Payment: Rs. " + amount);
        }
    }

    // Electric car class
    static class ElectricCar extends Vehicle
            implements Chargeable, Payable {

        ElectricCar(String vehicleNumber) {
            super(vehicleNumber);
        }

        @Override
        double calculateFee(int hours) {
            return hours * 30;
        }

        @Override
        public double chargingFee(int kWh) {
            return kWh * 12;
        }

        @Override
        public void makePayment(double amount) {
            System.out.println("Payment: Rs. " + amount);
        }
    }

    public static void main(String[] args) {

        Car car = new Car("TN01AB1234");

        System.out.println("CAR DETAILS");
        car.display();

        double carFee = car.calculateFee(2);
        car.makePayment(carFee);

        System.out.println();

        ElectricCar ev = new ElectricCar("TN02CD5678");

        System.out.println("ELECTRIC CAR DETAILS");
        ev.display();

        double parkingFee = ev.calculateFee(2);
        double chargingFee = ev.chargingFee(10);

        ev.showChargingMessage();

        System.out.println("Parking Fee: Rs. " + parkingFee);
        System.out.println("Charging Fee: Rs. " + chargingFee);

        ev.makePayment(parkingFee + chargingFee);

        System.out.println();

        Chargeable.chargingInfo();
    }
}
