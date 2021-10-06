package com.example.loop.node.exercise;

import org.springframework.stereotype.Component;

@Component
public class EventCarComplete implements EventHandler {
    @Override
    public String eventSignature() {
        return "carcomplete";
    }

    @Override
    public String handleEvent(String[] eventParams) {
        return "Car - best offer: " + eventParams[0];
    }
}
