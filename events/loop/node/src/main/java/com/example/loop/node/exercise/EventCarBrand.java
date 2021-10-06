package com.example.loop.node.exercise;

import org.springframework.stereotype.Component;

@Component
public class EventCarBrand implements EventHandler {
    @Override
    public String eventSignature() {
        return "carbrand";
    }

    @Override
    public NextEvent nextEvent() {
        return new NextEvent() {
            @Override
            public String eventSignature() {
                return "cardoors";
            }

            @Override
            public String[] eventParameters(String[] params) {
                return new String[]{String.format("Camry (%s)", params[0])};
            }
        };
    }
}
