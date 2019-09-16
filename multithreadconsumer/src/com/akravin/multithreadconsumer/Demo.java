package com.akravin.multithreadconsumer;
import com.akravin.multithreadconsumer.consumer.Consumer;
import com.akravin.multithreadconsumer.producer.Producer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Demo {

    private static final int PRODUCERS_COUNT = 1;
    private static final int CONSUMERS_COUNT = 5;

    private static final String FILE_NAME = "resources/messages.txt";

    public static void main(String[] args) throws Exception {
        new Demo().start();
    }

    private void start() throws InterruptedException {
        AtomicInteger threadCount = new AtomicInteger(0);

        ExecutorService producerExecutorService = Executors.newFixedThreadPool(PRODUCERS_COUNT,
                runnable -> new Thread(runnable, "producer-thread"));
        ExecutorService consumerExecutorService = Executors.newFixedThreadPool(CONSUMERS_COUNT,
                runnable -> new Thread(runnable, "consumer-thread-" + threadCount.incrementAndGet()));

        Producer producer = new Producer(FILE_NAME);

        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(CONSUMERS_COUNT);

        IntStream.range(0, CONSUMERS_COUNT).forEach(i ->
                producer.addConsumer(new Consumer(startSignal, doneSignal)));

        // Execute
        producerExecutorService.execute(producer);
        producer.getConsumers().forEach(consumerExecutorService::execute);

        startSignal.countDown();
        doneSignal.await();

        consumerExecutorService.shutdown();
        producerExecutorService.shutdown();
    }
}
