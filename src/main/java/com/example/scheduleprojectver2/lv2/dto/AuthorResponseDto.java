package com.example.scheduleprojectver2.lv2.dto;

import com.example.scheduleprojectver2.lv2.entity.Author;
import lombok.Getter;

@Getter
public class AuthorResponseDto {
    private final Long id;
    private final String name;
    private final String password;
    private final String email;





    public AuthorResponseDto(Long id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public AuthorResponseDto(Author save) {
        this.id = save.getId();
        this.name = save.getName();
        this.password = save.getPassword();
        this.email = save.getEmail();
    }
}
