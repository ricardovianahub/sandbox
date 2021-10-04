package com.example.loop.node.exercise;

import org.springframework.stereotype.Component;

@Component
public class EventAbbreviation implements EventHandler{
    @Override
    public String eventSignature() {
        return "abbreviation";
    }

    @Override
    public String handleEvent(String[] eventParams) {
        return "" + eventParams[0].charAt(0) + eventParams[1].charAt(0);
    }
}
