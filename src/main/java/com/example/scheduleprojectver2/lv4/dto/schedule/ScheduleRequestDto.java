package com.example.scheduleprojectver2.lv4.dto.schedule;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {

    private final String title;
    private final String contents;

    public ScheduleRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
