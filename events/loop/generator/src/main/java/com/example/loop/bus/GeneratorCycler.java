package com.example.loop.bus;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GeneratorCycler {

    @Value("${targetURL}")
    private String targetURL;

    Random random = new Random();

    @Scheduled(fixedRate = 2000)
    private void generateEvents() throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        int first = random.nextInt(10) + 1;
        int second = random.nextInt(10) + 1;
        String requestEvent = String.format("%s/bus/sendRequestEvent/calc/%d!x!%d", targetURL, first, second);
        restTemplate.getForObject(requestEvent, String.class);
        Thread.sleep(1200);
        popEvent("calc", restTemplate, first, second);
    }

    private void popEvent(String eventSignature, RestTemplate restTemplate, int first, int second) {
        String responseEvent = String.format("%s/bus/popNextResponseEvent/%s", targetURL,eventSignature);
        String result = restTemplate.getForObject(responseEvent, String.class);
        System.out.printf("%s : %d x %d = %s%n", eventSignature, first, second, result);
    }

}
