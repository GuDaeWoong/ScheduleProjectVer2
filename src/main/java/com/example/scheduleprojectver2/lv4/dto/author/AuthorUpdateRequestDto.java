package com.example.scheduleprojectver2.lv4.dto.author;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AuthorUpdateRequestDto {
    private Long id;

    @Size(max = 4, message = "사용자명은 4글자를 초과할 수 없습니다.")
    private String name;

    private String password;

    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+[.][a-zA-Z]{2,3}$", message = "이메일 주소 양식을 확인해주세요")
    private String email;

    //사용자가 비밀번호, 이메일 또는 이름 중 하나 이상을 수정했는지를 확인하는 검증 로직
    @AssertTrue(message = "하나 이상 수정해야 합니다.")
    public boolean hasPendingChanges() {
        return password != null || email != null || name != null;
    }
}


