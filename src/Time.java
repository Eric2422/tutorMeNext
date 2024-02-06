import java.time.*;

public class Time implements Comparable {
    private LocalTime currentTime;

    public Time() {
        this(0, 0);
    }

    public Time(int hour, int minute) {
        currentTime = LocalTime.of(hour, minute);
    }

    /**
     * Attmepts to set the time based on a given String
     * If it fails, default to 0:00
     */
    public Time(String timeStr) {
        LocalTime time = parse(timeStr);
        currentTime = (time != null) ? time : LocalTime.of(0, 0);
    }

    public Time(Time time) {
        this.currentTime = time.currentTime;
    }

    public LocalTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(int hour, int minute) {
        currentTime = LocalTime.of(hour, minute);
    }

    public void setCurrentTime(LocalTime time) {
        currentTime = time;
    }

    public void setCurrentTime(Time time) {
        currentTime = time.currentTime;
    }

    /**
     * Convert a String into a Time based on a 24-hour format.
     * Hours and minutes should be separated by a colon(:).
     * Does not accept seconds.
     * Returns null if it is not possible to convert the String
     * 
     * @param  timeStr a String that contains a time in 24-hour format
     * @return a LocalTime object representing the given time
     */
    public static LocalTime parse(String timeStr) {
        try {
            java.time.format.DateTimeFormatter timeFormat = java.time.format.DateTimeFormatter.ofPattern("H':'m");
            return LocalTime.parse(timeStr, timeFormat);


        } catch (java.time.format.DateTimeParseException e) { // if the given String is not a valid time
            return null;
        }
    }

    @Override
    public String toString() {
        return currentTime.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Time) {
            Time time = (Time) obj;
            return currentTime == time.currentTime;
        }

        return false;
    }

    @Overridew
}