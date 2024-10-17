package com.app.chat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "chat_messages")
public class MongoChatMessage {

    @Id
    private String id;
    private String sender;
    private String content;
    private String roomId;
    private long timestamp;

}
