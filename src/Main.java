import java.io.*;
import java.time.format.*;
import java.util.Scanner;

public class Main {
    private static final Scanner INPUT = new Scanner(System.in);
    
	private static final Time MIN_TIME = new Time (14, 15);
	private static final Time MAX_TIME = new Time (15, 30);

    private static void printGreeting() {
        System.out.println("Hello, welcome to Tutor Me Next.");
        System.out.println("In case you haven't read the README.md,");
        System.out.println("this is a simulation of a computer science teacher helping students with their errors after school.");
    }

    /**
     * Reads the input file of Help Requests and saves them to a ListQueue
     * 
     * @return a ListQueue of HelpRequests objects with all properties set
     */
    private static ListQueue<HelpRequest> readInputFile() {
        // keep prompting the user to choose a file until the user enters an existing file
        String filePath;
        BufferedReader fileData;
        do {
            System.out.println("Enter the name of the input file. Exclude the directory(i.e. \"../input/\"): ");
            filePath = INPUT.nextLine();

            try {
                // extract the data from the input file
                fileData = new BufferedReader(new FileReader("../input/" + filePath));
                break;

            } catch (FileNotFoundException e) {
                System.out.println();
                System.out.println("The file " + filePath + " does not exist.");
                System.out.println();
            }
        } while (true);
        
        ListQueue<HelpRequest> requestsQueue = new ListQueue<>();

        System.out.println();
        System.out.println("Reading input from " + filePath + "...");

        // while the end of the file has not been 
        String line;
        try {
            while ((line = fileData.readLine()) != null) {
                HelpRequest helpRequest = new HelpRequest();
    
                helpRequest.setTimeStamp(line);
                helpRequest.setName(fileData.readLine());
                helpRequest.setDemeanor(fileData.readLine());
                helpRequest.setError(fileData.readLine());
    
                // convert the Strings into ints before storing them
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

            fileData.close();

        } catch (IOException e) {
            System.out.println("An error has occurred while reading the input file. Check that there are no errors in the format.");
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("Finished reading input from " + filePath);

        return requestsQueue;
    }

    /**
     * Asks the user to input the teacher's name and experience. 
     * Prevents the user from inputting an invalid experience
     * 
     * @return a Teacher object with its name and experiene set
     */
    private static Teacher promptTeacherInfo() {
        Teacher teacher = new Teacher();

        System.out.print("Enter the teacher's name: ");
        teacher.setName(INPUT.nextLine());

        // keep asking for the teacher's experience until a valid input is given
        do {
            System.out.print("Enter the teacher's level of experience(\"First Year\", \"Intermediate\", \"Experienced\"): ");

            try {
                teacher.setExperience(INPUT.nextLine());
                break;

            } catch (IllegalArgumentException e) {
                System.out.println();
                System.out.println("Invalid teacher experience.");
                System.out.println();
            }
            
        } while (true);

        return teacher;
    }

    /**
     * Ask the user to input a time
     * It can not be before MIN_TIME or after MAX_TIME
     * 
     * @param prompt the prompt that is printed out
     */
    private static Time promptTime(String prompt) {
        Time time = new Time();

        // keep asking for a time until the user enters a valid one
        do {
            System.out.print(prompt);

            try {
                time = new Time(INPUT.nextLine());

            } catch (DateTimeParseException e) {
                System.out.println();
                System.out.println("Invalid time. Try again");
            }

        } while (time.isAfter(MAX_TIME) || time.isBefore(MIN_TIME));

        return time;
    }

    private static Time promptTime() {
        return promptTime("Enter a time from " + MIN_TIME + " to " + MAX_TIME + ": ");
    }

    /** 
     * 
     * @param teacher the teacher object that is going to be used in the simulation
     */
    private static void printHeader(FileWriter outputFile, Teacher teacher) {
        try {
            outputFile.append(teacher.toString());

            outputFile.append("\n\nSimulation Run\n");
            outputFile.append("==============\n");

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the output file(output/output.txt).");
            e.printStackTrace();
            System.exit(1);
        }
    } 

    /**
     * Prints out the final report after the simulation is done running
     * 
     * @param teacher the teacher object that was used in the simulatoin
     */
    private static void printEndReport(FileWriter outputFile, Teacher teacher, Result result) {
        try {
            // print out the teacher's information
            outputFile.append("\n");
            outputFile.append("Teacher At End\n");
            outputFile.append("==============\n");
            outputFile.append(teacher.toString());

            outputFile.append("\n\n");
            outputFile.append("Simulation summary\n");
            outputFile.append("==================\n");
            outputFile.append(result.toString());

            outputFile.close();

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the output file(../output/output.txt).");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        FileWriter outputFile;
        try {
            outputFile = new FileWriter("../output/output.txt");

        } catch (IOException e) {
            System.out.println("\nThe file output.txt either can't be created or can't be editted. Check your permissions.");
            e.printStackTrace();
            return;
        }

        printGreeting();
        System.out.println();

        ListQueue<HelpRequest> pendingRequests = readInputFile();
        System.out.println();

        Teacher teacher = promptTeacherInfo();
        teacher.addPendingRequests(pendingRequests);
        System.out.println();

        Time startTime = promptTime("Enter the start time(" + MIN_TIME + "-" + MAX_TIME + "): ");
        Time endTime = promptTime("Enter the end time(" + MIN_TIME + "-" + MAX_TIME + "): ");

        printHeader(outputFile, teacher);

        Simulation simulation = new Simulation(teacher, startTime, endTime);

        try {
            while (!simulation.getFinished()) {
                outputFile.append(simulation.runOneMinute());
            }

        } catch (IOException e) {
            System.out.println("\nThe file output.txt either can't be created or can't be editted. Check your permissions.");
            e.printStackTrace();
            return;
        }

        printEndReport(outputFile, teacher, simulation.getSimulationResult());
    }
}