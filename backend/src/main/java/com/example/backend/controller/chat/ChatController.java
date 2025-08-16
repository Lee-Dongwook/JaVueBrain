package com.example.backend.controller.chat;

import com.example.backend.service.ChatMessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/chat")
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatService;

    public ChatController(SimpMessagingTemplate messagingTemplate, ChatMessageService chatService) {
        this.messagingTemplate = messagingTemplate;
        this.chatService = chatService;

    }

    @MessageMapping("/chat.send")
    public void send(ChatMessagePayload msg) {
        chatService.saveToDbAndIndex(msg.getRoomId(), msg.getSender(), msg.getContent());
        messagingTemplate.convertAndSend("/topic/rooms/" + msg.getRoomId(), msg);
    }
}
