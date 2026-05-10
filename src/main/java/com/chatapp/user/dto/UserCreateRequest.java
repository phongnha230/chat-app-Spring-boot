package com.chatapp.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(
// Muốn không controll thì như này: String name, String email, String password
// muốn thêm tính năng bảo cơ chế cho đúng quy dữ liệu theo ý mình muốn
    @NotBlank @Size(min = 3, max = 50) String name,
    @NotBlank @Email String email,
    @NotBlank @Size(min = 6) String password

) { }
