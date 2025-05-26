package com.example.scheduleprojectver2.lv4.service;


import com.example.scheduleprojectver2.lv4.dto.comment.CommentRequestDto;
import com.example.scheduleprojectver2.lv4.dto.comment.CommentResponseDto;
import com.example.scheduleprojectver2.lv4.dto.comment.CommentUpdateRequestDto;
import com.example.scheduleprojectver2.lv4.entity.Author;
import com.example.scheduleprojectver2.lv4.entity.Comment;
import com.example.scheduleprojectver2.lv4.entity.Schedule;
import com.example.scheduleprojectver2.lv4.repository.CommentRepository;
import com.example.scheduleprojectver2.lv4.util.Const;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final AuthorService authorService;
    private final ScheduleService scheduleService;

    @Transactional
    public CommentResponseDto saveComment(Long scheduleId,CommentRequestDto requestDto, HttpServletRequest request
    ) {
        // 작성자 추출
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not logged in. Session not found.");
        }
        Author loginAuthor =(Author) session.getAttribute(Const.LOGIN_AUTHOR);
        
        Schedule ScheduleGetId = scheduleService.getId(scheduleId);

        Comment comment = new Comment(loginAuthor, ScheduleGetId, requestDto.getContents());
        Comment saveComment = commentRepository.save(comment);
        return  new CommentResponseDto(saveComment);
    }

    @Transactional
    public List<CommentResponseDto> findAllComment() {
        return commentRepository.findAll()
                .stream().map(CommentResponseDto::toDto)
                .toList();
    }

    @Transactional
    public List<CommentResponseDto> scheduleCommentViewer(Long scheduleId) {
        return commentRepository.findByScheduleId(scheduleId)
                .stream()
                .map(CommentResponseDto::toDto)
                .toList();
    }

    @Transactional
    public void updateCommnet(Long commentId, @Valid CommentUpdateRequestDto updateRequestDto, HttpServletRequest request) {
        // 로그인한 사용자 정보 가져오기
        Author loggedInAuthor = (Author) request.getSession().getAttribute(Const.LOGIN_AUTHOR);

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found with ID: " + commentId));

        // 로그인한 사용자가 해당 댓글의 작성자인지 확인
        if (!loggedInAuthor.getId().equals(comment.getAuthor().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to modify this comment.");
        }

        comment.updateComment(updateRequestDto);
    }

    @Transactional
    public void deleteComment(Long commentId, HttpServletRequest request) {
        // 로그인한 사용자 정보 가져오기
        Author loggedInAuthor = (Author) request.getSession().getAttribute(Const.LOGIN_AUTHOR);

        // 댓글 찾기 및 댓글이 없는 경우 처리
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found with ID: " + commentId));

        // 로그인한 사용자가 해당 댓글의 작성자인지 확인
        if (!loggedInAuthor.getId().equals(comment.getAuthor().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to modify this comment.");
        }

        commentRepository.delete(comment);

    }
}
