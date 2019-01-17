package com.jackie.concurrency.concurrency.practice;

import java.util.ArrayList;
import java.util.List;

public class EventSource {
    private List<EventListener> listenerList = new ArrayList<>();

    public void registerListener(EventListener listener) {
        listenerList.add(listener);
    }

    public void publishEvent(Event event) {
        for (EventListener listener : listenerList) {
            listener.onEvent(event);
        }
    }
}
