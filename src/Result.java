import java.io.FileWriter;

public class Result {
    // the number of students that the teacher helped
    private int studentsHelped;

    // the total of how long the students had to wait for help
    // in minutes
    private int totalWaitTime;

    public Result() {
        studentsHelped = 0;
        totalWaitTime = 0;
    }

    public Result(int studentsHelped, int totalWaitTime) {
        this.studentsHelped = studentsHelped;
        this.totalWaitTime = totalWaitTime;
    }

    public int getStudentsHelped() {
        return studentsHelped;
    }

    public void incrementStudentsHelped() {
        studentsHelped++;
    }

    public void incrementStudentsHelped(int studentsHelped) {
        this.studentsHelped += studentsHelped;
    }

    public int getTotalWaitTime() {
        return totalWaitTime;
    }

    public void incrementTotalWaitTime() {
        totalWaitTime++;
    }

    public void incrementTotalWaitTime(int minutes) {
        totalWaitTime += minutes;
    }

    public double getMeanWaitTime() {
        return (double) totalWaitTime / studentsHelped;
    }

    @Override
    public String toString() {
        return "Number of Students Helped: " + studentsHelped
             + "\nTotal Wait Time: " + totalWaitTime
             + "\nAverage Wait Time: " + getMeanWaitTime();
    }
}