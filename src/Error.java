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
            ErrorType errorType = ErrorType.valueOf(errorStr.trim().toUpperCase());

            // Can not be set to UNDEFINED
            if (errorType == ErrorType.UNDEFINED) {
                throw new IllegalArgumentException("\"" + errorStr + "\" is not a valid error.");
            }

            return errorType;
        }
    }

    private ErrorType errorType = ErrorType.UNDEFINED;

    // how many minutes it takes for the student to fix the error
    // if they receive help from the teacher
    private int minutesWithHelp;

    // how many minutes it takes for the student to fix the error
    // if they do not receive help from the teacher
    private int minutesWithoutHelp;

    // how many minutes left until the error is resolved
    private int minutesUntilFixed;

    public Error() {
        this(ErrorType.UNDEFINED, 1, 1);
    }

    public Error(ErrorType errorType) {
        this(errorType, 1, 1);
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

    public Error(ErrorType errorType, int minutesWithHelp, int minutesWithoutHelp) {
        this.errorType = errorType;
        this.minutesWithHelp = minutesWithHelp;
        this.minutesWithoutHelp = minutesWithoutHelp;

        minutesUntilFixed = minutesWithoutHelp;
    }

    /**
     * Take in a String and convert it to the corresponding ErrorType
     * Defaults to UNDEFINED
     * 
     * @param errorStr                a String that holds the error
     * @param minutesWithHelp    how long it takes for the student to fix the error 
     *                                if they receive help from the teacher
     * @param minutesWithoutHelp how long it takes for the student to fix the error 
     *                                if they do not receive help from the teacher
     */
    public Error(String errorStr, int minutesWithHelp, int minutesWithoutHelp) {
        this(ErrorType.toErrorType(errorStr), minutesWithHelp, minutesWithoutHelp);
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public void setErrorType(String errorStr) {
        errorType = ErrorType.toErrorType(errorStr);
    }

    public int getMinutesWithHelp() {
        return minutesWithHelp;
    }

    public void setMinutesWithHelp(int minutes) {
        minutesWithHelp = minutes;
    }

    public int getMinutesWithoutHelp() {
        return minutesWithoutHelp;
    }

    public void setMinutesWithoutHelp(int minutes) {
        minutesWithoutHelp = minutes;
    }

    public int getMinutesUntilFixed() {
        return minutesUntilFixed;
    }

    public void setMinutesUntilFixed(int minutesUntilFixed) {
        this.minutesUntilFixed = minutesUntilFixed;
    }

    public void decrementMinutesUntilFixed() {
        minutesUntilFixed--;
    }

    public void decrementMinutesUntilFixed(int minutes) {
        minutesUntilFixed -= minutes;
    }

    @Override
    public String toString() {
        return errorType.toString();
    }
}