
import java.util.*;

public class Q2_ListBenchmark {

    static void test(int size) {

        System.out.println("\nPlaylist Size: " + size);

        ArrayList<String> arrayList = new ArrayList<>();
        LinkedList<String> linkedList = new LinkedList<>();

        // Add songs
        for (int i = 0; i < size; i++) {
            arrayList.add("Song" + i);
            linkedList.add("Song" + i);
        }

        // 1. Add at the end
        long start = System.nanoTime();

        arrayList.add("NewSong");

        long end = System.nanoTime();
        System.out.println("ArrayList Add End: " + (end - start) + " ns");

        start = System.nanoTime();

        linkedList.add("NewSong");

        end = System.nanoTime();
        System.out.println("LinkedList Add End: " + (end - start) + " ns");

        // 2. Insert at front
        start = System.nanoTime();

        arrayList.add(0, "FrontSong");

        end = System.nanoTime();
        System.out.println("ArrayList Add Front: " + (end - start) + " ns");

        start = System.nanoTime();

        linkedList.addFirst("FrontSong");

        end = System.nanoTime();
        System.out.println("LinkedList Add Front: " + (end - start) + " ns");

        // 3. Insert in middle
        int middle = size / 2;

        start = System.nanoTime();

        arrayList.add(middle, "MiddleSong");

        end = System.nanoTime();
        System.out.println("ArrayList Add Middle: " + (end - start) + " ns");

        start = System.nanoTime();

        linkedList.add(middle, "MiddleSong");

        end = System.nanoTime();
        System.out.println("LinkedList Add Middle: " + (end - start) + " ns");

        // 4. Remove from front
        start = System.nanoTime();

        arrayList.remove(0);

        end = System.nanoTime();
        System.out.println("ArrayList Remove Front: " + (end - start) + " ns");

        start = System.nanoTime();

        linkedList.removeFirst();

        end = System.nanoTime();
        System.out.println("LinkedList Remove Front: " + (end - start) + " ns");

        // 5. Access middle song
        start = System.nanoTime();

        arrayList.get(middle);

        end = System.nanoTime();
        System.out.println("ArrayList Get Middle: " + (end - start) + " ns");

        start = System.nanoTime();

        linkedList.get(middle);

        end = System.nanoTime();
        System.out.println("LinkedList Get Middle: " + (end - start) + " ns");

        // 6. Iterate through all songs
        start = System.nanoTime();

        for (String song : arrayList) {
            song.length();
        }

        end = System.nanoTime();
        System.out.println("ArrayList Iteration: " + (end - start) + " ns");

        start = System.nanoTime();

        for (String song : linkedList) {
            song.length();
        }

        end = System.nanoTime();
        System.out.println("LinkedList Iteration: " + (end - start) + " ns");

        // 7. ArrayDeque Up Next Queue
        ArrayDeque<String> queue = new ArrayDeque<>();

        start = System.nanoTime();

        queue.addFirst("NextSong");
        queue.addLast("LastSong");
        queue.removeFirst();

        end = System.nanoTime();

        System.out.println("ArrayDeque Queue Operations: "
                + (end - start) + " ns");
    }

    public static void main(String[] args) {

        test(1000);
        test(10000);
        test(50000);

    }
}
