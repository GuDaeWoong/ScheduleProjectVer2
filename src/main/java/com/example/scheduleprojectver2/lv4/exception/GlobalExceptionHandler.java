package com.example.scheduleprojectver2.lv4.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ErrorResponse> handleLoginException(LoginException ex, HttpServletRequest request) {
        String message = ex.getMessage();
        HttpStatus httpStatus = ex.getHttpStatus();

        ErrorResponse errorResponse = new ErrorResponse(
                message,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

}
