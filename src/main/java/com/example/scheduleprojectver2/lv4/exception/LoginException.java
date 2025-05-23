package com.example.scheduleprojectver2.lv4.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class LoginException extends RuntimeException {

    private final HttpStatus httpStatus;

    public LoginException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
