import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
	private static final Time EARLIEST_TIME = new Time (14, 15);
	private static final Time LATEST_TIME = new Time (15, 30);
	private static final int PAUSE_LENGTH = 20;

    private static String getFileName() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the name of the input file: ");
        return input.nextLine();
    }

    private static ListQueue readInputfile(String filePath) {
        ListQueue<HelpRequest> requestsQueue = new ListQueue<>();

        // read the file 
        BufferedReader fileData = new BufferedReader(new FileReader(filePath));
        
        
    }

    public static void main(String[] args) {

    }
}