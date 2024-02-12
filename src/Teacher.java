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

    private ArrayStack<HelpRequest> requestsStack;
    private ListQueue<HelpRequest> requestsQueue;

    public Teacher() {
        name = "Teacher" + teacherID++;
        experience = new Experience(Experience.ExperienceLevel.FIRST_YEAR);

        requestsStack = new ArrayStack<>();
        requestsQueue = new ListQueue<>();
    }

    public Teacher(String name, Experience experience) {
        this.name = name;
        this.experience = experience;

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
     * The teacher accepts requests new into the stack or the queue
     * Varies depending on the teachers level of experience
     */
    public void acceptRequests() {
        switch(experience.getExperience()) {
            case Experience.ExperienceLevel.EXPERIENCED -> {

            }

            case Experience.ExperienceLevel.INTERMEDIATE -> {

            }

            case Experience.ExperienceLevel.FIRST_YEAR -> {

            }

            case Experience.ExperienceLevel.UNDEFINED -> {
                throw new IllegalStateException("The `experience` property of a Teacher object must be set before calling acceptRequests()")
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
            // they leave and the teacher moves onto the next student
            currentRequest = null;
            return true;
        }

        return false;
    }

    /**
     * 
     */
    public boolean 

    @Override
    public String toString() {
        return name + "\nExperience: "  + experience;
    }
}