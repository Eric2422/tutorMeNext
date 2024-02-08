public class Demeanor {
    public enum DemeanorType {
        POLITE,
        RUDE,
        UNDEFINED;

        /**
         * Attempts to convert a String to an DemeanorType
         * If it it does not match any DemeanorType, default to UNDEFINED
         * 
         * @param demeanorStr a String that contains an DemeanorType
         */
        private static DemeanorType toDemeanorType(String demeanorStr) {
            // convert the String to the matching DemeanorType and store it
            DemeanorType demeanor = DemeanorType.valueOf(demeanorStr.trim().toUpperCase());

            // Can not be set to undefined
            if (demeanor == DemeanorType.UNDEFINED) {
                throw new IllegalArgumentException("\"" + demeanorStr + "\" is not a valid demeanor.");
            }

            return demeanor;
        }
    }

    private DemeanorType demeanor = DemeanorType.UNDEFINED; 

    public Demeanor() {
    }

    public Demeanor(DemeanorType demeanorType) {
        this.demeanor = demeanorType;
    }

    public Demeanor(Demeanor demeanor) {
        this.demeanor = demeanor.demeanor;
    }

    /**
     * Take in a String and save the corresponding DemeanorType
     * Defaults to UNDEFINED
     * 
     * @param demeanorStr a String that holds the demeanor
     */
    public Demeanor(String demeanorStr) {
        setDemeanor(demeanorStr);
    }

    public DemeanorType getDemeanor() {
        return demeanor;
    }

    public void setDemeanor(DemeanorType demeanorType) {
        this.demeanor = demeanorType;
    }

    public void setDemeanor(Demeanor demeanor) {
        this.demeanor = demeanor.demeanor;
    }

    public void setDemeanor(String demeanorStr) {
        this.demeanor = DemeanorType.toDemeanorType(demeanorStr);
    }

    @Override
    public String toString() {
        return demeanor.toString();
    }
}