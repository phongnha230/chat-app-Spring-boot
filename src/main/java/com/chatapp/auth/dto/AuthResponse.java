package com.chatapp.auth.dto;

import com.chatapp.user.dto.UserResponse;

public record AuthResponse(
    String token,
    String tokenType, //trường này
    Long expiresIn,
    UserResponse user
) {
    // Method of GIÚP bạn tự động điền "Bearer"
    public static AuthResponse of(String token,Long expiresIn, UserResponse user) {
        return new AuthResponse(token, "Bearer", expiresIn, user);
    }
}
