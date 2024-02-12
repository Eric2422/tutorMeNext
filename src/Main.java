import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ListQueue<Character> queue = new ListQueue<>();

        String str = "Hello world!";

        for (int i=0; i<str.length(); i++) {
            queue.add(str.charAt(i));
        }

        for (int i=0; i<str.length(); i++) {
            System.out.println(queue.remove());
        }
    }
}