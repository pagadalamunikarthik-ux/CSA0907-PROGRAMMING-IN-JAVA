
class ScannerThread extends Thread {

    int scanned = 0;

    ScannerThread(String name) {
        super(name);
    }

    public void run() {

        for (int i = 1; i <= 5; i++) {

            scanned++;

            System.out.println(
                getName() + " scanned script " + i
            );

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        System.out.println(getName() + " completed.");
    }
}

class ScannerTask implements Runnable {

    int scanned = 0;
    String name;

    ScannerTask(String name) {
        this.name = name;
    }

    public void run() {

        for (int i = 1; i <= 5; i++) {

            scanned++;

            System.out.println(
                name + " scanned script " + i
            );

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        System.out.println(name + " completed.");
    }
}

public class ScannerDemo {

    public static void main(String[] args)
            throws InterruptedException {

        // Creating two threads using Thread class
        ScannerThread station1 =
            new ScannerThread("Station 1");

        ScannerThread station2 =
            new ScannerThread("Station 2");

        // Creating two threads using Runnable
        ScannerTask station3 =
            new ScannerTask("Station 3");

        ScannerTask station4 =
            new ScannerTask("Station 4");

        Thread t3 = new Thread(station3);
        Thread t4 = new Thread(station4);

        // Starting all four threads
        station1.start();
        station2.start();
        t3.start();
        t4.start();

        System.out.println("\nThreads started.");

        System.out.println(
            "Station 1 alive: " + station1.isAlive()
        );

        // Waiting for all threads to finish
        station1.join();
        station2.join();
        t3.join();
        t4.join();

        System.out.println("\nAll stations completed.");

        System.out.println(
            "Total scripts scanned: " +
            (station1.scanned + station2.scanned +
             station3.scanned + station4.scanned)
        );
    }
}
