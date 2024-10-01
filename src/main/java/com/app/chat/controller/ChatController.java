package com.app.chat.controller;

import com.app.chat.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Slf4j
public class ChatController {
    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public Message sendMessage(Message message) {
        System.out.println("message received: "+message);
        return message; // Echo the message back to the clients
    }
}
