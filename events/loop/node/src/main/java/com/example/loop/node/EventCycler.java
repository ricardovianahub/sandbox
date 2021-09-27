package com.example.loop.node;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.loop.node.exercise.EventHandler;

@Component
public class EventCycler {

    @Value("${targetURL}")
    private String targetURL;

    private EventHandler eventHandler;

    public EventCycler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Scheduled(fixedRate = 500)
    public void checkForEvents() {
        System.out.print("handleEvent: ");
        RestTemplate restTemplate = new RestTemplate();
        String payload = restTemplate.getForObject(String.format(targetURL + "/popNextRequestEvent/" + eventHandler.eventSignature()), String.class);
        if ("[empty]".equals(payload)) {
            System.out.println("*** PAYLOAD ***> " + payload);
            return;
        }
        System.out.print(payload);
        String[] request = payload.split(",");
        String originalEventId = request[0];
        String eventContent = request[1];
        String[] eventParams = eventContent.split("!");

        String result = eventHandler.handleEvent(eventParams);
        System.out.println(" - result: " + result);
        restTemplate.getForObject(
                String.format(targetURL + "/sendResponseEvent/%s/%s", originalEventId, result), String.class
        );

        EventHandler.NextEvent nextEvent = eventHandler.nextEvent();
        if (nextEvent.eventSignature() != null) {
            String[] params = nextEvent.eventParameters(eventParams);
            restTemplate.getForObject(String.format(
                    "%s/sendRequestEvent/%s/%s!%s!%s", targetURL, nextEvent.eventSignature(), params[0], params[1], params[2]
            ), String.class);
        }

    }

}