import java.io.FileWriter;

public class Result {
    // the number of students that the teacher helped
    private int studentsHelped = 0;
    private int totalWaitTime = 0;

    public Result(int studentsHelped, int totalWaitTime) {
        this.studentsHelped = studentsHelped;
        this.totalWaitTime = totalWaitTime;
    }

    public int getNumberHelped() {
        return studentsHelped;
    }

    public int getTotalWaitTime() {
        return totalWaitTime;
    }

    @Override
    public String toString() {
        return "Number of Students Helped: " + studentsHelped + "\nTotal Wait Time: " + totalWaitTime;
    }
}