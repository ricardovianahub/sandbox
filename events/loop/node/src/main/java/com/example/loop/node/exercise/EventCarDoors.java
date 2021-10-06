package com.example.loop.node.exercise;

import org.springframework.stereotype.Component;

@Component
public class EventCarDoors implements EventHandler {
    @Override
    public String eventSignature() {
        return "cardoors";
    }

    @Override
    public NextEvent nextEvent() {
        return new NextEvent() {
            @Override
            public String eventSignature() {
                return "carcomplete";
            }

            @Override
            public String[] eventParameters(String[] params) {
                return new String[] {params[0] + " - 4 doors"};
            }
        };
    }
}
