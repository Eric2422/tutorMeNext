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

    public HelpRequest(Student student, Error error, Time time) {
        this.student = student;
        this.error = error;
        this.timeStamp = time;
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

    public void setName(String name) {
        student.setName(name);
    }

    public void setDemeanor(Demeanor demeanor) {
        student.setDemeanor(demeanor);
    }

    public void setDemeanor(String demeanorStr) {
        student.setDemeanor(demeanorStr);
    }

    public Error getError() { 
        return error;
    }

    public int getMinutesWithoutHelp() {
        return error.getMinutesWithoutHelp();
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

    public void setError(String errorStr) {
        this.error = new Error(errorStr);
    }

    /**
     * Sets minutesUntilFixed to minutesWithHelp
     */
    public void getsHelp() {
        error.setMinutesUntilFixed(error.getMinutesWithHelp());
    }

    /**
     * Returns whether the student's error is fixed
     * 
     * @return a boolean representing whether the timeUntilFinished value of error is 0
     */
    public boolean errorFixed() {
        return error.getMinutesUntilFixed() == 0;
    }

    public Time getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(int hour, int minute) {
        timeStamp = new Time(hour, minute);
    }

    public void setTimeStamp(LocalTime localTime) {
        timeStamp = new Time(localTime);
    }

    public void setTimeStamp(Time time) {
        timeStamp = time;
    }

    public void setTimeStamp(String timeStr) {
        timeStamp = new Time(timeStr);
    }

    @Override
    public String toString() {
        String str = "Request:  \"" + student.getName() + "\", @ " + timeStamp
		           + "\n\tDemeanor:  " + student.getDemeanor()
		           + "\n\tError:  " + error
		           + "\n\tMinutes with help:  " + error.getMinutesWithHelp()
		           + "\n\tMinutes without help:  " + error.getMinutesWithoutHelp()
                   + "\n\tMinutes until fixed:  " + error.getMinutesUntilFixed();

		return str;
    }
}
