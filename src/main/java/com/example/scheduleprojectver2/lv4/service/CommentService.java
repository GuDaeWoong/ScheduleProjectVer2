package com.example.scheduleprojectver2.lv4.service;


import com.example.scheduleprojectver2.lv4.dto.comment.CommentRequestDto;
import com.example.scheduleprojectver2.lv4.dto.comment.CommentResponseDto;
import com.example.scheduleprojectver2.lv4.entity.Author;
import com.example.scheduleprojectver2.lv4.entity.Comment;
import com.example.scheduleprojectver2.lv4.entity.Schedule;
import com.example.scheduleprojectver2.lv4.repository.CommentRepository;
import com.example.scheduleprojectver2.lv4.util.Const;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final AuthorService authorService;
    private final ScheduleService scheduleService;

    public CommentResponseDto saveComment(Long scheduleId,CommentRequestDto requestDto, HttpServletRequest request
    ) {
        // 작성자 추출
        HttpSession session = request.getSession(false);
        Author loginAuthor =(Author) session.getAttribute(Const.LOGIN_AUTHOR);
        
        // schedule 추출
        Schedule ScheduleGetId = scheduleService.getId(scheduleId);

        Comment comment = new Comment(loginAuthor, ScheduleGetId, requestDto.getContents());
        Comment saveComment = commentRepository.save(comment);
        return  new CommentResponseDto(saveComment);
    }

    public List<CommentResponseDto> findAllComment() {
        return commentRepository.findAll()
                .stream().map(CommentResponseDto::toDto)
                .toList();
    }

    public List<CommentResponseDto> scheduleCommentViewer(Long scheduleId) {
        return commentRepository.findByScheduleId(scheduleId)
                .stream()
                .map(CommentResponseDto::toDto)
                .toList();
    }


}
