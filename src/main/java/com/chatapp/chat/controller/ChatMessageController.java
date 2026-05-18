package com.chatapp.chat.controller;
import com.chatapp.chat.dto.ChatMessageRequest;
import com.chatapp.chat.dto.ChatMessageResponse;
import com.chatapp.chat.service.ChatService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ChatMessageController {
    private final ChatService chatService;


    @PostMapping("/message")
    public ResponseEntity<ChatMessageResponse> sendMessage(@RequestBody @Valid ChatMessageRequest request ) {
         var msg = chatService.sendMessage(request.roomId(), request.senderId(), request.senderName(), request.content());

         return  ResponseEntity.ok(ChatMessageResponse.fromEntity(msg));
    }

    @GetMapping("/message/{roomId}")
    public ResponseEntity<List<ChatMessageResponse>>  getMessage(@PathVariable String roomId) {
        return ResponseEntity.ok(chatService.getMessagesByRoom(roomId).stream().map(ChatMessageResponse::fromEntity).toList());
    }

}
