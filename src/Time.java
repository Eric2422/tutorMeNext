public class Time {
    private short hour;
    private short minute;

    /**
     * @param startHour   int of the hour that time starts at
     * @param startMinute int of the minute that time starts at
     */
    public Time(int startHour, int startMinute) {
        hour = startHour;
        minute = startMinute;
    }
}