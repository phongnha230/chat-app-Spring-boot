package com.chatapp.user.service;

import com.chatapp.user.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(String name, String email, String password);
    Optional<User> findByName(String name);
    Optional<User> finById(String id);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    boolean existsByName(String name);

    boolean existsByEmail(String email);
    void deleteUser(String id);

}
