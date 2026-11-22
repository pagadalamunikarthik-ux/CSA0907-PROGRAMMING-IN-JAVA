
import java.io.*;
import java.sql.Connection;
import java.util.ArrayList;

class Resident implements Serializable {

    private static final long serialVersionUID = 1L;

    String name;
    String regNo;
    String phone;

    transient String password;
    transient Connection connection;

    Resident(String name, String regNo, String phone, String password) {
        this.name = name;
        this.regNo = regNo;
        this.phone = phone;
        this.password = password;
    }

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Register Number: " + regNo);
        System.out.println("Phone: " + phone);
        System.out.println("Password: " + password);
        System.out.println("Connection: " + connection);
    }
}

class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    int roomNumber;
    ArrayList<Resident> residents = new ArrayList<>();

    Room(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}

class Hostel implements Serializable {

    private static final long serialVersionUID = 1L;

    String hostelName;
    ArrayList<Room> rooms = new ArrayList<>();

    Hostel(String hostelName) {
        this.hostelName = hostelName;
    }
}

class A4 {

    public static void main(String[] args) {

        try {

            // Create hostel
            Hostel hostel = new Hostel("SIMATS Hostel");

            // Create room
            Room room = new Room(101);

            // Create resident
            Resident resident = new Resident(
                    "Arun",
                    "192311001",
                    "9876543210",
                    "arun123"
            );

            room.residents.add(resident);
            hostel.rooms.add(room);

            // Serialize object
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("hostel.dat")
            );

            out.writeObject(hostel);
            out.close();

            System.out.println("Hostel data saved successfully!");

            // Deserialize object
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream("hostel.dat")
            );

            Hostel savedHostel = (Hostel) in.readObject();
            in.close();

            System.out.println("\nHostel Details:");
            System.out.println("Hostel: " + savedHostel.hostelName);

            for (Room r : savedHostel.rooms) {
                System.out.println("\nRoom Number: " + r.roomNumber);

                for (Resident res : r.residents) {
                    res.display();
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
