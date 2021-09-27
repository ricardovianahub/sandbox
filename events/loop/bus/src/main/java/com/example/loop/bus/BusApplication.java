package com.example.loop.bus;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BusApplication {

    private Map<String, Map<Integer, String>> eventRequests = new HashMap<>();
    private Map<Integer, String> eventResponses = new HashMap<>();

    private static int autoKey = 1;

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

    @GetMapping(value = "/sendRequestEvent/{eventType}/{eventPayload}")
    String sendRequestEvent(@PathVariable String eventType, @PathVariable String eventPayload) {
        if (this.eventRequests.get(eventType) == null) {
            this.eventRequests.put(eventType, new LinkedHashMap<>());
        }
        int key = nextKey();
        this.eventRequests.get(eventType).put(key, eventPayload);
        return "" + key;
    }

    @GetMapping(value = "/popNextRequestEvent/{eventType}")
    String popNextRequestEvent(@PathVariable String eventType) {
        synchronized (this) {
            Map<Integer, String> eventMap = this.eventRequests.get(eventType);
            if (eventMap == null || eventMap.isEmpty()) {
                return "[empty]";
            }
            LinkedList<Integer> ll = new LinkedList<>(eventMap.keySet());
            int id = ll.getLast();
            String result = "" + id + "," + eventMap.get(id);
            eventMap.clear();
            return result;
        }
    }

    @GetMapping(value = "/sendResponseEvent/{eventId}/{result}")
    private String sendResponseEvent(@PathVariable Integer eventId, @PathVariable String result) {
        this.eventResponses.put(eventId, result);
        return "" + eventId + "," + result;
    }

    @GetMapping(value = "/popResponseEvent/{eventId}")
    private String popResponseEvent(@PathVariable Integer eventId) {
        if (this.eventResponses.get(eventId) == null) {
            return "[empty]";
        } else {
            String currentResponse = this.eventResponses.get(eventId);
            this.eventResponses.remove(eventId);
            return currentResponse;
        }
    }

}
