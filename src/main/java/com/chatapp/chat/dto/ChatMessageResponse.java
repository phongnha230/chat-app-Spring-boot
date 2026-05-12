package com.chatapp.chat.dto;

import com.chatapp.chat.entity.ChatMessage;

import java.time.LocalDateTime;

public record ChatMessageResponse(String id, String roomId, String senderId, String senderName, String content, LocalDateTime timestamp) {
    public static ChatMessageResponse fromEntity(ChatMessage chatMessage) {
        return new ChatMessageResponse(chatMessage.getId(), chatMessage.getRoomId(), chatMessage.getSenderId(), chatMessage.getSenderName(), chatMessage.getContent(), chatMessage.getTimestamp());
    }
}
