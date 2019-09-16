package com.akravin.multithreadconsumer.utils;

import java.util.Objects;

public class Message {

    private final String id;
    private final long duration;
    private final String payload;

    public Message(String id, long duration, String payload) {
        this.id = id;
        this.duration = duration;
        this.payload = payload;
    }

    public String getId() {
        return id;
    }

    public long getDuration() {
        return duration;
    }

    public String getPayload() {
        return payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return duration == message.duration &&
                Objects.equals(id, message.id) &&
                Objects.equals(payload, message.payload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, duration, payload);
    }

    @Override
    public String toString() {
        return "id:"+ id+", duration:"+duration+", payload:"+payload;
    }
}
