public class Error {
    public enum ErrorType {
        COMPILING,
        LINKING,
        RUNTIME,
        UNDEFINED;

        /**
         * Attempts to convert a String to an ErrorType
         * If it it does not match any ErrorType, default to UNDEFINED
         * 
         * @param errorStr a String that contains an ErrorType
         */
        private static ErrorType toErrorType(String errorStr) {
            // convert the String to the matching ErrorType and store it
            ErrorType error = ErrorType.valueOf(errorStr.trim().toUpperCase());

            // Can not be set to UNDEFINED
            if (error == ErrorType.UNDEFINED) {
                throw new IllegalArgumentException("\"" + errorStr + "\" is not a valid error.");
            }

            return error;
        }
    }

    private ErrorType error = ErrorType.UNDEFINED;

    // how many minutes it takes for the student to fix the error
    // if they receive help from the teacher
    private int minutesToFixWithHelp;

    // how many minutes it takes for the student to fix the error
    // if they do not receive help from the teacher
    private int minutesToFixWithoutHelp;

    public Error() {
        this(ErrorType.UNDEFINED, 1, 1);
    }

    public Error(ErrorType error) {
        this(error, 1, 1);
    }

    /**
     * Take in a String and convert it to the corresponding ErrorType
     * Defaults to UNDEFINED
     * 
     * @param errorStr a String that holds the error
     */
    public Error(String errorStr) {
        this(ErrorType.toErrorType(errorStr), 1, 1);
    }

    public Error(ErrorType error, int minutesToFixWithHelp, int minutesToFixWithoutHelp) {
        this.error = error;
        this.minutesToFixWithHelp = minutesToFixWithHelp;
        this.minutesToFixWithoutHelp = minutesToFixWithoutHelp;
    }

    /**
     * Take in a String and convert it to the corresponding ErrorType
     * Defaults to UNDEFINED
     * 
     * @param errorStr                a String that holds the error
     * @param minutesToFixWithHelp    how long it takes for the student to fix the error 
     *                                if they receive help from the teacher
     * @param minutesToFixWithoutHelp how long it takes for the student to fix the error 
     *                                if they do not receive help from the teacher
     */
    public Error(String errorStr, int minutesToFixWithHelp, int minutesToFixWithoutHelp) {
        this(ErrorType.toErrorType(errorStr), minutesToFixWithHelp, minutesToFixWithoutHelp);
    }

    public ErrorType getError() {
        return error;
    }

    public void setError(ErrorType errorType) {
        this.error = errorType;
    }

    public void setError(String errorStr) {
        this.error = ErrorType.toErrorType(errorStr);
    }

    public int getMinutesToFixWithHelp() {
        return minutesToFixWithHelp;
    }

    public void setMinutesToFixWithHelp(int minutes) {
        minutesToFixWithHelp = minutes;
    }

    public int getMinutesToFixWithoutHelp() {
        return minutesToFixWithoutHelp;
    }

    public void setMinutesToFixWithoutHelp(int minutes) {
        minutesToFixWithoutHelp = minutes;
    }

    @Override
    public String toString() {
        return error.toString();
    }
}