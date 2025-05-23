package com.example.scheduleprojectver2.lv4.controller;


import com.example.scheduleprojectver2.lv4.dto.comment.CommentRequestDto;
import com.example.scheduleprojectver2.lv4.dto.comment.CommentResponseDto;
import com.example.scheduleprojectver2.lv4.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //생성
    @PostMapping("/create")
    public ResponseEntity<CommentResponseDto> saveComment(
            @RequestParam("scheduleId") Long scheduleId,
            @RequestBody CommentRequestDto requestDto,
            HttpServletRequest request
            ) {
        return new ResponseEntity<>(commentService.saveComment(scheduleId,requestDto,request), HttpStatus.CREATED);
    }



}
