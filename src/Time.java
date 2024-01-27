public class Time {
    private int hour;
    private int minute;

    /**
     * @param startHour   int of the hour that time starts at
     * @param startMinute int of the minute that time starts at
     */
    public Time(int startHour, int startMinute) {
        hour = startHour;
        minute = startMinute;
    }

    /**
     * Adjusts for number of minutes beyond 60
     */
    private convertMinutesToHours() {
        hour += minute / 60;
        minute % 
    }
}