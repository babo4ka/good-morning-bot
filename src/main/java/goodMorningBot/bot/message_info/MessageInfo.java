package goodMorningBot.bot.message_info;

import goodMorningBot.interval.Interval;
import goodMorningBot.midnightLog.MidnightLogWriter;
import org.javacord.api.entity.message.Message;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MessageInfo {
    public static final int EARLIER = 1;
    public static final int IN_TIME = 2;
    public static final int LATER = 3;
    public static final int ALREADY_WRITED = 4;

    private Map<Long, Date> usersWrited = new HashMap<>();

    @Scheduled(cron = "0 0 0 * * *", zone = "GMT+3")
    public void resetUsersWrited() throws IOException {
        usersWrited.clear();

        MidnightLogWriter mnlw = new MidnightLogWriter();
        StringBuilder data = new StringBuilder()
                .append("Reseted users writed for ")
                .append(new Date())
                .append("\n==========================================\n");
        mnlw.writeLog(data.toString());
    }

    private static MessageInfo instance;

    public static MessageInfo getInstance(){
        if(instance == null){
            instance = new MessageInfo();
        }
        return instance;
    }
    private MessageInfo(){}

    public int checkUser(Message message) throws IOException {
        if(usersWrited.get(message.getAuthor().getId()) != null)
            return ALREADY_WRITED;

        Interval interval = Interval.getInstance();
        Date now = new Date();

        usersWrited.put(message.getAuthor().getId(), now);

        if(interval.isInInterval(now)) {
            writeLog(message.getAuthor().getName(), message.getChannel().getIdAsString(), now, " вовремя");
            return IN_TIME;
        }
        else if(interval.isEarlier(now)) {
            writeLog(message.getAuthor().getName(), message.getChannel().getIdAsString(), now, " раньше");
            return EARLIER;
        }
        else {
            writeLog(message.getAuthor().getName(), message.getChannel().getIdAsString(), now, "позже");
            return LATER;
        }
    }

    private void writeLog(String userName, String channelID, Date date, String status) throws IOException {
        File logFile = new File("src/main/java/goodMorningBot/bot/message_info/msg_log.txt");

        if(!logFile.exists()){
            logFile.createNewFile();
        }

        StringBuilder out = new StringBuilder()
                .append(userName)
                .append("\n")
                .append("В канале: " + channelID)
                .append("\n")
                .append("Дата: ")
                .append(date.getDate() + ".")
                .append((date.getMonth()+1) + ".")
                .append(date.getYear()+1900)
                .append(" Время: ")
                .append(date.getHours()<10?("0"+date.getHours()):date.getHours() + ":")
                .append(date.getMinutes()<10?("0"+date.getMinutes()):date.getMinutes())
                .append("\n")
                .append(status)
                .append("\n==========================================\n");

        FileWriter logWriter = new FileWriter(logFile, true);

        logWriter.write(out.toString());
        logWriter.flush();
    }
}
