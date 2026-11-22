
import java.util.*;

class Student {
    int id;
    String name;
    String course;

    Student(int id, String name, String course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }

    public String toString() {
        return id + " " + name + " " + course;
    }
}

// Generic DAO interface
interface Dao<T, K> {
    void insert(T student);
    T findById(K id);
    List<T> findAll();
    void update(T student);
    void delete(K id);
}

// Student DAO implementation
class StudentDao implements Dao<Student, Integer> {

    ArrayList<Student> students = new ArrayList<>();

    public void insert(Student student) {
        students.add(student);
        System.out.println("Student inserted");
    }

    public Student findById(Integer id) {
        for (Student s : students) {
            if (s.id == id) {
                return s;
            }
        }
        return null;
    }

    public List<Student> findAll() {
        return students;
    }

    public void update(Student student) {
        for (Student s : students) {
            if (s.id == student.id) {
                s.name = student.name;
                s.course = student.course;
                System.out.println("Student updated");
                return;
            }
        }
        System.out.println("Student not found");
    }

    public void delete(Integer id) {
        students.removeIf(s -> s.id == id);
        System.out.println("Student deleted");
    }
}

// Main class
class A3 {
    public static void main(String[] args) {

        StudentDao dao = new StudentDao();

        dao.insert(new Student(101, "Arun", "AIML"));
        dao.insert(new Student(102, "Divya", "CSE"));

        System.out.println("\nAll Students:");
        for (Student s : dao.findAll()) {
            System.out.println(s);
        }

        System.out.println("\nSearch Student:");
        System.out.println(dao.findById(101));

        System.out.println("\nUpdate Student:");
        dao.update(new Student(101, "Arun Kumar", "AIML"));

        System.out.println(dao.findById(101));

        System.out.println("\nDelete Student:");
        dao.delete(102);

        System.out.println("\nFinal Student List:");
        for (Student s : dao.findAll()) {
            System.out.println(s);
        }
    }
}
