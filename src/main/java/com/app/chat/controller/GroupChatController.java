package com.app.chat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class GroupChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final RedisTemplate redisTemplate;

    public GroupChatController(SimpMessagingTemplate messagingTemplate, RedisTemplate redisTemplate) {
        this.messagingTemplate = messagingTemplate;
        this.redisTemplate = redisTemplate;
    }

    @MessageMapping("/sendMessage")
    public void sendMessage(ChatMessage chatMessage) {
        log.info("message recieved in server: " + chatMessage);
        messagingTemplate.convertAndSend("/topic/" + chatMessage.getRoomId(), chatMessage);
//        redisTemplate.convertAndSend("/topic/" + chatMessage.getRoomId(), chatMessage);
    }
}
