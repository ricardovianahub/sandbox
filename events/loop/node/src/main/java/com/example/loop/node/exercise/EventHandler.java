package com.example.loop.node.exercise;

public interface EventHandler {
    String eventSignature();
    String handleEvent(String[] eventParams);
    default NextEvent nextEvent() {
        return new NextEvent() {};
    };

    interface NextEvent {
        default String eventSignature() {
            return null;
        }
        default String[] eventParameters(String[] params) {
            return new String[0];
        }
    }
}
