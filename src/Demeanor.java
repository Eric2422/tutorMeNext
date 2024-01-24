public class Demeanor {
    public enum DemeanorType {
        POLITE,
        RUDE,
        UNDEFINED;
    }

    // holds the demeanor
    private DemeanorType demeanor;    

    public Demeanor() {
        demeanor = DemeanorType.UNDEFINED;
    }

    /**
     * Converts a String holding demeanor to a DemeanorType
     * 
     * @param demeanorStr a String that holds the demeanor
     */
    public Demeanor(String demeanorStr) {
        // try to convert the String to the matching DemeanorType and store it
        try {
            demeanor = DemeanorType.valueOf(demeanorStr.trim().toUpperCase());

        } catch (IllegalArgumentException | NullPointerException e) { // if it fails
            // set it to the default(i.e. UNDEFINED)
            demeanor = DemeanorType.UNDEFINED;
        }
    } 

    public DemeanorType getDemeanor() {
        return demeanor;
    }

    public void  setDemeanor()
}