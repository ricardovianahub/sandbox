package app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("live")
public class LatlongEndpointLive {
    @Bean
    public LatlongEndpoint point() {
        return new LatlongEndpoint() {
            @Override
            public boolean isLocal() {
                return false;
            }

            @Override
            public String url() {
                return "https://api.weather.gov/points/33.1032,-96.6706";
            }
        };
    }
}
