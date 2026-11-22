
public class VehicleInheritanceDemo {

    // Parent class
    static class Vehicle {

        String vehicleNumber;

        Vehicle(String vehicleNumber) {
            this.vehicleNumber = vehicleNumber;
            System.out.println("Vehicle constructor called");
        }

        void display() {
            System.out.println("Vehicle Number: " + vehicleNumber);
        }
    }

    // Child class 1
    static class TwoWheeler extends Vehicle {

        TwoWheeler(String vehicleNumber) {
            super(vehicleNumber);
            System.out.println("TwoWheeler constructor called");
        }
    }

    // Child class 2
    static class Car extends Vehicle {

        Car(String vehicleNumber) {
            super(vehicleNumber);
            System.out.println("Car constructor called");
        }
    }

    // Child class of Car
    static class ElectricCar extends Car {

        ElectricCar(String vehicleNumber) {
            super(vehicleNumber);
            System.out.println("ElectricCar constructor called");
        }
    }

    public static void main(String[] args) {

        System.out.println("TWO WHEELER DETAILS");

        TwoWheeler bike = new TwoWheeler("TN01AB1234");
        bike.display();

        System.out.println();

        System.out.println("ELECTRIC CAR DETAILS");

        ElectricCar ev = new ElectricCar("TN02CD5678");
        ev.display();
    }
}
