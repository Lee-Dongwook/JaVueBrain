package com.example.backend.service;

import com.example.backend.domain.chat.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatMessageService {
    private final ChatMessageRepository jpaRepo;
    private final ChatMessageSearchRepository esRepo;

    public ChatMessageService(ChatMessageRepository jpaRepo, ChatMessageSearchRepository esRepo) {
        this.jpaRepo = jpaRepo;
        this.esRepo = esRepo;
    }

    @Transactional
    public ChatMessage saveToDbAndIndex(String roomId, String sender, String content) {
        ChatMessage m = new ChatMessage();
        m.setRoomId(roomId);
        m.setSender(sender);
        m.setContent(content);
        ChatMessage saved = jpaRepo.save(m);

        ChatMessageDocument doc = new ChatMessageDocument(
            String.valueOf(saved.getId()),
            saved.getRoomId(),
            saved.getSender(),
            saved.getContent(),
            saved.getCreatedAt().toInstant()
        );
        esRepo.save(doc);
        return saved;
    }
}
