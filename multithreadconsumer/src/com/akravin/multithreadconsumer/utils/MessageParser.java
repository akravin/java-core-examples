package com.akravin.multithreadconsumer.utils;

public class MessageParser {
    private static final String SEPARATOR = "\\|";

    private MessageParser() {}

    public static Message parse(String message) {
        String[] parts = message.split(SEPARATOR);
        if (parts.length != 3)
            throw new IllegalArgumentException("Message format is incorrect. Message: " + message);
        return new Message(parts[0], Long.parseLong(parts[1]), parts[2]);
    }
}
