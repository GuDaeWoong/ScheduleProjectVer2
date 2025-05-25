package com.example.scheduleprojectver2.lv4.dto.schedule;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleGetAllResponseDto {
    private String title;
    private String contents;
    private int commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String authorName;


    public ScheduleGetAllResponseDto(String title, String contents, Long commentCount, LocalDateTime createdAt, LocalDateTime modifiedAt, String authorName) {
        this.title = title;
        this.contents = contents;
        this.commentCount = commentCount != null ? commentCount.intValue() : 0;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.authorName = authorName;
    }
}
