package com.kafka.first;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

	 private String receivedMessage;

    @KafkaListener(topics = "second", groupId = "my-consumer-group")
    public void listen(String message) {
        System.out.println("First Received message: " + message);
        this.receivedMessage = message;
    }
    
    public String getReceivedMessage() {
        return receivedMessage;
    }

}