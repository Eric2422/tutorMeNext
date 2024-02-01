import java.io.File;

public class Main {
    public static void main(String[] args) {
        Teacher teacher = new Teacher("The Teacher", new Experience("FIRST_YEAR"));
        System.out.println(teacher);

        Error error = new Error("compiling", 3, 15);
        Student student = new Student("Eric Zhang", "POLITE");
    }
}