package com.example.loop.node.exercise;

import org.springframework.stereotype.Component;

@Component
public class EventHandlerCalc implements EventHandler {
    @Override
    public String eventSignature() {
        return "calc";
    } // new type : "name"

    @Override
    public String handleEvent(String[] params) {
        int first = Integer.parseInt(params[0]);
        int second = Integer.parseInt(params[2]);
        return String.format("%d x %d = %d", first, second, first * second);
    }

    @Override
    public NextEvent nextEvent() {
        return new NextEvent() {
            @Override
            public String eventSignature() {
                return "name";
            }

            @Override
            public String[] eventParameters(String[] params) {
                return new String[] {"additional"};
            }
        };
    }
}
