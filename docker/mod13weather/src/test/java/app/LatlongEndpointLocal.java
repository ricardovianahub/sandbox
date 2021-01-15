package app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
public class LatlongEndpointLocal {
    @Bean
    public LatlongEndpoint point() {
        return new LatlongEndpoint() {
            @Override
            public boolean isLocal() {
                return true;
            }

            @Override
            public String url() {
                return "http://localhost:8113/points";
            }
        };
    }
}
