package com.example.loop.bus;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GeneratorCycler {

    private static String DATE_FORMAT = "%1$tH:%1$tM:%1$tS";

    @Value("${targetURL}")
    private String targetURL;

    Random random = new Random();

    @Scheduled(fixedRate = 2000)
    private void generateEvents() throws InterruptedException {
        String requestEvent;

        RestTemplate restTemplate = new RestTemplate();
        int first = random.nextInt(10) + 1;
        int second = random.nextInt(10) + 1;
        requestEvent = String.format("%s/bus/sendRequestEvent/calc/%d!x!%d", targetURL, first, second);
        restTemplate.getForObject(requestEvent, String.class);

        requestEvent = String.format("%s/bus/sendRequestEvent/name/initial", targetURL);
        restTemplate.getForObject(requestEvent, String.class);

        Thread.sleep(500);

        String result;
        boolean firstRound;

        firstRound = true;
        do {
            result = popEvent(restTemplate, "calc");
            if (!"[empty]".equals(result) || firstRound) {
                System.out.printf("[" + DATE_FORMAT + "] calc : %2$s%n", new Date(), result);
            }
            firstRound = false;
        } while (!"[empty]".equals(result));

        firstRound = true;
        do {
            result = popEvent(restTemplate, "name");
            if (!"[empty]".equals(result) || firstRound) {
                System.out.printf("[" + DATE_FORMAT + "] name : %2$s%n", new Date(), result);
            }
            firstRound = false;
        } while (!"[empty]".equals(result));
    }

    private String popEvent(RestTemplate restTemplate, String eventSignature) {
        String responseEvent = String.format("%s/bus/popNextResponseEvent/%s", targetURL, eventSignature);
        String result = restTemplate.getForObject(responseEvent, String.class);
        return result;
    }

}
