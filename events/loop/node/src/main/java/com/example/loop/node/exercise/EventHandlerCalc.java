package com.example.loop.node.exercise;

import org.springframework.stereotype.Component;

@Component
public class EventHandlerCalc implements EventHandler {
    @Override
    public String handleEvent(String[] eventParams) {
        if (eventParams[1].equals("x")) {
            return "" + (Integer.parseInt(eventParams[0]) * Integer.parseInt(eventParams[2]));
        } else {
            return "[ERROR]";
        }
    }
}
