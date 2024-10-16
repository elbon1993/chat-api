package com.app.chat.service;

import com.app.chat.controller.ChatMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatMessageService {
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void saveChatMessage(ChatMessage chatMessage) {
        try {
            String key = "chat:group:" + chatMessage.getRoomId();
            String jsonMessage = objectMapper.writeValueAsString(chatMessage);
            redisTemplate.opsForList().rightPush(key, jsonMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public List<ChatMessage> getChatMessages(String groupId) {
        String key = "chat:group:" + groupId;
        List<String> jsonMessages = redisTemplate.opsForList().range(key, 0, -1);
        List<ChatMessage> messages = new ArrayList<>();
        if (jsonMessages != null) {
            for (String jsonMessage : jsonMessages) {
                try {
                    ChatMessage message = objectMapper.readValue(jsonMessage, ChatMessage.class);
                    messages.add(message);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
        return messages;
    }
}
