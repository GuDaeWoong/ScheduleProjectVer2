package com.example.scheduleprojectver2.lv2.dto;

import com.example.scheduleprojectver2.lv2.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {

    private final Long id;
    private final String title;
    private final String contents;

    public ScheduleResponseDto(Long id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

    public ScheduleResponseDto(Schedule scheduleEntity) {
        this.id = scheduleEntity.getId();
        this.title = scheduleEntity.getTitle();
        this.contents = scheduleEntity.getContents();
    }

    public static ScheduleResponseDto toDto(Schedule scheduleEntity) {
        return new ScheduleResponseDto(scheduleEntity.getId(),scheduleEntity.getTitle(),scheduleEntity.getContents());
    }
}
