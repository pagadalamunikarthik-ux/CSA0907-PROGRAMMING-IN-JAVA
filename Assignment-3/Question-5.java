
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class SeatBooking {

    private final boolean[] booked;
    private int bookingsMade = 0;

    SeatBooking(int seats) {
        booked = new boolean[seats];
    }

    // Booking without synchronization
    boolean bookUnsafe(int seat, CyclicBarrier barrier)
            throws InterruptedException, BrokenBarrierException {

        if (!booked[seat]) {

            barrier.await();

            booked[seat] = true;
            bookingsMade++;

            return true;
        }

        return false;
    }

    // Booking using synchronized method
    synchronized boolean bookSynchronized(int seat) {

        if (!booked[seat]) {
            booked[seat] = true;
            bookingsMade++;
            return true;
        }

        return false;
    }

    // Booking using synchronized block
    boolean bookWithBlock(int seat) {

        synchronized (this) {

            if (!booked[seat]) {
                booked[seat] = true;
                bookingsMade++;
                return true;
            }

            return false;
        }
    }

    int getBookingsMade() {
        return bookingsMade;
    }

    public static void main(String[] args)
            throws InterruptedException {

        int users = 100;
        int seats = 10;

        // Test 1: Without synchronization
        SeatBooking unsafe = new SeatBooking(seats);

        AtomicInteger unsafeCount = new AtomicInteger(0);

        CyclicBarrier[] barriers = new CyclicBarrier[seats];

        for (int i = 0; i < seats; i++) {
            barriers[i] = new CyclicBarrier(10);
        }

        Thread[] t1 = new Thread[users];

        for (int i = 0; i < users; i++) {

            final int seat = i % seats;

            t1[i] = new Thread(() -> {
                try {
                    if (unsafe.bookUnsafe(seat, barriers[seat])) {
                        unsafeCount.incrementAndGet();
                    }
                } catch (Exception e) {
                    System.out.println("Booking error");
                }
            });

            t1[i].start();
        }

        for (Thread t : t1) {
            t.join();
        }

        System.out.println("Without synchronization:");
        System.out.println("Seats sold: " + unsafeCount.get());

        // Test 2: Synchronized method
        SeatBooking method = new SeatBooking(seats);

        Thread[] t2 = new Thread[users];

        for (int i = 0; i < users; i++) {

            final int seat = i % seats;

            t2[i] = new Thread(() ->
                method.bookSynchronized(seat)
            );

            t2[i].start();
        }

        for (Thread t : t2) {
            t.join();
        }

        System.out.println("\nSynchronized method:");
        System.out.println("Seats sold: " + method.getBookingsMade());

        // Test 3: Synchronized block
        SeatBooking block = new SeatBooking(seats);

        Thread[] t3 = new Thread[users];

        for (int i = 0; i < users; i++) {

            final int seat = i % seats;

            t3[i] = new Thread(() ->
                block.bookWithBlock(seat)
            );

            t3[i].start();
        }

        for (Thread t : t3) {
            t.join();
        }

        System.out.println("\nSynchronized block:");
        System.out.println("Seats sold: " + block.getBookingsMade());
    }
}
