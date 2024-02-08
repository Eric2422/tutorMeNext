import java.time.LocalTime;

public class HelpRequest { 
    private Student student;
    private Error error;

    // the time that the request was made
    private Time timeStamp;

    public HelpRequest() {
        this.student = new Student();
        this.error = new Error();
        this.timeStamp = new Time();
    }

    public HelpRequest(Student student, Error error, Time timeStamp) {
        this.student = student;
        this.error = error;
        this.timeStamp = timeStamp;
    }

    public Student getStudent() {
        return student;
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

    public Error getError() { 
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public void setError(Error.ErrorType error) {
        this.error = new Error(error);
    }

    public void setError(String errorStr) {
        this.error = new Error(errorStr);
    }

    public Time getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(int hour, int minute) {
        this.timeStamp = new Time(hour, minute);
    }

    public void setTimeStamp(LocalTime localTime) {
        this.timeStamp = new Time(localTime);
    }

    public void setTimeStamp(Time time) {
        this.timeStamp = time;
    }
}
