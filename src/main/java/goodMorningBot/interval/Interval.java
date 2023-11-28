package goodMorningBot.interval;

import lombok.Getter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

@Getter
public final class Interval {
    private int hoursStart;
    private int minutesStart;

    private int hoursEnd;
    private int minutesEnd;

    private static Interval instance;

    public static Interval getInstance(){
        if(instance == null){
            instance = new Interval();
        }

        return instance;
    }

    private Interval(){}

    public boolean isInInterval(Date date){
        int hours = date.getHours();
        int minutes = date.getMinutes();

        return hours>=hoursStart && minutes>=minutesStart
                &&hours<=hoursEnd && minutes<=minutesEnd;
    }

    public boolean isEarlier(Date date){
        int hours = date.getHours();
        int minutes = date.getMinutes();

        if(hours<hoursStart)return true;
        else if (hours == hoursStart && minutes < minutesStart)return true;
        return false;
    }

    public boolean isLater(Date date){
        int hours = date.getHours();
        int minutes = date.getMinutes();

        if(hours>hoursEnd)return true;
        else if (hours == hoursEnd && minutes > minutesEnd)return true;
        return false;

    }

    public void intervalsToday() throws IOException {
        Date today = new Date();
        StringBuilder out = new StringBuilder()
                .append("Дата: ")
                .append(today.getDate())
                .append(".")
                .append(today.getMonth() + 1)
                .append(".")
                .append(today.getYear() + 1900)
                .append("\n")
                .append("Начало интервала: ")
                .append(hoursStart < 10 ? ("0" + hoursStart) : hoursStart)
                .append(":")
                .append(minutesStart < 10 ? ("0" + minutesStart) : minutesStart)
                .append("\n")
                .append("Конец интервала: ")
                .append(hoursEnd < 10 ? ("0" + hoursEnd) : hoursEnd)
                .append(":")
                .append(minutesEnd<10?("0"+minutesEnd):minutesEnd)
                .append("\n\n");

        File logFile = new File("src/main/java/goodMorningBot/interval/intervals_log.txt");

        if(!logFile.exists()){
            logFile.createNewFile();
        }

        FileWriter logWriter = new FileWriter(logFile, true);

        logWriter.write(out.toString());
        logWriter.flush();
    }

    public Interval setHoursStart(int hoursStart) {
        this.hoursStart = hoursStart;
        return this;
    }

    public Interval setMinutesStart(int minutesStart) {
        this.minutesStart = minutesStart;
        return this;
    }

    public Interval setHoursEnd(int hoursEnd) {
        this.hoursEnd = hoursEnd;
        return this;
    }

    public Interval setMinutesEnd(int minutesEnd) {
        this.minutesEnd = minutesEnd;
        return this;
    }
}
