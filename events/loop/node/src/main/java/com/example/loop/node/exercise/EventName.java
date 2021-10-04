package com.example.loop.node.exercise;

import org.springframework.stereotype.Component;

@Component
public class EventName implements EventHandler {
    @Override
    public String eventSignature() {
        return "name";
    }

    @Override
    public String handleEvent(String[] eventParams) {
        return "Ricardo - " + eventParams[0];
    }
}
