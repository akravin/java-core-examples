package com.akravin.multithreadconsumer.selector;

import com.akravin.multithreadconsumer.consumer.Consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobinConsumerSelector {

    private final ArrayList<Consumer> consumers = new ArrayList<>();
    private final AtomicInteger index = new AtomicInteger();

    public void addConsumer(Consumer consumer) {
        consumers.add(consumer);
    }

    public Consumer getConsumer() {
        return consumers.get(index.getAndIncrement() % consumers.size());
    }

    public List<Consumer> getConsumers() {
        return List.copyOf(consumers);
    }
}
