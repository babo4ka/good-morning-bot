package goodMorningBot.interval;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
@PropertySource("app.properties")
public class SocketConfig {

    @Value("${host}")
    private String host;

    @Value("${port}")
    private int port;
}
