package com.example.scheduleprojectver2.lv4.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private String message;
    private String timestamp;
    private String path;

    public ErrorResponse(String message, String timestamp, String path) {
        this.message = message;
        this.timestamp = timestamp;
        this.path = path;
    }
}