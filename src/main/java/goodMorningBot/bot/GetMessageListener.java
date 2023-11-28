package goodMorningBot.bot;

import goodMorningBot.bot.message_info.MessageInfo;
import goodMorningBot.bot.selfWriter.ChannelsHolderAndWriter;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.io.IOException;

public class GetMessageListener implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        if(!messageCreateEvent.getMessage().getAuthor().isBotUser()){

            MessageInfo mi = MessageInfo.getInstance();
            ChannelsHolderAndWriter chaw = ChannelsHolderAndWriter.getInstance();
            chaw.addChannel(messageCreateEvent.getChannel());
            try {
                int status = mi.checkUser(messageCreateEvent.getMessage());
                TextChannel tc = messageCreateEvent.getMessage().getChannel();
                switch (status){
                    case MessageInfo.IN_TIME ->
                            tc.sendMessage("<@"+messageCreateEvent.getMessage().getAuthor().getId() + ">, ты вовремя!");

                    case MessageInfo.EARLIER ->
                            tc.sendMessage("<@"+messageCreateEvent.getMessage().getAuthor().getId() + ">, ты рано!");

                    case MessageInfo.LATER ->
                            tc.sendMessage("<@"+messageCreateEvent.getMessage().getAuthor().getId() + ">, ты поздно!");
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
