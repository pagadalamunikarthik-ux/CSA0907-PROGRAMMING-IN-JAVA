
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Converter extends JFrame {

    private final JTextField amount = new JTextField(10);

    private final JComboBox<String> target =
            new JComboBox<>(new String[]{"USD", "EUR"});

    private final JLabel result = new JLabel(" ");

    private static double rate(String currency) {
        return currency.equals("USD") ? 0.012 : 0.011;
    }

    public Converter() {

        super("Currency Converter");

        setLayout(new FlowLayout());

        JButton convertButton = new JButton("Convert");

        add(new JLabel("INR"));
        add(amount);
        add(target);
        add(convertButton);
        add(result);

        // One lambda listener for button and Enter key
        ActionListener listener = e -> convert();

        convertButton.addActionListener(listener);
        amount.addActionListener(listener);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pack();
    }

    private void convert() {

        try {
            double value = Double.parseDouble(amount.getText());

            String currency = (String) target.getSelectedItem();

            double converted = value * rate(currency);

            String answer = String.format("%.2f %s", converted, currency);

            result.setText(answer);

            JOptionPane.showMessageDialog(this, answer);

        } catch (NumberFormatException e) {

            result.setText("Invalid amount");

            JOptionPane.showMessageDialog(this, "Invalid amount");
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() ->
                new Converter().setVisible(true));
    }
}
