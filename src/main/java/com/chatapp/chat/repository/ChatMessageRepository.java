package com.chatapp.chat.repository;
import com.chatapp.chat.entity.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
public interface ChatMessageRepository extends MongoRepository<ChatMessage, String>{
    List<ChatMessage> findByRoomIdOrderByTimestampAsc(String roomId);
    List<ChatMessage> findBySenderId(String senderId);
    long countByRoomId(String roomId);
}
