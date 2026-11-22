
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;

// User-defined exception
class InsufficientBalanceException extends Exception {

    InsufficientBalanceException(String message) {
        super(message);
    }
}

public class ATM {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double balance = 10000;
        double amount = 0;

        FileReader card = null;

        try {

            // Open card reader file
            card = new FileReader("card.txt");

            System.out.print("Enter account number: ");
            long accountNumber = sc.nextLong();

            System.out.print("Enter withdrawal amount: ");
            amount = sc.nextDouble();

            if (amount <= 0) {
                throw new IllegalArgumentException(
                    "Withdrawal amount must be positive"
                );
            }

            if (amount > balance) {
                throw new InsufficientBalanceException(
                    "Insufficient account balance"
                );
            }

            balance = balance - amount;

            System.out.println("Withdrawal successful!");
            System.out.println("Account Number: " + accountNumber);
            System.out.println("Amount withdrawn: " + amount);
            System.out.println("Remaining balance: " + balance);

        }

        catch (InputMismatchException e) {
            System.out.println("Error: Please enter numbers only.");
        }

        catch (InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        }

        catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        catch (IOException | SecurityException e) {
            System.out.println("Error: Card reader file is missing or inaccessible.");
        }

        finally {

            try {
                if (card != null) {
                    card.close();
                }
            }

            catch (IOException e) {
                System.out.println("Error closing card reader.");
            }

            System.out.println("ATM transaction completed.");
        }

        sc.close();
    }
}
