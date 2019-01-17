package com.jackie.concurrency.concurrency_practice;

import java.util.ArrayList;
import java.util.List;

public class ThisEscape {
    private final List<Event> listOfEvents;

    public ThisEscape(EventSource source) {
        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Event e) {
                doSomething();
                //this list will not get intialized when other thread call this listener
                listOfEvents.add(e);
            }
        });
        try {
            System.out.println("do some time consuming intitialization work");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        listOfEvents = new ArrayList<>();
    }

    void doSomething() {
        System.out.println("do something");
    }

    public static void main(String[] args) {
        EventSource source = new EventSource();
        Thread t = new Thread("worker thread") {
            @Override
            public void run() {
                while (true) {
                    source.publishEvent(new Event());
                    try {
                        System.out.println("sleep for a while");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        };
        t.start();
        new ThisEscape(source);


    }
}
