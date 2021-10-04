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
        RestTemplate restTemplate = new RestTemplate();

        // requirement for new event: eventSignature: abbreviation // parameters (first name, last name): John, Doe  // response: JD

        sendRequestEventCalc(restTemplate);
        sendRequestEventName(restTemplate);
        sendRequestEventAbbreviation(restTemplate);

        Thread.sleep(500);

        popAllEvents(restTemplate, "calc");
        popAllEvents(restTemplate, "name");
        popAllEvents(restTemplate, "something");
        popAllEvents(restTemplate, "abbreviation");
        System.out.println();
    }

    private void sendRequestEventAbbreviation(RestTemplate restTemplate) {
        String requestEvent;
        requestEvent = String.format("%s/bus/sendRequestEvent/abbreviation/John!Doe", targetURL);
        restTemplate.getForObject(requestEvent, String.class);
    }

    private void sendRequestEventName(RestTemplate restTemplate) {
        String requestEvent;
        requestEvent = String.format("%s/bus/sendRequestEvent/name/initial", targetURL);
        restTemplate.getForObject(requestEvent, String.class);
    }

    private void sendRequestEventCalc(RestTemplate restTemplate) {
        String requestEvent;
        int first = random.nextInt(10) + 1;
        int second = random.nextInt(10) + 1;
        requestEvent = String.format("%s/bus/sendRequestEvent/calc/%d!x!%d", targetURL, first, second);
        restTemplate.getForObject(requestEvent, String.class);
    }

    private void popAllEvents(RestTemplate restTemplate, String eventSignature) {
        boolean firstRound;
        String result;
        firstRound = true;
        do {
            result = popEvent(restTemplate, eventSignature);
            if (!"[empty]".equals(result) || firstRound) {
                System.out.printf("[" + DATE_FORMAT + "] %2$s : %3$s%n", new Date(), eventSignature, result);
            }
            firstRound = false;
        } while (!"[empty]".equals(result));
    }

    private String popEvent(RestTemplate restTemplate, String eventSignature) {
        String responseEvent = String.format("%s/bus/popNextResponseEvent/%s", targetURL, eventSignature);
        return restTemplate.getForObject(responseEvent, String.class);
    }

}
