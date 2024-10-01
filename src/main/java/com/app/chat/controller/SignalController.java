
package com.app.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;



@Controller
public class SignalController {

    private final SimpMessagingTemplate messagingTemplate;

    public SignalController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/webrtc")
    public void webrtcSignaling(String message) {
        // Forward the message to the other participant
        messagingTemplate.convertAndSend("/topic/webrtc", message);
    }
}

