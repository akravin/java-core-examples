package com.akravin.multithreadconsumer.consumer;

import com.akravin.multithreadconsumer.utils.AbstractLatchWorker;
import com.akravin.multithreadconsumer.utils.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class Consumer extends AbstractLatchWorker {

    private int useCount = 0;
    private final BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
    private volatile boolean stop = false;
    private static AtomicLong messageCounter = new AtomicLong(0);

    public Consumer(CountDownLatch startSignal, CountDownLatch doneSignal) {
        super(startSignal, doneSignal);
    }

    @Override
    protected void doWork() {
        while (!stop || queue.peek() != null) {
            try {
                Message message = queue.poll(1, TimeUnit.SECONDS);
                if (message != null) {
                    processMessage(message);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void putMessage(Message message) {
        try {
            queue.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        stop = true;
    }

    public int getUseCount() {
        return useCount;
    }

    public void increaseUseCount() {
        useCount++;
    }

    private void processMessage(Message message) {
        try {
            System.out.println(Thread.currentThread().getName() + "\t Message(number:"+messageCounter.incrementAndGet()+
                    ","+message+")");
            Thread.sleep(message.getDuration());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
