package com.chatapp.chat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChatMessageRequest(@NotBlank String roomId, @NotBlank String senderId, @NotBlank String senderName,
        @NotBlank @Size(min = 1) String content) {
}
