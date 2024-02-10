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
    private int fixTimeWithHelp;

    // how many minutes it takes for the student to fix the error
    // if they do not receive help from the teacher
    private int fixTimeWithoutHelp;

    // how many minutes left until the error is resolved
    private int timeUntilFixed;

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

    public Error(ErrorType error, int fixTimeWithHelp, int fixTimeWithoutHelp) {
        this.error = error;
        this.fixTimeWithHelp = fixTimeWithHelp;
        this.fixTimeWithoutHelp = fixTimeWithoutHelp;

        timeUntilFixed = fixTimeWithoutHelp;
    }

    /**
     * Take in a String and convert it to the corresponding ErrorType
     * Defaults to UNDEFINED
     * 
     * @param errorStr                a String that holds the error
     * @param fixTimeWithHelp    how long it takes for the student to fix the error 
     *                                if they receive help from the teacher
     * @param fixTimeWithoutHelp how long it takes for the student to fix the error 
     *                                if they do not receive help from the teacher
     */
    public Error(String errorStr, int fixTimeWithHelp, int fixTimeWithoutHelp) {
        this(ErrorType.toErrorType(errorStr), fixTimeWithHelp, fixTimeWithoutHelp);
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

    public int getFixTimeWithHelp() {
        return fixTimeWithHelp;
    }

    public void setFixTimeWithHelp(int minutes) {
        fixTimeWithHelp = minutes;
    }

    public int getFixTimeWithoutHelp() {
        return fixTimeWithoutHelp;
    }

    public void setFixTimeWithoutHelp(int minutes) {
        fixTimeWithoutHelp = minutes;
    }

    public int getTimeUntilFixed() {
        return timeUntilFixed;
    }

    public void decrementTimeUntilFixed() {
        timeUntilFixed--;
    }

    public void decrementTimeUntilFixed(int minutes) {
        timeUntilFixed -= minutes;
    }

    @Override
    public String toString() {
        return error.toString();
    }
}