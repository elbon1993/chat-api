package com.app.chat.model;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("messages")
public class Message {
    private String from;
    private String content;
}