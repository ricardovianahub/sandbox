package com.example.loop.bus;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/bus")
public class BusApplication {

    private final Map<String, Map<Integer, String>> eventRequests = new HashMap<>();
    private final Map<String, Map<Integer, String>> eventResponses = new HashMap<>();

    private static int autoKey = 0;

    synchronized static int nextKey() {
        return ++autoKey;
    }

    public static void main(String[] args) {
        SpringApplication.run(BusApplication.class, args);
    }

    @GetMapping(value = "/heartbeat")
    private String heartBeat() {
        return "alive";
    }

    @GetMapping(value = "/sendRequestEvent/{eventSignature}/{eventPayload}")
    String sendRequestEvent(@PathVariable String eventSignature, @PathVariable String eventPayload) {
        this.eventRequests.computeIfAbsent(eventSignature, k -> new LinkedHashMap<>());
        int key = nextKey();
        this.eventRequests.get(eventSignature).put(key, eventPayload);
        System.out.printf("SEND REQUEST Event[%d] = %s - %s%n", key, eventSignature, eventPayload);
        return "" + key;
    }

    @GetMapping(value = "/popNextRequestEvent/{eventSignature}")
    String popNextRequestEvent(@PathVariable String eventSignature) {
        synchronized (this) {
            System.out.printf("POP Next REQUEST Event ");
            Map<Integer, String> eventMap = this.eventRequests.get(eventSignature);
            if (eventMap == null || eventMap.isEmpty()) {
                System.out.println("[empty]%n");
                return "[empty]";
            }
            LinkedList<Integer> ll = new LinkedList<>(eventMap.keySet());
            int id = ll.getLast();
            System.out.printf("[%d] - %s %s%n", id, eventSignature, eventMap.get(id));
            String result = "" + id + "," + eventMap.get(id);
            eventMap.remove(id);
            return result;
        }
    }

    @GetMapping(value = "/sendResponseEvent/{eventSignature}/{eventId}/{response}")
    private String sendResponseEvent(
            @PathVariable String eventSignature,
            @PathVariable Integer eventId,
            @PathVariable String response
    ) {
        Map<Integer, String> eventMap =
                this.eventResponses.computeIfAbsent(eventSignature, k -> new LinkedHashMap<>());

        eventMap.put(eventId, response);
        String result = "" + eventId + "," + response;
        System.out.printf("SEND RESPONSE Event[%d] = %s%n", eventId, response);
        return result;
    }

    @GetMapping(value = "/popNextResponseEvent/{eventSignature}")
    private String popNextResponseEvent(@PathVariable String eventSignature) {
        synchronized (this) {
            System.out.printf("POP next RESPONSE Event [%s] - ", eventSignature);
            Map<Integer, String> eventMap =
                    this.eventResponses.computeIfAbsent(eventSignature, k -> new LinkedHashMap<>());

            if (eventMap.isEmpty()) {
                System.out.printf("[empty]%n");
                return "[empty]";
            }

            LinkedList<Integer> ll = new LinkedList<>(eventMap.keySet());
            int id = ll.getLast();
            System.out.printf("[%d] - %s %s%n", id, eventSignature, eventMap.get(id));
            String result = String.valueOf(eventMap.get(id));
            eventMap.remove(id);
            return result;
        }
    }

}
