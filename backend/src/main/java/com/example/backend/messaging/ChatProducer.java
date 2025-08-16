package com.example.backend.messaging;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String roomId, String message) {
        String payload = String.format("{\"roomId\":\"%s\", \"message\":\"%s\"}", roomId, message);
        kafkaTemplate.send("chat-messages", roomId, payload);
        System.out.println("sent: " + payload);
    }
}
