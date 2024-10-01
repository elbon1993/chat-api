
package com.app.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/* @Slf4j
@Service
public class RedisSubscriber implements MessageListener {

    private final ObjectMapper objectMapper;

    public RedisSubscriber(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        SignalMessage signalMessage = objectMapper.convertValue(new String(message.getBody(), StandardCharsets.UTF_8), SignalMessage.class);
        log.info("Received message: " + signalMessage);
    }
}
*/
