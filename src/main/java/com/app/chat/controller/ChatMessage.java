package com.app.chat.controller;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChatMessage implements Serializable {
    private String sender;
    private String content;
    private String roomId;
}
