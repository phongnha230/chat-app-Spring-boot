package com.chatapp.auth.service;
import com.chatapp.auth.dto.LoginRequest;
import com.chatapp.user.dto.UserResponse;
import com.chatapp.auth.dto.AuthResponse;
import com.chatapp.user.dto.UserCreateRequest;
public interface AuthService {
    UserResponse register(UserCreateRequest request);
    AuthResponse login(LoginRequest request);
}
