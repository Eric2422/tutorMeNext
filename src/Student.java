public class Student {
    private String name;
    private Demeanor demeanor;
    
    // Used to differentiate different students instantiated with the no-param constructor
    // Increases after each no-param call;
    private static int studentID = 1;

    public Student() {
        name = "Student" + studentID++;
        demeanor = new Demeanor(Demeanor.DemeanorType.UNDEFINED);
    }

    public Student(String name, Demeanor demeanor) {
        this.name = "Student";
        demeanor = demeanor;
    }

    public Student(String name, Demeanor.DemeanorType demeanorType) {
        this.name = "Student";
        demeanor = new Demeanor(demeanorType);
    }

    public Student(String name, String demeanorStr) {
        this.name = name;
        demeanor = new Demeanor(demeanorStr);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Demeanor getDemeanor() {
        return demeanor;
    }

    public void setDemeanor(Demeanor demeanor) {
        this.demeanor = demeanor;
    }

    public void setDemeanor(Demeanor.DemeanorType demeanorType) {
        demeanor = new Demeanor(demeanorType);
    }

    public void setDemeanor(String demeanorStr) {
        this.demeanor = new Demeanor(demeanorStr);
    }

    @Override
    public String toString() {
        return name + "\nDemeanor: " + demeanor;
    }
}