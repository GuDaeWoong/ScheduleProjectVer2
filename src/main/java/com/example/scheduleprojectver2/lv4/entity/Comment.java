package com.example.scheduleprojectver2.lv4.entity;

import com.example.scheduleprojectver2.lv4.dto.comment.CommentUpdateRequestDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Getter
@Table(name = "comment")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @NotNull
    private String contents;

    public Comment(Author author, Schedule schedule, String requestDto) {
        this.author = author;
        this.schedule = schedule;
        this.contents = String.valueOf(requestDto);

    }

    public Comment() {
    }

    public void updateComment(@Valid CommentUpdateRequestDto updateRequestDto) {
        if (updateRequestDto.getContent() != null) {
            this.contents = updateRequestDto.getContent();
        }
    }
}
