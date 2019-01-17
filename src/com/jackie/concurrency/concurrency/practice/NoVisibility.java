package com.jackie.concurrency.concurrency.practice;

public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        public void run() {
            while (!ready)
                Thread.yield();
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        number = 42;
        new ReaderThread().start();
        ready = true;
    }
}
