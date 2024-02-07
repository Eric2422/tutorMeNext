import java.util.Stack;
import java.util.Queue;

public class Teacher { 
    private String name;
    private Experience experience;

    // the student that the teacher is currenlty working with
    // Is null when the teacher is not helping anyone
    private HelpRequest currentRequest;

    private Stack<HelpRequest> requestsStack;
    private Queue<HelpRequest> requestsQueue;

    public Teacher() {

    }

    public Teacher(String name, Experience experience) {
        this.name = name;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    /**
     * The teacher accepts requests from the stack or the queue
     */
    public static void acceptRequests() {
    }
}