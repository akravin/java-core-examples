package com.akravin.multithreadconsumer.consumer;

import com.akravin.multithreadconsumer.utils.AbstractLatchWorker;
import com.akravin.multithreadconsumer.utils.Message;
import com.akravin.multithreadconsumer.utils.StopMessage;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

public class Consumer extends AbstractLatchWorker {

    private final BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
    private static AtomicLong messageCounter = new AtomicLong(0);

    public Consumer(CountDownLatch startSignal, CountDownLatch doneSignal) {
        super(startSignal, doneSignal);
    }

    @Override
    protected void doWork() {
        while (true) {
            try {
                Message message = queue.take();
                if (message instanceof StopMessage) {
                    System.out.println("-------------------------------------------");
                    System.out.println("EXIT: " + Thread.currentThread().getName());
                    System.out.println("-------------------------------------------");
                    break;
                }
                processMessage(message);
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
        putMessage(new StopMessage());
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
