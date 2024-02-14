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
            DemeanorType demeanorType = DemeanorType.valueOf(demeanorStr.trim().toUpperCase());

            // Can not be set to undefined
            if (demeanorType == DemeanorType.UNDEFINED) {
                throw new IllegalArgumentException("\"" + demeanorStr + "\" is not a valid demeanor.");
            }

            return demeanorType;
        }
    }

    private DemeanorType demeanorType = DemeanorType.UNDEFINED; 

    public Demeanor() {
    }

    public Demeanor(DemeanorType demeanorType) {
        this.demeanorType = demeanorType;
    }

    public Demeanor(Demeanor demeanor) {
        this.demeanorType = demeanor.demeanorType;
    }

    /**
     * Take in a String and save the corresponding DemeanorType
     * Defaults to UNDEFINED
     * 
     * @param demeanorStr a String that holds the demeanor
     */
    public Demeanor(String demeanorStr) {
        this.demeanorType = DemeanorType.toDemeanorType(demeanorStr);
    }

    public DemeanorType getDemeanorType() {
        return demeanorType;
    }

    public void setDemeanorType(DemeanorType demeanorType) {
        this.demeanorType = demeanorType;
    }

    public void setDemeanorType(Demeanor demeanor) {
        this.demeanorType = demeanor.demeanorType;
    }

    public void setDemeanorType(String demeanorStr) {
        this.demeanorType = DemeanorType.toDemeanorType(demeanorStr);
    }

    @Override
    public String toString() {
        return demeanorType.toString();
    }
}