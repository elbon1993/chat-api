/*
package com.app.chat.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class RedisPublisher {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ChannelTopic topic;

    public RedisPublisher(RedisTemplate<String, Object> redisTemplate, ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }

    public void publish(SignalMessage signalMessage) {
        redisTemplate.convertAndSend(topic.getTopic(), signalMessage);
    }
}
*/
