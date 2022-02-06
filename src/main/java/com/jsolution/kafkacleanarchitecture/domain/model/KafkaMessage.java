package com.jsolution.kafkacleanarchitecture.domain.model;

public class KafkaMessage {

    private String message;
    private String topic;

    public KafkaMessage() {
    }

    public KafkaMessage(String message, String topic) {
        this.message = message;
        this.topic = topic;
    }

    public KafkaMessage(String topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "Message{ message = " + message + " " +", topic = " + topic + "}";
    }
}
