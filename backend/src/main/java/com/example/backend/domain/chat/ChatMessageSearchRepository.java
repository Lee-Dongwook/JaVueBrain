package com.example.backend.domain.chat;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ChatMessageSearchRepository extends ElasticsearchRepository<ChatMessageDocument, String> {
    List<ChatMessageDocument> findByContentContaining(String keyword);
    List<ChatMessageDocument> findByRoomIdAndContentContaining(String roomId, String keyword);    
}
