package com.chatapp.auth.service.Impl;
import com.chatapp.auth.dto.AuthResponse;
import com.chatapp.auth.dto.LoginRequest;
import com.chatapp.auth.security.JwtTokenProvider;
import com.chatapp.auth.security.UserDetailsImpl;

import com.chatapp.auth.service.AuthService;
import com.chatapp.user.dto.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import com.chatapp.user.dto.UserResponse;
import com.chatapp.user.repository.UserRepository;
import com.chatapp.user.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    @Override
    @Transactional
    public UserResponse register(UserCreateRequest request) {
       if(userRepository.existsByName(request.name())) {
           throw new RuntimeException("User name exists");
       }
       if(userRepository.existsByEmail(request.email())) {
           throw new RuntimeException("Email exist");
       }
        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();
        userRepository.save(user);
        return UserResponse.fromEntity(user);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.usernameOrEmail(),
                        request.password()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();

        User user = userRepository.findByName(userDetails.getName()).orElseThrow(() -> new RuntimeException("Not found"));
        return AuthResponse.of(jwt, 8640000L, UserResponse.fromEntity(user));
    }
}
