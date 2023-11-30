package goodMorningBot.interval;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.Socket;
import java.util.Date;

@Component
public class IntervalService {

    private Interval interval;
    private static SocketConfig config;
    @Autowired
    private void init(SocketConfig config) throws IOException {
        System.out.println("init");
        IntervalService.config = config;
        responseToData();
    }

    private int tries = 0;
    @Scheduled(cron = "0 0 0 * * *", zone = "GMT+3")
    public void responseToData() throws IOException {
        String response = requestInterval();
        Gson g = new Gson();
        ResponseData data = g.fromJson(response, ResponseData.class);

        interval = Interval.getInstance();

        interval
                .setHoursStart((int)Math.floor(data.getStart()))
                .setMinutesStart((int)((data.getStart()-Math.floor(data.getStart()))*60))
                .setHoursEnd((int)Math.floor(data.getEnd()))
                .setMinutesEnd((int)((data.getEnd()-Math.floor(data.getEnd()))*60));

        interval.intervalsToday();

        StringBuilder out = new StringBuilder()
                .append("Set new intervals for ")
                .append(new Date())
                .append("\n==========================================\n");

        System.out.println(out);
    }

    private String requestInterval() throws IOException {
        Socket client = new Socket(config.getHost(), config.getPort());

        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

        out.write("{0:0}");
        out.flush();

        String response = in.readLine();
        in.close();
        client.close();
        return response;
    }
}
