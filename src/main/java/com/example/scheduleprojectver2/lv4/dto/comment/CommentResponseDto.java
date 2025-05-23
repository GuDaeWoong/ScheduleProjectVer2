package com.example.scheduleprojectver2.lv4.dto.comment;

import com.example.scheduleprojectver2.lv4.entity.Comment;
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
        this.commentId= saveComment.getId();
        this.authorId=saveComment.getAuthor().getId();
        this.scheduleId = saveComment.getSchedule().getId();
        this.content = saveComment.getContents();

    }
}
