import java.util.Scanner;
import java.io.*;

public class Main {
	private static final Time EARLIEST_TIME = new Time (14, 15);
	private static final Time LATEST_TIME = new Time (15, 30);
	private static final int PAUSE_LENGTH = 20;

    private static String getFileName() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the name of the input file: ");
        return input.nextLine();
    }

    /**
     * Reads the input file of Help Requests and saves them to a ListQueue
     * 
     * @return a ListQueue of HelpRequests objects with all properties set
     */
    private static ListQueue<HelpRequest> readInputfile(String filePath) throws IOException {
        ListQueue<HelpRequest> requestsQueue = new ListQueue<>();
        
        // extract the data from the input file
        BufferedReader fileData = new BufferedReader(new FileReader(filePath));
        
        // checks whether the end of the file has been reached
        String line;
        while ((line=fileData.readLine()) != null) {
            HelpRequest helpRequest = new HelpRequest();

            helpRequest.setTime(line);
            helpRequest.setName(fileData.readLine());
            helpRequest.setError(fileData.readLine());

            // convert the String into an int before saving it
            helpRequest.getError().setMinutesWithHelp(
                Integer.parseInt(fileData.readLine())
            );
            helpRequest.getError().setMinutesWithoutHelp(
                Integer.parseInt(fileData.readLine())
            );
            // since the student is by default not being helped,
            // set minuntesUntilFixed to minutesWithoutHelp
            helpRequest.getError().setMinutesUntilFixed(helpRequest.getError().getMinutesWithoutHelp());

            requestsQueue.add(helpRequest);
        }

        return requestsQueue;
    }

    /**
     * Asks the user to input the teacher's name and experience. 
     * Prevents the user from inputting an invalid experience
     * 
     * @return a Teacher object with its name and experiene set
     */
    public static Teacher getTeacherInfo() {
        Scanner input = new Scanner(System.in);

        Teacher teacher = new Teacher();
        System.out.print("Enter teacher's name: ");
        teacher.setName(input.nextLine());

        // keep asking for the teacher's experience until a valid input is given
        do {
            System.out.print("Enter the teacher's level of experience(\"First Year\", \"Intermediate\", \"Experienced\"): ");

            try {
                teacher.setExperience(input.nextLine());
                break;

            } catch (IllegalArgumentException e) {
                System.out.println();
                System.out.println("Invalid teacher experience.");
                System.out.println();
            }
            
        } while (true);

        return teacher;
    }

    public static Time getTime() {
        
    }

    public static void main(String[] args) {

    }
}