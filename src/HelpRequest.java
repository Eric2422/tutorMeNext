public class HelpRequest { 
    private Student student;
    private Error error;

    // the time that the request was made
    private Time timeStamp;

    public HelpRequest(Student student, Error error, Time timeStamp) {
        this.student = student;
        this.error = error;
    }
}
