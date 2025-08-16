package com.example.backend.domain.chat;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;
import java.time.Instant;

@Document(indexName = "chat-messages")
@Setting(settingPath = "/elasticsearch/chat-message-settings.json")
@Mapping(mappingPath = "/elasticsearch/chat-message-mappings.json")
public class ChatMessageDocument {
    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String roomId;

    @Field(type = FieldType.Text, analyzer = "nori_analyzer", searchAnalyzer = "nori_analyzer")
    private String sender;
    
    @Field(type = FieldType.Text, analyzer = "nori_analyzer", searchAnalyzer = "nori_analyzer")
    private String content;
    
    @Field(type = FieldType.Date)
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
