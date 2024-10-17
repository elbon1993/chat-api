package com.app.chat.service;

import com.app.chat.controller.ChatMessage;
import com.app.chat.model.MongoChatMessage;
import com.app.chat.repository.MongoChatRepository;
import com.app.chat.util.CommonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatMessageService {

    private final RedisTemplate<String, String> redisTemplate;

    private final MongoChatRepository mongoChatRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public ChatMessageService(MongoChatRepository mongoChatRepository, RedisTemplate<String, String> redisTemplate) {
        this.mongoChatRepository = mongoChatRepository;
        this.redisTemplate = redisTemplate;
    }

    public void saveChatMessage(ChatMessage chatMessage) {
        saveToRedis(chatMessage);
        saveToMongo(chatMessage);
    }

    private void saveToMongo(ChatMessage chatMessage) {
        MongoChatMessage mongoChatMessage = new MongoChatMessage(
                null,
                chatMessage.getContent(),
                chatMessage.getSender(),
                chatMessage.getRoomId(),
                CommonUtil.getCurrentTimeStamp()
        );
        mongoChatRepository.save(mongoChatMessage);
    }

    private void saveToRedis(ChatMessage chatMessage) {
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


    public List<MongoChatMessage> getMongoChatMessages(String roomId) {
        return mongoChatRepository.findByRoomId(roomId);
    }
}
