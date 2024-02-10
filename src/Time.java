import java.time.*;
import java.time.format.*;
import java.time.temporal.ChronoUnit;

public class Time implements Comparable<Time> {
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("H':'m");

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

    public Time(LocalTime localTime) {
        currentTime = localTime;
    }

    public Time(Time time) {
        this.currentTime = time.currentTime;
    }

    public LocalTime getCurrentTime() {
        return currentTime;
    }

    /**
     * Increments the time by 1 minute
     */
    public void incrementTime() {
        setCurrentTime(currentTime.plusMinutes(1));
    }

    public void incrementTime(int minutes) {
        setCurrentTime(currentTime.plusMinutes(minutes));
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
        try {;
            return LocalTime.parse(timeStr, timeFormat);

        } catch (DateTimeParseException e) { // if the given String is not a valid time
            return null;
        }
    }

    @Override
    public String toString() {
        return currentTime.format(timeFormat);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Time) {
            Time time = (Time) obj;
            return currentTime == time.currentTime;
        }

        return false;
    }

    @Override
    public int compareTo(Time time) {
        // until() returns the amount of time from this LocalTime to another LocalTime,
        // so the value returned by until() has to be negated to return thisTime - otherTime
        return -(int) (this.currentTime.until(time.currentTime, ChronoUnit.MINUTES));
    }
}