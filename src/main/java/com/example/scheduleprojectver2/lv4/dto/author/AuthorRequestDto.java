package com.example.scheduleprojectver2.lv4.dto.author;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AuthorRequestDto {

    @NotBlank
    @Size(max = 4, message = "사용자명은 4글자를 초과할 수 없습니다.")
    private final String name;

    @NotBlank
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+[.][a-zA-Z]{2,3}$", message = "이메일 주소 양식을 확인해주세요")
    private final String email;

    @NotBlank
    private final String password;


    public AuthorRequestDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
