package com.example.backend.web.chat;

import com.example.backend.web.chat.dto.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    
    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.send")
    public void send(ChatMessage msg) {
        String destination = "/topic/rooms/" + msg.getRoomId();
        messagingTemplate.convertAndSend(destination, msg);
    }
}
