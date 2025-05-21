package com.example.scheduleprojectver2.lv2.dto;

import jakarta.validation.constraints.AssertTrue;
import lombok.Getter;

@Getter
public class AuthorUpdateRequestDto {
    private Long id;
    private String password;
    private String email;

    @AssertTrue(message = "하나 이상 수정해야 합니다.")
    public boolean hasPendingChanges() {
        return password!= null || email!= null;
    }
}


