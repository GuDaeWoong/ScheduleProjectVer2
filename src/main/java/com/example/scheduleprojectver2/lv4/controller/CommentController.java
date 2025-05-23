package com.example.scheduleprojectver2.lv4.controller;


import com.example.scheduleprojectver2.lv4.dto.comment.CommentRequestDto;
import com.example.scheduleprojectver2.lv4.dto.comment.CommentResponseDto;
import com.example.scheduleprojectver2.lv4.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //생성
//    @PostMapping("/create")
    @PostMapping("/create/{scheduleId}")
    public ResponseEntity<CommentResponseDto> saveComment(
//            @RequestParam Long scheduleId,
            @PathVariable Long scheduleId,
            @RequestBody CommentRequestDto requestDto,
            HttpServletRequest request
            ) {
        return new ResponseEntity<>(commentService.saveComment(scheduleId,requestDto,request), HttpStatus.CREATED);
    }

    // 모두 조회
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> findAllComment() {
        List<CommentResponseDto> commentResponseDtos = commentService.findAllComment();
        return new ResponseEntity<>(commentResponseDtos,HttpStatus.OK);
    }

    // 스케줄선택 하여 모든 댓글 조회
    @GetMapping("/{scheduleId}")
    public ResponseEntity<List<CommentResponseDto>> scheduleCommentViewer(@PathVariable Long scheduleId
    ) {
       return new ResponseEntity<>(commentService.scheduleCommentViewer(scheduleId),HttpStatus.OK);
    }


}



