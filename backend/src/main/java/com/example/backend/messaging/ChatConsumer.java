package com.example.backend.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ChatConsumer {
    @KafkaListener(topics = "chat-messages", groupId = "chat-group")
    public void consume(String message) {
        System.out.println("Received: " + message);
    }
}
