public class Error {
    public enum ErrorType {
        COMPILING,
        LINKING,
        RUNTIME,
        UNDEFINED;
    }

    private ErrorType error;

    public Error() {
        error = ErrorType.UNDEFINED;
    }

    public void setDemeanor(ErrorType error) {
        this.error = error;
    }

    /**
     * Tries to convert a String to a valid ErrorType enum
     * If it fails, defaults to UNDEFINED
     * 
     * @param errorStr a String that holds the error
     */
    public void setDemeanor(String errorStr) {
        // try to convert the String to the matching DemeanorType and store it
        try {
            error = ErrorType.valueOf(errorStr.trim().toUpperCase());

        } catch (IllegalArgumentException | NullPointerException e) { // if it fails
            // set it to the default(i.e. UNDEFINED)
            error = ErrorType.UNDEFINED;
        }
    }
}