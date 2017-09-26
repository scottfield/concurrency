package com.jackie.concurrency;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by jackie on 9/26/2017.
 */
public class PrimeGenerator extends Thread {
    private BlockingQueue<BigInteger> queue;

    public PrimeGenerator(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        try {
            while (!isInterrupted()) {
                queue.put(p = p.nextProbablePrime());
            }
        } catch (InterruptedException consumed) {
        }
    }

    public void cancel() {
        interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<BigInteger> queue = new LinkedBlockingDeque<>(3);
        PrimeGenerator generator = new PrimeGenerator(queue);
        generator.start();
        for (int i = 0; i < 10; i++) {
            System.out.println(queue.take());
        }
        Thread.currentThread().sleep(3000);
        generator.cancel();
    }
}
