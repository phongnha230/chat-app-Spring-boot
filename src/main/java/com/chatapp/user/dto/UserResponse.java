package com.chatapp.user.dto;
import com.chatapp.user.entity.User;
import java.time.LocalDateTime;
public record UserResponse(String id, String name, String email, String password, LocalDateTime createAt) {
    public static UserResponse fromEntity(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getCreateAt());
    }
}
