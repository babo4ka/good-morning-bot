package goodMorningBot.bot.selfWriter;

import goodMorningBot.interval.Interval;
import goodMorningBot.midnightLog.MidnightLogWriter;
import org.javacord.api.entity.channel.TextChannel;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.*;

public class ChannelsHolderAndWriter {

    private static ChannelsHolderAndWriter instance;

    private ChannelsHolderAndWriter(){}

    public static ChannelsHolderAndWriter getInstance(){
        if(instance == null){
            instance = new ChannelsHolderAndWriter();
        }

        return instance;
    }

    private List<TextChannel> channels = new ArrayList<>();

    public void addChannel(TextChannel channel){
        if(channels.contains(channel))return;
        channels.add(channel);
    }

    private final TimerTask writeGMTask = new TimerTask() {
        @Override
        public void run() {
            channels.forEach(channel -> channel.sendMessage("Доброе утро!!"));
        }
    };
    @Scheduled(cron = "0 1 0 * * *", zone = "GMT+3")
    public void writeGoodMo() throws IOException {
        Timer timer = new Timer();

        Date date = new Date();
        Interval interval = Interval.getInstance();
        date.setHours(interval.getHoursStart());
        date.setMinutes(interval.getMinutesStart());
        timer.schedule(writeGMTask, date);

        MidnightLogWriter mnlw = new MidnightLogWriter();
        StringBuilder data = new StringBuilder()
                .append("Set new timer to write gm for ")
                .append(new Date())
                .append("\n==========================================\n");

        mnlw.writeLog(date.toString());
    }
}
