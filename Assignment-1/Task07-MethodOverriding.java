
public class VehicleOverrideDemo {

    // Parent class
    static class Vehicle {

        final String vehicleNumber;

        Vehicle(String vehicleNumber) {
            this.vehicleNumber = vehicleNumber;
        }

        // Method to be overridden
        double calculateFee(int hours) {
            return 0;
        }

        // Final method cannot be overridden
        final void displayNumber() {
            System.out.println("Vehicle Number: " + vehicleNumber);
        }

        // Override toString()
        @Override
        public String toString() {
            return "Vehicle Number: " + vehicleNumber;
        }

        // Override equals()
        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }

            if (!(obj instanceof Vehicle)) {
                return false;
            }

            Vehicle v = (Vehicle) obj;

            return vehicleNumber.equals(v.vehicleNumber);
        }

        // Override hashCode()
        @Override
        public int hashCode() {
            return vehicleNumber.hashCode();
        }
    }

    // TwoWheeler class
    static class TwoWheeler extends Vehicle {

        TwoWheeler(String number) {
            super(number);
        }

        @Override
        double calculateFee(int hours) {
            return hours * 10;
        }
    }

    // Car class
    static class Car extends Vehicle {

        Car(String number) {
            super(number);
        }

        @Override
        double calculateFee(int hours) {
            return hours * 30;
        }
    }

    // ElectricCar class
    static class ElectricCar extends Vehicle {

        ElectricCar(String number) {
            super(number);
        }

        @Override
        double calculateFee(int hours) {
            return hours * 30 + 120;
        }
    }

    public static void main(String[] args) {

        TwoWheeler bike = new TwoWheeler("TN01AB1234");
        Car car = new Car("TN02CD5678");
        ElectricCar ev = new ElectricCar("TN03EF9012");

        System.out.println("PARKING FEE DETAILS");

        bike.displayNumber();
        System.out.println("Fee: Rs. " + bike.calculateFee(2));

        car.displayNumber();
        System.out.println("Fee: Rs. " + car.calculateFee(2));

        ev.displayNumber();
        System.out.println("Fee: Rs. " + ev.calculateFee(2));

        System.out.println();

        System.out.println("Object Details:");
        System.out.println(car);

        Car car2 = new Car("TN02CD5678");

        System.out.println("Same Registration: " + car.equals(car2));
        System.out.println("Same Hash Code: " +
                (car.hashCode() == car2.hashCode()));
    }
}
