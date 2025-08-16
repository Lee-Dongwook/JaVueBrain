package com.example.backend.domain.chat;

import lombok.Data;

@Data
public class ChatMessagePayload {
    private String roomId;
    private String sender;
    private String content;
}
