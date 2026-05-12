package com.chatapp.chat.service;

import com.chatapp.chat.entity.ChatMessage;

import java.util.List;


public interface ChatService {
    ChatMessage sendMessage(String roomId, String senderId, String senderName, String content);
    List<ChatMessage> getMessagesByRoom(String roomId);
    List<ChatMessage> getBySenderId(String senderId);
    long getMessageCount(String roomId);
}
