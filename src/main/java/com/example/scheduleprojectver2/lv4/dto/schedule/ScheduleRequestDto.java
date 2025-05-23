package com.example.scheduleprojectver2.lv4.dto.schedule;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {

    @Size(max = 10, message = "할 일 제목은 10글자를 초과할 수 없습니다.")
    private final String title;

    @NotNull
    private final String contents;

    public ScheduleRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
