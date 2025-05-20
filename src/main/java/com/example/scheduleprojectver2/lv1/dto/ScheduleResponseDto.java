package com.example.scheduleprojectver2.lv1.dto;

import com.example.scheduleprojectver2.lv1.entity.ScheduleEntity;
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

    public ScheduleResponseDto(ScheduleEntity scheduleEntity) {
        this.id = scheduleEntity.getId();
        this.title = scheduleEntity.getTitle();
        this.contents = scheduleEntity.getContents();
    }

    public static ScheduleResponseDto toDto(ScheduleEntity scheduleEntity) {
        return new ScheduleResponseDto(scheduleEntity.getId(),scheduleEntity.getTitle(),scheduleEntity.getContents());
    }
}
