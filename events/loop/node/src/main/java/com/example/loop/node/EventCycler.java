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

    private EventHandler[] eventHandlers;

    private RestTemplate restTemplate;

    public EventCycler(EventHandler[] eventHandlers) {
        this.eventHandlers = eventHandlers;
        this.restTemplate = new RestTemplate();
    }

    @Scheduled(fixedRate = 100)
    public void checkForEvents() {
        for (EventHandler eventHandler : this.eventHandlers) {
            String payload = restTemplate.getForObject(String.format(targetURL + "/bus/popNextRequestEvent/" + eventHandler.eventSignature()), String.class);
            if ("[empty]".equals(payload)) {
                continue;
            }
            String[] request = payload.split(",");
            String originalEventId = request[0];
            String eventContent = request[1];
            String[] eventParams = eventContent.split("!");

            System.out.print(eventHandler.eventSignature() + " - ");

            String result = eventHandler.handleEvent(eventParams);
            if (result != null) {
                String responseEvent = String.format(targetURL + "/bus/sendResponseEvent/%s/%s/%s", eventHandler.eventSignature(), originalEventId, result);
                restTemplate.getForObject(responseEvent, String.class);
                System.out.print(result + " - ");
            }

            EventHandler.NextEvent nextEvent = eventHandler.nextEvent();
            if (nextEvent.eventSignature() != null) {
                String[] params = nextEvent.eventParameters(eventParams);
                restTemplate.getForObject(String.format(
                        "%s/bus/sendRequestEvent/%s/%s", targetURL, nextEvent.eventSignature(), params[0]
                ), String.class);
                System.out.print(" - NEXT: " + nextEvent.eventSignature() + " - " + params[0]);
            }
            System.out.println();
        }

    }

}
