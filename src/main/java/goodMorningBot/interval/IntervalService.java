package goodMorningBot.interval;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.Socket;

@Component
public class IntervalService {

    public static final Interval interval = new Interval();
    private static SocketConfig config;
    @Autowired
    private void init(SocketConfig config) throws IOException {
        IntervalService.config = config;
        responseToData();
    }

    @Scheduled(cron = "0 0 0 * * *", zone = "GMT+3")
    public void responseToData() throws IOException {
        String response = requestInterval();
        Gson g = new Gson();
        ResponseData data = g.fromJson(response, ResponseData.class);
        System.out.println(response);
        interval
                .setHoursStart((int)Math.floor(data.getStart()))
                .setMinutesStart((int)((data.getStart()-Math.floor(data.getStart()))*60))
                .setHoursEnd((int)Math.floor(data.getEnd()))
                .setMinutesEnd((int)((data.getEnd()-Math.floor(data.getEnd()))*60));

        interval.intervalsToday();
    }

    private String requestInterval() throws IOException {
        Socket client = new Socket(config.getHost(), config.getPort());

        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

        out.write("{0:0}");
        out.flush();

        String response = in.readLine();
        in.close();

        return response;
    }
}
