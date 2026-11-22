
public class Q1StringBenchmark {

    static String usingString(String[] fields) {
        String ticket = "";

        for (String field : fields) {
            ticket = ticket + field + "|";
        }
        return ticket;
    }

    static String usingStringBuilder(String[] fields) {
        StringBuilder ticket = new StringBuilder();

        for (String field : fields) {
            ticket.append(field).append("|");
        }
        return ticket.toString();
    }

    static String usingStringBuffer(String[] fields) {
        StringBuffer ticket = new StringBuffer();

        for (String field : fields) {
            ticket.append(field).append("|");
        }
        return ticket.toString();
    }

    static String[] createFields(int count) {
        String[] fields = new String[count];

        for (int i = 0; i < count; i++) {
            fields[i] = "Field" + (i + 1);
        }
        return fields;
    }

    static long test(String[] fields, int tickets, int choice) {
        long result = 0;

        for (int i = 0; i < tickets; i++) {
            String ticket;

            if (choice == 1)
                ticket = usingString(fields);
            else if (choice == 2)
                ticket = usingStringBuilder(fields);
            else
                ticket = usingStringBuffer(fields);

            result += ticket.length();
        }
        return result;
    }

    static void measure(String name, String[] fields,
                        int tickets, int choice) {

        for (int i = 0; i < 3; i++)
            test(fields, tickets, choice);

        long totalTime = 0;
        long result = 0;

        for (int i = 0; i < 5; i++) {
            long start = System.nanoTime();

            result = test(fields, tickets, choice);

            long end = System.nanoTime();
            totalTime += end - start;
        }

        double average = totalTime / 5.0 / 1000000.0;

        System.out.println(name);
        System.out.printf("Average time: %.3f ms%n", average);
        System.out.println("Result: " + result);
        System.out.println("--------------------------");
    }

    static void workload(String name, int tickets, int fieldsCount) {

        System.out.println("\n" + name);
        System.out.println("Tickets: " + tickets);
        System.out.println("Fields: " + fieldsCount);

        String[] fields = createFields(fieldsCount);

        measure("String", fields, tickets, 1);
        measure("StringBuilder", fields, tickets, 2);
        measure("StringBuffer", fields, tickets, 3);
    }

    public static void main(String[] args) {

        workload("SHORT TICKET", 1, 6);

        workload("MANY TICKETS", 10000, 50);

        workload("LARGE TICKETS", 1000, 200);

        java.util.StringJoiner ticket =
                new java.util.StringJoiner("|");

        ticket.add("PNR12345");
        ticket.add("Karthik");
        ticket.add("S2");
        ticket.add("B12");

        System.out.println("\nStringJoiner Alternative:");
        System.out.println(ticket);
    }
}
