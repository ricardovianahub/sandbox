package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class LatlongApplication {

    @Autowired
    LatlongEndpoint latlongEndpoint;

    public static void main(String[] args) {
        SpringApplication.run(LatlongApplication.class, args);
    }

    @GetMapping(value = "/cityState")
    public String cityState() {
        RestTemplate restTemplate = new RestTemplate();
        CityState2 cityState2 = restTemplate.getForObject(latlongEndpoint.url(), CityState2.class);
        return "City: " + cityState2.getProperties().getRelativeLocation().getProperties().getCity()
                + " - "
                + cityState2.getProperties().getRelativeLocation().getProperties().getState();
    }
}
