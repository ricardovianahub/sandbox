package com.example.loop.node.exercise;

import org.springframework.stereotype.Component;

@Component
public class EventSomething implements EventHandler{
    @Override
    public String eventSignature() {
        return "something";
    }

    @Override
    public String handleEvent(String[] eventParams) {
        return "Ricardo - " + eventParams[0];
    }
}
