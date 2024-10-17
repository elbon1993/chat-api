package com.app.chat.controller;

import com.app.chat.model.MongoChatMessage;
import com.app.chat.service.ChatMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class GroupChatController {
    @Autowired
    ChatMessageService chatMessageService;

    @MessageMapping("/sendMessage/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessage sendMessage(@DestinationVariable String roomId, @Payload ChatMessage chatMessage) {
        log.info("message recieved in server: " + chatMessage);
        chatMessageService.saveChatMessage(chatMessage);
        return chatMessage;
    }

    @GetMapping("/getMessages/{roomId}")
    public List<ChatMessage> getMessages(@PathVariable("roomId") String roomId) {
        return chatMessageService.getChatMessages(roomId);
    }

    @GetMapping("/getMessagesMongo/{roomId}")
    public List<MongoChatMessage> getMongoChatMessages(@PathVariable("roomId") String roomId) {
        return chatMessageService.getMongoChatMessages(roomId);
    }


}
