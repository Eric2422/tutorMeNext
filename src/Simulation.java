import java.util.ArrayList;

/**
 * Used to simulate one session of tutoring students
 */
public class Simulation {
    private final Teacher teacher;
    
    private final Time startTime;
    private final Time endTime;
    private final Time currentTime;

    private final Result simulationResult;
    private boolean finished;

    public Simulation(Teacher teacher, Time startTime, Time endTime) {
        this.teacher = teacher;

        this.startTime = startTime;
        this.currentTime = new Time(startTime);
        this.endTime = endTime;

        simulationResult = new Result();

        finished = false;
    }

    private String helpNewStudent() {
        String str = "";

        HelpRequestSource requestSource = teacher.moveOnToNextStudent();

        if (requestSource == HelpRequestSource.UNDEFINED) {
            str += "No one to help.\n";

        } else {
            str += "Now helping new student from " + requestSource + ":\n";

            // since the student is now receiving help, their minutes left is set to minutesWithHelp
            teacher.getCurrentRequest().getsHelp();

            str += teacher.getCurrentRequest().toString();
            str += "\n";

            int waitTime = currentTime.compareTo(teacher.getCurrentRequest().getTimeStamp());
            simulationResult.incrementTotalWaitTime(waitTime);
        }

        return str;
    }

    /**
     * Runs one minute of the simulation
     * 
     * @return the String that is written to the output file
     */
    public String runOneMinute() {
        String outputString = "Time: " + currentTime + "\n";

        // if the teacher is  helping someone
        if (!teacher.isIdle()) {
            if (teacher.helpCurrentStudent()) {
                outputString += "Problem fixed: \n";
                outputString += teacher.getCurrentRequest() + "\n";

                simulationResult.incrementStudentsHelped();

                outputString += "\n" + helpNewStudent();

            } else {
                outputString += "Helping: \n";
                outputString += teacher.getCurrentRequest() + "\n";
            }

        } else if (teacher.hasRequestsToAccept(currentTime)) {
            ArrayList<HelpRequest> acceptedRequests = teacher.acceptRequests(currentTime);

            outputString += teacher.getName() + " accepts requests: \n";

            for (HelpRequest request : acceptedRequests) {
                outputString += request.toString() + "\n";
            }

        } else /*if (teacher.isIdle() && teacher.hasWaitingStudents())*/ {
            outputString += helpNewStudent();
        }

        currentTime.incrementTime();

        // if the time has reached endTime, end the simulation
        if (currentTime.isAfter(endTime)) {
            finished = true;
        }

        return outputString + "\n";
    }

    public boolean getFinished() {
        return finished;
    }

    public Result getSimulationResult() {
        return simulationResult;
    }
}