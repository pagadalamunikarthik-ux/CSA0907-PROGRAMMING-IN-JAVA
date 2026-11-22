
class A5 {
    public static void main(String[] args) {

        int hostelWallet = 2000;
        int canteenWallet = 1000;

        System.out.println("Before Transaction:");
        System.out.println("Hostel Wallet: " + hostelWallet);
        System.out.println("Canteen Wallet: " + canteenWallet);

        // Transaction
        hostelWallet = hostelWallet - 500;
        canteenWallet = canteenWallet + 500;

        System.out.println("\nTransaction Successful!");

        System.out.println("After Transaction:");
        System.out.println("Hostel Wallet: " + hostelWallet);
        System.out.println("Canteen Wallet: " + canteenWallet);

        // Safe login demonstration
        String username = "admin";
        String password = "1234";

        if (username.equals("admin") && password.equals("1234")) {
            System.out.println("\nLogin Successful!");
        } else {
            System.out.println("\nInvalid Login!");
        }
    }
}
