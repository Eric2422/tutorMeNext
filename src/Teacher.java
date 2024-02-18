import java.util.ArrayList;
import java.util.Collection;

public class Teacher { 
    // Used to differentiate different teachers instantiated with the no-param constructor
    // Increases after each no-param call;
    private static int teacherID = 1;

    private String name;
    private Experience experience;

    // the student that the teacher is currently working with
    // Is null when the teacher is not helping anyone
    private HelpRequest currentRequest;

    // stores the HelpRequests that haven't been sorted into requestsStack or requestsQueue yet
    private ListQueue<HelpRequest> pendingRequests;

    private ArrayStack<HelpRequest> requestsStack;
    private ListQueue<HelpRequest> requestsQueue;

    public Teacher() {
        name = "Teacher" + teacherID++;
        experience = new Experience(Experience.ExperienceLevel.FIRST_YEAR);

        pendingRequests = new ListQueue<>();

        requestsStack = new ArrayStack<>();
        requestsQueue = new ListQueue<>();
    }

    public Teacher(String name, Experience experience) {
        this.name = name;
        this.experience = experience;

        pendingRequests = new ListQueue<>();

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
     * Adds a new HelpRequest to pendingRequests
     * 
     * @param request a HelpRequest representing a student coming in for help
     */
    public void addPendingRequest(HelpRequest request) {
        pendingRequests.add(request);
    }

    /**
     * Adds one or more new HelpRequests to pendingRequests
     * 
     * @param requests a Collection of HelpRequests representing multiple students coming in for help
     */
    public void addPendingRequests(Collection<HelpRequest> requests) {
        pendingRequests.addAll(requests);
    }

    public HelpRequest getCurrentRequest() {
        return currentRequest;
    }

    public int getNumberOfWaitingStudents() {
        return requestsQueue.size() + requestsStack.size();
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
     * Returns if there are any students in requestsStack or requestsQueue
     */
    public boolean hasWaitingStudents() {
        return !requestsStack.isEmpty() || !requestsQueue.isEmpty();
    }

    /**
     * Whether there are any requests that the teacher can accept
     */
    public boolean hasRequestsToAccept(Time currentTime) {
        return pendingRequests.size() != 0 && !pendingRequests.peek().getTimeStamp().isAfter(currentTime);
    }

    /**
     * The teacher accepts requests new into the stack or the queue
     * Varies depending on the teachers level of experience
     * 
     * @param  currentTime the currentTime of the simulation. Used to determine which requests the teacher can accept
     * @return ArrayList containg the HelpRequests that are accepted
     */
    public ArrayList<HelpRequest> acceptRequests(Time currentTime) {
        ArrayList<HelpRequest> acceptedRequests = new ArrayList<>();

        // loop through pendingRequests and find all the requests that have arrived
        while (hasRequestsToAccept(currentTime)) {
            HelpRequest incomingStudent = pendingRequests.remove();
            acceptedRequests.add(incomingStudent);

            switch(experience.getExperienceLevel()) {
                case EXPERIENCED  -> acceptRequestsExperienced(incomingStudent);
                case INTERMEDIATE -> requestsQueue.add(incomingStudent);
                case FIRST_YEAR   -> acceptRequestsFirstYear(incomingStudent);
                case UNDEFINED    -> throw new IllegalStateException("The `experience` property of a Teacher object must be set before calling acceptRequests()");
            }
        }   

        return acceptedRequests;
    }

    /**
     * Helper method for accepting requests when the teacher is experienced
     * 
     * @param incomingStudent the student that is being moved into the stack or queue
     */
    public void acceptRequestsExperienced(HelpRequest incomingStudent) {
        switch (incomingStudent.getDemeanor().getDemeanorType()) {
            case RUDE -> {
                requestsQueue.add(incomingStudent);
            }

            case POLITE -> {
                switch (incomingStudent.getError().getErrorType()) {
                    case COMPILING, LINKING -> requestsStack.push(incomingStudent);
                    case RUNTIME            -> requestsQueue.add(incomingStudent);
                }
            }

            case UNDEFINED -> {
                throw new IllegalStateException("The `demeanor` property of a Student object must be set before it can be used.");
            }
        }
    }

    /**
     * Helper method for accepting requests when the teacher is only FIRST_YEAR
     * 
     * @param incomingStudent the student that is being moved into the stack or queue
     */
    public void acceptRequestsFirstYear(HelpRequest incomingStudent) {
        switch (incomingStudent.getDemeanor().getDemeanorType()) {
            case RUDE -> requestsStack.push(incomingStudent);
            case POLITE -> requestsQueue.add(incomingStudent);
            case UNDEFINED -> throw new IllegalStateException("The `demeanor` property of a Student object must be set before it can be used.");
        }
    }

    /**
     * Decreases the time left on the current request by 1
     * 
     * @return a boolean representing whether the current student has fixed their error
     */
    public boolean helpCurrentStudent() {
        // decrease the amount of time left on the student's error by 1 minute
        currentRequest.getError().decrementMinutesUntilFixed();

        // if the current student is done
        if (currentRequest.errorFixed()) {
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
    public HelpRequestSource moveOnToNextStudent() {
        // Prioritize the stack over the queue
         if (!requestsStack.isEmpty()) {
            currentRequest = requestsStack.pop();
            return HelpRequestSource.STACK;
        }

        // If the stack is empty, process the queue
        if (!requestsQueue.isEmpty()) {
            currentRequest = requestsQueue.remove();
            return HelpRequestSource.QUEUE;
        }

        // If both are empty, there are no students to help
        currentRequest = null;
        return HelpRequestSource.UNDEFINED;
    }

    @Override
    public String toString() {
        String str = 
               "Teacher: " 
             + "\n\t\"" + name + "\""
             + "\n\t" + experience
             + "\nCurrent Request: \n" + currentRequest
             + "\n\nTeacher Request Stack:\n" + requestsStack
             + "\nTeacher Request Queue:\n" + requestsQueue;

        return str;
    }
}