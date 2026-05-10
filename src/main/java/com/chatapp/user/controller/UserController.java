package com.chatapp.user.controller;
import com.chatapp.user.dto.UserCreateRequest;
import com.chatapp.user.dto.UserResponse;
import com.chatapp.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Tạm thời cho CORS, Bước 8 sẽ config chặt hơn
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserCreateRequest request) {
        if(userService.existsByName(request.name())) throw new RuntimeException("Name exists!");
        var user = userService.createUser(request.name(), request.email(), request.password());
        //
        return ResponseEntity
                .status(HttpStatus.CREATED)  // ✅ 201 Created cho resource mới
                .body(UserResponse.fromEntity(user));
        //return ResponseEntity.ok(UserResponse.fromEntity(user));
    }
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll().stream().map(UserResponse::fromEntity).toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
        return userService.finById(id)
                .map(u -> ResponseEntity.ok(UserResponse.fromEntity(u)))
                .orElseGet(() -> ResponseEntity.notFound().build());

        // ✅ Cách viết rõ ràng, dễ debug
//        Optional<User> userOpt = userService.findById(id);
//
//        if (userOpt.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        UserResponse response = UserResponse.fromEntity(userOpt.get());
//        return ResponseEntity.ok(response);
    }

}
