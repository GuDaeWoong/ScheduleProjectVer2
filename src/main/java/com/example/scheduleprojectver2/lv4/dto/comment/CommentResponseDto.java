package com.example.scheduleprojectver2.lv4.dto.comment;

import com.example.scheduleprojectver2.lv4.entity.Comment;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentResponseDto {
    private Long commentId;
    private Long authorId;
    private Long scheduleId;
    private String content;

    public CommentResponseDto(Comment saveComment) {
        this.commentId = saveComment.getId();
        this.authorId = saveComment.getAuthor().getId();
        this.scheduleId = saveComment.getSchedule().getId();
        this.content = saveComment.getContents();

    }

    public CommentResponseDto(Long commentId, Long authorId, Long scheduleId, @NotNull String contents) {
        this.commentId = commentId;
        this.authorId = authorId;
        this.scheduleId = scheduleId;
        this.content = contents;
    }

    public static CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getAuthor().getId(),
                comment.getSchedule().getId(),
                comment.getContents()
        );
    }

}
