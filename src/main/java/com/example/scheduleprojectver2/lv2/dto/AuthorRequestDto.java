package com.example.scheduleprojectver2.lv2.dto;

import lombok.Getter;

@Getter
public class AuthorRequestDto {

    private final String name;
    private final String email;
    private final String password;


    public AuthorRequestDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
