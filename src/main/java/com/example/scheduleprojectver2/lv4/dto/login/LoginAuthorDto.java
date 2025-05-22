package com.example.scheduleprojectver2.lv4.dto.login;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginAuthorDto {

    @NotBlank
    private final String email;

    @NotBlank
    private final String password;
    // 로그인 인증
    public LoginAuthorDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
