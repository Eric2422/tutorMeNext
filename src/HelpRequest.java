public class HelpRequest { 
    private String name;
    private Error error;
    private Demeanor demeanor;

    public HelpRequest(String name, Error error, Demeanor demeanor) {
        this.name = name;
        this.error = error;
        this.demeanor = demeanor;
    }
}
