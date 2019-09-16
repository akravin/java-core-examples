package com.akravin.multithreadconsumer.producer;

import com.akravin.multithreadconsumer.consumer.Consumer;
import com.akravin.multithreadconsumer.selector.RoundRobinConsumerSelector;
import com.akravin.multithreadconsumer.utils.Message;
import com.akravin.multithreadconsumer.utils.MessageParser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Producer implements Runnable {

    private final String fileName;
    private final Map<String, Consumer> routes = new HashMap<>();
    private final RoundRobinConsumerSelector consumerSelector = new RoundRobinConsumerSelector();

    public Producer(String fileName) {
        this.fileName = fileName;
    }

    public void addConsumer(Consumer consumer) {
        consumerSelector.addConsumer(consumer);
    }

    @Override
    public void run() {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(processLine);

            // Stop consumers
            consumerSelector.getConsumers().forEach(Consumer::stop);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private java.util.function.Consumer<String> processLine = line -> {
        Message message = MessageParser.parse(line);
        Consumer consumer = routes.computeIfAbsent(message.getId(), k -> consumerSelector.getConsumer());
        consumer.putMessage(message);
    };

    public List<Consumer> getConsumers() {
        return consumerSelector.getConsumers();
    }
}
