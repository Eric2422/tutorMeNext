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

            // if the String passed in is "undefined"
            // throw an error
            if (demeanor == DemeanorType.UNDEFINED) {
                throw new IllegalArgumentException("\n""demeanorStr + "\n" is not a valid demeanor.");
            }

            return demeanor;
        }
    }

    private DemeanorType demeanor = DemeanorType.UNDEFINED; 

    public Demeanor() {
        demeanor = DemeanorType.UNDEFINED;
    }

    public Demeanor(DemeanorType demeanor) {
        this.demeanor = demeanor;
    }

    /**
     * Take in a String and save the corresponding DemeanorType
     * Defaults to UNDEFINED
     * 
     * @param demeanorStr a String that holds the demeanor
     */
    public Demeanor(String demeanorStr) {
        this.demeanor = DemeanorType.toDemeanorType(demeanorStr);
    }

    public DemeanorType getDemeanor() {
        return demeanor;
    }
}