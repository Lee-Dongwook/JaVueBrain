package com.example.backend.controller.chat;

import com.example.backend.service.ChatSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;


@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class ChatSearchController {
    private final ChatSearchService service;
    
    @GetMapping
    public Page<ChatSearchService.SearchResult> search(
        @RequestParam(required = false) String keyword,
        @RequestParam(required = false) String roomId,
        @RequestParam(required = false) String from,    
        @RequestParam(required = false) String to,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size,
        @RequestParam(defaultValue = "recent") String sort
    ) {
        Instant fromTs = (from != null && !from.isBlank()) ? Instant.parse(from) : null;
        Instant toTs = (to != null && !to.isBlank()) ? Instant.parse(to) : null;
        return service.search(keyword, roomId, fromTs, toTs, page, size, sort);
    }
    
}
