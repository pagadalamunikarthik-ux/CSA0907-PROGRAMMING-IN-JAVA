
import java.io.*;

class B2 {

    public static void main(String[] args) throws IOException {

        // SQL Injection demonstration
        String username = "' OR '1'='1' --";
        String password = "wrong";

        System.out.println("UNSAFE LOGIN:");

        String sql = "SELECT * FROM users WHERE id='" 
                     + username + "' AND pwd='" + password + "'";

        System.out.println("SQL Query: " + sql);

        // Simulating the unsafe query accepting injected input
        if (username.contains("' OR '1'='1'")) {
            System.out.println("Login Successful!");
        }

        System.out.println("\nSAFE LOGIN:");

        String correctUsername = "admin";
        String correctPassword = "1234";

        if (username.equals(correctUsername) &&
            password.equals(correctPassword)) {
            System.out.println("Login Successful!");
        } else {
            System.out.println("Login Failed!");
        }

        // Damaged file demonstration
        System.out.println("\nDAMAGED BUS PASS RECORDS:");

        FileWriter fw = new FileWriter("damaged.txt");

        fw.write("P1001,192311001,Arun,12,4500\n");
        fw.write("P1002,DAMAGED LINE\n");
        fw.write("P1003,192311003,Divya,7,ABC\n");
        fw.write("\n");

        fw.close();

        BufferedReader br = new BufferedReader(
            new FileReader("damaged.txt")
        );

        String line;

        while ((line = br.readLine()) != null) {

            String[] data = line.split(",", -1);

            if (line.isEmpty()) {
                System.out.println("Empty line detected");
            }
            else if (data.length != 5) {
                System.out.println("Wrong number of fields: " + line);
            }
            else {
                try {
                    Double.parseDouble(data[4]);
                    System.out.println("Valid record: " + line);
                }
                catch (NumberFormatException e) {
                    System.out.println("Invalid amount: " + line);
                }
            }
        }

        br.close();
    }
}
