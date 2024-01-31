import java.io.File;

public class Main {
    public static void main(String[] args) {
        Teacher teacher = new Teacher("Eric Zhang", new Experience("FIRST_YEAR"));

        Error error = new Error("compiling", 3, 15);
        Student student = new Student();
    }
}