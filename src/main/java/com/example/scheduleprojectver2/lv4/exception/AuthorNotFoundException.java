package com.example.scheduleprojectver2.lv4.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AuthorNotFoundException extends RuntimeException {

    private final HttpStatus httpStatus;

    public AuthorNotFoundException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
