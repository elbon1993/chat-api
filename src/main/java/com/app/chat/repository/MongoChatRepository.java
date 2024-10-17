package com.app.chat.repository;

import com.app.chat.model.MongoChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoChatRepository extends MongoRepository<MongoChatMessage, String> {
    List<MongoChatMessage> findByRoomId(String roomId);
}
