
import java.util.HashSet;

class Student {

    String regNo;
    String name;

    Student(String regNo, String name) {
        this.regNo = regNo;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Student))
            return false;

        Student s = (Student) obj;

        return regNo.equals(s.regNo);
    }

    // Correct hashCode implementation
    @Override
    public int hashCode() {
        return regNo.hashCode();
    }
}

public class Q3_HashSetTest {

    public static void main(String[] args) {

        // TEST 1: Same object inserted twice
        HashSet<Student> set1 = new HashSet<>();

        Student s1 = new Student("101", "Karthik");

        set1.add(s1);
        set1.add(s1);

        System.out.println("TEST 1: Same Object");
        System.out.println("Set Size: " + set1.size());

        // TEST 2: Different objects with same register number
        HashSet<Student> set2 = new HashSet<>();

        Student s2 = new Student("102", "Ravi");
        Student s3 = new Student("102", "Ravi Kumar");

        set2.add(s2);
        set2.add(s3);

        System.out.println("\nTEST 2: Duplicate Register Number");
        System.out.println("Set Size: " + set2.size());

        // TEST 3: Different students
        HashSet<Student> set3 = new HashSet<>();

        Student s4 = new Student("103", "Arun");
        Student s5 = new Student("104", "Kumar");

        set3.add(s4);
        set3.add(s5);

        System.out.println("\nTEST 3: Different Students");
        System.out.println("Set Size: " + set3.size());

        // TEST 4: Change register number after insertion
        HashSet<Student> set4 = new HashSet<>();

        Student s6 = new Student("105", "Manoj");

        set4.add(s6);

        System.out.println("\nTEST 4: Before Changing Register Number");
        System.out.println("Contains Student: " + set4.contains(s6));

        s6.regNo = "106";

        System.out.println("After Changing Register Number");
        System.out.println("Contains Student: " + set4.contains(s6));

        // Correct implementation verification
        HashSet<Student> set5 = new HashSet<>();

        set5.add(new Student("107", "Ram"));
        set5.add(new Student("107", "Ram Kumar"));

        System.out.println("\nCORRECTED HASHSET TEST");
        System.out.println("Unique Students: " + set5.size());
    }
}
