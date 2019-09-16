package com.akravin.multithreadconsumer.selector;

import com.akravin.multithreadconsumer.consumer.Consumer;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class RoundRobinConsumerSelector {

    private final PriorityQueue<Consumer> queue = new PriorityQueue<>(
            Comparator.comparingInt(Consumer::getUseCount));

    public void addConsumer(Consumer consumer) {
        queue.add(consumer);
    }

    public Consumer getConsumer() {
        Consumer consumer = queue.poll();
        if (consumer != null) {
            consumer.increaseUseCount();
            queue.add(consumer);
        }
        return consumer;
    }

    public List<Consumer> getConsumers() {
        return List.copyOf(queue);
    }
}
