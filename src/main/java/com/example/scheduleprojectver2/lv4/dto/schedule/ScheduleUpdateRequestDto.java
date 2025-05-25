package com.example.scheduleprojectver2.lv4.dto.schedule;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleUpdateRequestDto {

    private Long id;

    @Size(max = 10, message = "할 일 제목은 10글자를 초과할 수 없습니다.")
    private String title;

    private String contents;

    @AssertTrue(message = "하나 이상 수정해야 합니다.")
    public boolean hasPendingChanges() {
        return title != null || contents != null;
    }
}
