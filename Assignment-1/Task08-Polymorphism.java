
public class FeeDemo {

    // Interface
    interface Chargeable {
        double chargingFee(int kWh);
    }

    // Parent abstract class
    static abstract class Vehicle {

        private final String regNo;

        Vehicle(String regNo) {
            this.regNo = regNo;
        }

        public String getRegNo() {
            return regNo;
        }

        protected abstract double ratePerHour();

        // Calculate parking fee
        public double calculateFee(int minutes) {
            int hours = (minutes + 59) / 60;
            return hours * ratePerHour();
        }

        // Compare vehicle registration numbers
        @Override
        public boolean equals(Object o) {
            return o instanceof Vehicle &&
                   regNo.equals(((Vehicle) o).regNo);
        }

        @Override
        public int hashCode() {
            return regNo.hashCode();
        }
    }

    // TwoWheeler class
    static class TwoWheeler extends Vehicle {

        TwoWheeler(String regNo) {
            super(regNo);
        }

        @Override
        protected double ratePerHour() {
            return 10;
        }
    }

    // Car class
    static class Car extends Vehicle {

        Car(String regNo) {
            super(regNo);
        }

        @Override
        protected double ratePerHour() {
            return 30;
        }
    }

    // ElectricCar class
    static class ElectricCar extends Car implements Chargeable {

        ElectricCar(String regNo) {
            super(regNo);
        }

        @Override
        public double chargingFee(int kWh) {
            return kWh * 12;
        }
    }

    public static void main(String[] args) {

        // Runtime polymorphism
        Vehicle[] parked = {
            new TwoWheeler("TN01A1"),
            new Car("TN02B2"),
            new ElectricCar("TN03C3")
        };

        for (Vehicle v : parked) {

            double fee = v.calculateFee(130);

            if (v instanceof Chargeable) {
                Chargeable c = (Chargeable) v;
                fee = fee + c.chargingFee(10);
            }

            System.out.println(
                v.getClass().getSimpleName() +
                " " + v.getRegNo() +
                " -> Rs " + fee
            );
        }

        System.out.println(
            new Car("TN02B2").equals(
                new ElectricCar("TN02B2")
            )
        );
    }
}
