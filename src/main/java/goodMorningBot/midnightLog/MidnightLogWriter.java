package goodMorningBot.midnightLog;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MidnightLogWriter {

    public void writeLog(String data) throws IOException {
        File logFile = new File("src/main/java/goodMorningBot/midnightLog/mn_log.txt");

        if(!logFile.exists()){
            logFile.createNewFile();
        }

        FileWriter logWriter = new FileWriter(logFile, true);

        logWriter.write(data);
        logWriter.flush();
    }
}
