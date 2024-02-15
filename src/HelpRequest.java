import java.time.LocalTime;

public class HelpRequest { 
    private Student student;
    private Error error;

    // the time that the request was made
    private Time time;

    public HelpRequest() {
        this.student = new Student();
        this.error = new Error();
        this.time = new Time();
    }

    public HelpRequest(Student student, Error error, Time time) {
        this.student = student;
        this.error = error;
        this.time = time;
    }

    public Student getStudent() {
        return student;
    }

    public String getName() {
        return student.getName();
    }

    public Demeanor getDemeanor() {
        return student.getDemeanor();
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setStudent(String name, Demeanor demeanor) {
        student = new Student(name, demeanor);
    }

    public void setStudent(String name, Demeanor.DemeanorType demeanorType) {
        student = new Student(name, demeanorType);
    }

    public void setDemeanor(Demeanor demeanor) {
        student.setDemeanor(demeanor);
    }

    public Error getError() { 
        return error;
    }

    public int getMinutesWithoutHelp() {
        return error.minutesWithoutHelp();
    }

    public int getMinutesWithHelp() {
        return error.getMinutesWithHelp();
    }

    public void setError(Error error) {
        this.error = error;
    }

    public void setError(Error.ErrorType error) {
        this.error = new Error(error);
    }

    /**
     * Returns whether the student's error is fixed
     * 
     * @return a boolean representing whether the timeUntilFinished value of error is 0
     */
    public boolean errorFixed() {
        return error.getTimeUntilFixed() == 0;
    }

    public void setError(String errorStr) {
        this.error = new Error(errorStr);
    }

    public Time getTime() {
        return time;
    }

    public void setTime(int hour, int minute) {
        this.time = new Time(hour, minute);
    }

    public void setTime(LocalTime localTime) {
        this.time = new Time(localTime);
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
