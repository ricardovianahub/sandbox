package com.example.loop.node.exercise;

import org.springframework.stereotype.Component;

@Component
public class EventCarOffer implements EventHandler {
    @Override
    public String eventSignature() {
        return "caroffer";
    }

    @Override
    public String handleEvent(String[] eventParams) {
        return "Car - best offer: " + eventParams[0];
    }
}
