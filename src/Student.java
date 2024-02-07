public class Student {
    private String name;
    private Demeanor demeanor;
    
    // Used to differentiate different students instantiated with the no-param constructor
    // Increases after each no-param call;
    private static int studentID = 1;

    public Student() {
        setName("Student" + studentID++);
    }

    public Student(String name, Demeanor demeanor) {
        setName("Student");
        setDemeanor(demeanor);
    }

    public Student(String name, String demeanorStr) {
        this.name = name;
        setDemeanor(demeanorStr);
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
        this.demeanor = demeanor
    }

    public void setDemeanor(String demeanorStr) {
        this.demeanor = new Demeanor(demeanorStr);
    }
}