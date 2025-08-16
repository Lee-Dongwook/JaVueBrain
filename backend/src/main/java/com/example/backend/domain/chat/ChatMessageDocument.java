package com.example.backend.domain.chat;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import java.time.Instant;

@Document(indexName = "chat-messages")
public class ChatMessageDocument {
    @Id
    private String id;
    private String roomId;
    private String sender;
    private String content;
    private Instant createdAt;

    public ChatMessageDocument() {}

    public ChatMessageDocument(String id, String roomId, String sender, String content, Instant createdAt) {
        this.id = id;
        this.roomId = roomId;
        this.sender = sender;
        this.content = content;
        this.createdAt = createdAt;
    }
    
    public String getId() { return id; }
    public String getRoomId() { return roomId; }
    public String getSender() { return sender; }
    public String getContent() { return content; }
    public Instant getCreatedAt() { return createdAt; }
}
