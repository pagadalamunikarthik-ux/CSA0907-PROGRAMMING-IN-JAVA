
import java.awt.*;
import java.awt.event.*;

// Separate class for payment logic
class FeeService {

    String payFee(String regNo, double amount) {

        if (regNo.isEmpty()) {
            return "Please enter registration number.";
        }

        if (amount <= 0) {
            return "Enter a valid amount.";
        }

        return "Payment successful!\nRegistration No: "
                + regNo + "\nAmount Paid: Rs. " + amount;
    }
}

// Main GUI class
public class FeeWindow extends Frame {

    TextField regNo, amount;
    Label status;

    FeeService service = new FeeService();

    String receipt = "";

    FeeWindow() {

        setTitle("Student Fee Payment");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2, 10, 10));

        Label l1 = new Label("Registration Number:");
        Label l2 = new Label("Amount:");

        regNo = new TextField();
        amount = new TextField();

        Button pay = new Button("Pay");
        Button clear = new Button("Clear");
        Button receiptButton = new Button("Receipt");

        status = new Label("Enter payment details");

        add(l1);
        add(regNo);

        add(l2);
        add(amount);

        add(pay);
        add(clear);

        add(receiptButton);
        add(status);

        // Pay button listener
        pay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {

                    double a = Double.parseDouble(amount.getText());

                    receipt = service.payFee(regNo.getText(), a);

                    status.setText("Payment processed");

                    showMessage(receipt);

                } catch (NumberFormatException ex) {

                    showMessage("Please enter a valid number.");
                }
            }
        });

        // Clear button listener
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                regNo.setText("");
                amount.setText("");
                status.setText("Fields cleared");
                receipt = "";
            }
        });

        // Receipt button listener
        receiptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (receipt.isEmpty()) {
                    showMessage("No payment receipt available.");
                } else {
                    showMessage(receipt);
                }
            }
        });

        // Window closing listener
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    void showMessage(String message) {

        Dialog d = new Dialog(this, "Payment Message", true);

        d.setLayout(new FlowLayout());

        d.add(new Label(message));

        Button ok = new Button("OK");

        ok.addActionListener(e -> d.dispose());

        d.add(ok);

        d.setSize(300, 150);
        d.setVisible(true);
    }

    public static void main(String[] args) {
        new FeeWindow();
    }
}
