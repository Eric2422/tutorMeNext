import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Teacher teacher = new Teacher("The Teacher", new Experience("FIRST_YEAR"));
        // System.out.println(teacher);

        // Error error = new Error("compiling", 3, 15);
        // Student student = new Student("Eric Zhang", "POLITE");
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a time: ");
            String timeStr = input.nextLine();


            System.out.println("timeStr is a time: " + Time.parse(timeStr));
            System.out.println();
        }
    }
}