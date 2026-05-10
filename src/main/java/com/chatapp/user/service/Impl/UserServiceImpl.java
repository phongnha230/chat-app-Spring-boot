package com.chatapp.user.service.Impl;

import com.chatapp.user.entity.User;
import com.chatapp.user.repository.UserRepository;
import com.chatapp.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // Mọi method mặc định read-only
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public User createUser(String name, String email, String password) {
        User user = User.builder()
                .name(name)
                .email(email)
                .password(password)
                .createAt(LocalDateTime.now())
                .build();
        return userRepository.save(user);
    }

    @Override
    public Optional<User> finById(String id) {

        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByName(String name) {
        return userRepository.existsByName(name);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
