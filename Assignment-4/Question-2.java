
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AppointmentDesk extends JFrame {

    JTextField patientId;
    JComboBox<String> department;
    JComboBox<String> doctor;
    JComboBox<String> timeSlot;

    JButton bookButton;

    boolean bookingInProgress = false;

    AppointmentDesk() {

        setTitle("Hospital Appointment Desk");
        setSize(450, 350);
        setLayout(new GridLayout(6, 2, 10, 10));

        patientId = new JTextField();

        department = new JComboBox<>(new String[]{
            "Cardiology", "Neurology", "Orthopedics"
        });

        doctor = new JComboBox<>();

        timeSlot = new JComboBox<>(new String[]{
            "10:00 AM", "11:00 AM", "12:00 PM",
            "2:00 PM", "3:00 PM"
        });

        bookButton = new JButton("Book Appointment");

        add(new JLabel("Patient ID:"));
        add(patientId);

        add(new JLabel("Department:"));
        add(department);

        add(new JLabel("Doctor:"));
        add(doctor);

        add(new JLabel("Time Slot:"));
        add(timeSlot);

        add(new JLabel(""));
        add(bookButton);

        // Load doctors initially
        updateDoctors();

        // Change doctors when department changes
        department.addActionListener(e -> updateDoctors());

        // Book appointment
        bookButton.addActionListener(e -> bookAppointment());

        // Handle window closing
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {

                if (bookingInProgress) {

                    int choice = JOptionPane.showConfirmDialog(
                        AppointmentDesk.this,
                        "Booking is in progress. Do you want to close?",
                        "Confirm Closing",
                        JOptionPane.YES_NO_OPTION
                    );

                    if (choice == JOptionPane.YES_OPTION) {
                        dispose();
                    }

                } else {
                    dispose();
                }
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Update doctor list
    void updateDoctors() {

        doctor.removeAllItems();

        String selected = (String) department.getSelectedItem();

        if (selected.equals("Cardiology")) {
            doctor.addItem("Dr. Kumar");
            doctor.addItem("Dr. Ravi");
        }

        else if (selected.equals("Neurology")) {
            doctor.addItem("Dr. Priya");
            doctor.addItem("Dr. Anitha");
        }

        else if (selected.equals("Orthopedics")) {
            doctor.addItem("Dr. Arun");
            doctor.addItem("Dr. Suresh");
        }
    }

    // Book appointment
    void bookAppointment() {

        String id = patientId.getText().trim();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(
                this, "Please enter Patient ID."
            );
            return;
        }

        String dept = (String) department.getSelectedItem();
        String doc = (String) doctor.getSelectedItem();
        String slot = (String) timeSlot.getSelectedItem();

        bookingInProgress = true;
        bookButton.setEnabled(false);

        // Simulate appointment booking
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

            protected Void doInBackground() throws Exception {
                Thread.sleep(2000);
                return null;
            }

            protected void done() {

                bookingInProgress = false;
                bookButton.setEnabled(true);

                JOptionPane.showMessageDialog(
                    AppointmentDesk.this,
                    "Appointment Booked Successfully!\n\n" +
                    "Patient ID: " + id +
                    "\nDepartment: " + dept +
                    "\nDoctor: " + doc +
                    "\nTime Slot: " + slot
                );
            }
        };

        worker.execute();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new AppointmentDesk());
    }
}
