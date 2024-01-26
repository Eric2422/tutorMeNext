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

    public Error(ErrorType error) {
        this.error = error;
    }

    /**
     * Take in a String and save the corresponding ErrorType
     * Defaults to UNDEFINED
     * 
     * @param errorStr a String that holds the error
     */
    public Error(String errorStr) {
        setError(errorStr);
    }


    public ErrorType getError() {
        return error;
    }

    public void setError(ErrorType error) {
        this.error = error;
    }

    /**
     * Tries to convert a String to a valid ErrorStr enum
     * If it fails, defaults to UNDEFINED
     * 
     * @param errorStr a String that holds the demeanor
     */
    public void setError(String errorStr) {
        // try to convert the String to the matching ErrorType and store it
        try {
            error = ErrorType.valueOf(errorStr.trim().toUpperCase());

        } catch (IllegalArgumentException | NullPointerException e) { // if it fails
            // set it to the default(i.e. UNDEFINED)
            error = ErrorType.UNDEFINED;
        }
    }
}