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

    @Override
    public String toString() {
        return currentTime.toString();
    }    
}