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
        setDemeanor(demeanorStr);
    } 

    public DemeanorType getDemeanor() {
        return demeanor;
    }

    public void setDemeanor(DemeanorType demeanor) {
        this.demeanor = demeanor;
    }

    /**
     * Tries to convert the String to a valid DemeanorType enum
     * If it fails, defaults to undefined
     * 
     * @param demeanorStr a String that holds the demeanor
     */
    public void setDemeanor(String demeanorStr) {
        // try to convert the String to the matching DemeanorType and store it
        try {
            demeanor = DemeanorType.valueOf(demeanorStr.trim().toUpperCase());

        } catch (IllegalArgumentException | NullPointerException e) { // if it fails
            // set it to the default(i.e. UNDEFINED)
            demeanor = DemeanorType.UNDEFINED;
        }
    }
}