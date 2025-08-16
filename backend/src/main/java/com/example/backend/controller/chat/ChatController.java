package com.example.backend.controller.chat;

import com.example.backend.service.ChatMessageService;
import com.example.backend.messaging.ChatProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatService;
    private final ChatProducer chatProducer;

    @MessageMapping("/chat.send")
    public void send(ChatMessagePayload msg) {
        // 1) DB & Elasticsearch 저장
        chatService.saveToDbAndIndex(msg.getRoomId(), msg.getSender(), msg.getContent());
       
        // 2) Kafka 발행
        chatProducer.sendMessage(msg.getRoomId(), msg.getContent());

        // 3) WebSocket 구독자에게 메시지 브로드캐스트
        messagingTemplate.convertAndSend("/topic/rooms/" + msg.getRoomId(), msg);
    }
}
