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
        String id = restTemplate.getForObject(String.format("%s/sendRequestEvent/calc/%d!x!%d", targetURL, first, second), String.class);
        Thread.sleep(1200);
        String result = restTemplate.getForObject(String.format("%s/popResponseEvent/%s", targetURL, id), String.class);
        System.out.printf("calc : %d x %d = %s%n", first, second, result);
    }

}
