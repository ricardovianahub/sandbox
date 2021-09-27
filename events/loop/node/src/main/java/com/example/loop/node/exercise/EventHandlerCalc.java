package com.example.loop.node.exercise;

import org.springframework.stereotype.Component;

@Component
public class EventHandlerCalc implements EventHandler {
    @Override
    public String eventSignature() {
        return "calc";
    }


    @Override
    public String handleEvent(String[] params) {
        int first = Integer.parseInt(params[0]);
        int second = Integer.parseInt(params[2]);
        return String.valueOf(first * second);
    }

}
