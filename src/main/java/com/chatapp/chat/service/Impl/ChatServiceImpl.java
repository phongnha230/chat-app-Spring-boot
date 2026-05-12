package com.chatapp.chat.service.Impl;

import com.chatapp.chat.entity.ChatMessage;
import com.chatapp.chat.repository.ChatMessageRepository;
import com.chatapp.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatMessageRepository messageRepository;

    @Override
    public ChatMessage sendMessage(String roomId, String senderId, String senderName, String content) {
        ChatMessage chat = ChatMessage.builder()
                .roomId(roomId)
                .senderId(senderId)
                .senderName(senderName)
                .content(content)
                .timestamp(LocalDateTime.now())
                .build();
        return messageRepository.save(chat);
    }

    @Override
    public List<ChatMessage> getBySenderId(String senderId) {
        return messageRepository.findBySenderId(senderId);
    }

    @Override
    public List<ChatMessage> getMessagesByRoom(String roomId) {
        return messageRepository.findByRoomIdOrderByTimestampAsc(roomId);
    }

    @Override
    public long getMessageCount(String roomId) {
        return messageRepository.countByRoomId(roomId);
    }
}
