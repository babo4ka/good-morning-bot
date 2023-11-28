package goodMorningBot.bot.selfWriter;

import goodMorningBot.interval.Interval;
import org.javacord.api.entity.channel.TextChannel;
import org.springframework.scheduling.annotation.Scheduled;

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

    @Scheduled(cron = "0 1 0 * * *", zone = "GMT+3")
    public void writeGoodMo(){
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for (TextChannel ch :channels){
                    ch.sendMessage("Доброе утро!!");
                }
            }
        };

        Date date = new Date();
        Interval interval = Interval.getInstance();
        date.setHours(interval.getHoursStart());
        date.setMinutes(interval.getMinutesStart());
        timer.schedule(task, date);
    }
}
