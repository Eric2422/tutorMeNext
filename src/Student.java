public class Student {
    private String name;
    private Demeanor demeanor;

    public Student(String name, Demeanor demeanor) {
        this.name = name;
        this.demeanor = demeanor;
    }

    public Student(String name, String demeanorStr) {
        this.name = name;
        this.demeanor = new Demeanor(demeanorStr);
    }

    public String getName() {
        return name;
    }

    public Demeanor getDemeanor() {
        return demeanor;
    }
}