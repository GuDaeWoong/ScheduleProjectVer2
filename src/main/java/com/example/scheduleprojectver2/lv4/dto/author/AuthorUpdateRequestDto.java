package com.example.scheduleprojectver2.lv4.dto.author;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AuthorUpdateRequestDto {
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @NotBlank
    private String email;

    //사용자가 비밀번호, 이메일 또는 이름 중 하나 이상을 수정했는지를 확인하는 검증 로직
    @AssertTrue(message = "하나 이상 수정해야 합니다.")
    public boolean hasPendingChanges() {
        return password != null || email != null || name != null;
    }
}


