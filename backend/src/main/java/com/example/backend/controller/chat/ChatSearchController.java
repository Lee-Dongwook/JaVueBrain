package com.example.backend.controller.chat;

import com.example.backend.domain.chat.ChatMessageDocument;
import com.example.backend.domain.chat.ChatMessageSearchRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/search")
public class ChatSearchController {
    private final ChatMessageSearchRepository repo;

    public ChatSearchController(ChatMessageSearchRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<ChatMessageDocument> serach(@RequestParam String keyword, @RequestParam(required = false) String roomId) {
        return (roomId == null) ? repo.findByContentContaining(keyword)
                : repo.findByRoomIdAndContentContaining(roomId, keyword);
    }
    
}
