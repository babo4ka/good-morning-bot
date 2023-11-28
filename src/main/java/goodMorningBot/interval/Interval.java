package goodMorningBot.interval;

import java.util.Date;

public final class Interval {
    private int hoursStart;
    private int minutesStart;

    private int hoursEnd;
    private int minutesEnd;

    public boolean isInInterval(Date date){
        int hours = date.getHours();
        int minutes = date.getMinutes();

        return hours>=hoursStart && minutes>=minutesStart
                &&hours<=hoursEnd && minutes<=minutesEnd;
    }

    public void intervalsToday(){
        System.out.println(hoursStart + ":" + minutesStart);
        System.out.println(hoursEnd + ":" + minutesEnd);
    }

    public int getHoursStart() {
        return hoursStart;
    }

    public Interval setHoursStart(int hoursStart) {
        this.hoursStart = hoursStart;
        return this;
    }

    public int getMinutesStart() {
        return minutesStart;
    }

    public Interval setMinutesStart(int minutesStart) {
        this.minutesStart = minutesStart;
        return this;
    }

    public int getHoursEnd() {
        return hoursEnd;
    }

    public Interval setHoursEnd(int hoursEnd) {
        this.hoursEnd = hoursEnd;
        return this;
    }

    public int getMinutesEnd() {
        return minutesEnd;
    }

    public Interval setMinutesEnd(int minutesEnd) {
        this.minutesEnd = minutesEnd;
        return this;
    }
}
