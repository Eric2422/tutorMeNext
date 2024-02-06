import java.time.LocalTime;

public class Time {
    private LocalTime currentTime;

    /**
     * @param startHour   int of the hour that time starts at
     * @param startMinute int of the minute that time starts at
     */
    public Time(int startHour, int startMinute) {
        currentTime = LocalTime.of(startHour, startMinute);
    }

    public LocalTime getCurrentTime() {
        return currentTime;
    }

    /**
     * Checks to see if the String can be converted to a time by using regex
     * 
     * @param 
     */
    public static boolean isValidTimeStr(String timeStr) {
        return timeStamp.match("\\d?[0-4]:\\d\\d");
    }

    @Override
    public String toString() {
        return currentTime.toString();
    }
}