import java.util.Stack;
import java.util.Queue;

public class Teacher { 
    // Used to differentiate different teachers instantiated with the no-param constructor
    // Increases after each no-param call;
    private static int teacherID = 1;

    private String name;
    private Experience experience;

    // the student that the teacher is currenlty working with
    // Is null when the teacher is not helping anyone
    private HelpRequest currentRequest;

    // stores the HelpRequests that haven't been sorted into requestsStack or requestsQueue yet
    private ListQueue<HelpRequest> incomingRequests;

    private ArrayStack<HelpRequest> requestsStack;
    private ListQueue<HelpRequest> requestsQueue;

    public Teacher() {
        name = "Teacher" + teacherID++;
        experience = new Experience(Experience.ExperienceLevel.FIRST_YEAR);

        incomingRequests = new ListQueue<>();

        requestsStack = new ArrayStack<>();
        requestsQueue = new ListQueue<>();
    }

    public Teacher(String name, Experience experience) {
        this.name = name;
        this.experience = experience;

        incomingRequests = new ListQueue<>();

        requestsStack = new ArrayStack<>();
        requestsQueue = new ListQueue<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public void setExperience(Experience.ExperienceLevel experienceLevel) {
        this.experience = new Experience(experienceLevel);
    }

    public void setExperience(String experienceStr) {
        this.experience = new Experience(experienceStr);
    }

    /**
     * Adds a new HelpRequest to incomingRequests
     * 
     * @param request a HelpRequest representing a student coming in for help
     */
    public void addIncomingRequest(HelpRequest request) {
        incomingRequests.add(request);
    }

    public HelpRequest getCurrentRequest() {
        return currentRequest;
    }

    /**
     * Returns true if the teacher is not helping a student
     * Returns false otherwise
     * 
     * @return whether currentRequest is null(i.e. the teacher is not helping any student)
     */
    public boolean isIdle() {
        return currentRequest == null;
    }

    /**
     * The teacher accepts requests new into the stack or the queue
     * Varies depending on the teachers level of experience
     */
    public void acceptRequests() {
        HelpRequest incomingStudent = incomingRequests.remove();

        switch(experience.getExperienceLevel()) {
            case Experience.ExperienceLevel.EXPERIENCED -> {
                switch (incomingStudent.getDemeanor().getDemeanorType()) {
                    case RUDE -> {

                    }
                }
            }

            case Experience.ExperienceLevel.INTERMEDIATE -> {
                requestsQueue.add(incomingStudent);
            }

            case Experience.ExperienceLevel.FIRST_YEAR -> {

            }

            case Experience.ExperienceLevel.UNDEFINED -> {
                throw new IllegalStateException("The `experience` property of a Teacher object must be set before calling acceptRequests()");
            }
        }
    }

    /**
     * Decreases the time left on the current request by 1
     * 
     * @return a boolean representing whether the current student has fixed their error
     */
    public boolean helpCurrentStudent() {
        // decrease the amount of time left on the student's error by 1 minute
        currentRequest.getError().decrementTimeUntilFixed();

        // if the current student is done
        if (currentRequest.errorFixed()) {
            // the current student leaves and the teacher moves on to the next student
            moveOnToNextStudent();
            return true;
        }

        return false;
    }

    /**
     * Called after the teacher is done helping a student
     * The teacher moves on to the next student that needs help
     * Accepts HelpRequests from the requestsStack before requestsQueue
     * 
     * @return Whether the teacher successfully moved on to the next student
     *         Returns false if there are no more students
     */
    public boolean moveOnToNextStudent() {
        // Prioritize the stack over the queue
         if (!requestsStack.isEmpty()) {
            currentRequest = requestsStack.pop();
            return true;
        }

        // If the stack is empty, process the queue
        if (!requestsQueue.isEmpty()) {
            currentRequest = requestsQueue.remove();
            return true;
        }

        // If both are empty, there are no students to help
        currentRequest = null;

        return false;
    }

    @Override
    public String toString() {
        return name + "\nExperience: "  + experience;
    }
}