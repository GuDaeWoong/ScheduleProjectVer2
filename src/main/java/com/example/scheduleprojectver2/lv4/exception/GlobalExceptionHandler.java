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

    // 추상메서드를 어떻게 해야할지 몰라서 예외처리마다 추가...
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

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAuthorNotFoundException(AuthorNotFoundException ex, HttpServletRequest request) {
        String message = ex.getMessage();
        HttpStatus httpStatus = ex.getHttpStatus();

        ErrorResponse errorResponse = new ErrorResponse(
                message,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        String message = ex.getMessage();
        HttpStatus httpStatus = ex.getHttpStatus(); // 이 경우 주로 HttpStatus.NOT_FOUND (404)가 될 것입니다.

        ErrorResponse errorResponse = new ErrorResponse(
                message,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

}
