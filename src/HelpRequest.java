public class HelpRequest { 
    private Student student;
    private Error error;

    // the time that the request was made
    private Time timeStamp;

    public HelpRequest(Student student, Error error, Time timeStamp) {
        this.student = student;
        this.error = error;
        this.timeStamp = timeStamp;
    }

    public Student getStudent() {
        return student;
    }

    public Error getError() { 
        return error;
    }

    public Time getTimeStamp() {
        return timeStamp;
    
}
