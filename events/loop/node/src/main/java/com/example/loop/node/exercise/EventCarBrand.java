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
                return "caroffer";
            }

            @Override
            public String[] eventParameters(String[] params) {
                return new String[]{
                        params[0] + (params[0].equals("Prius") ? " (Toyota)" : " (VW)")
                };
            }
        };
    }
}
