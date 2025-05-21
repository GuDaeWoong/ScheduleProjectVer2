package com.example.scheduleprojectver2.lv2.dto;
import jakarta.validation.constraints.AssertTrue;
import lombok.Getter;

@Getter
public class ScheduleUpdateRequestDto {
    private Long id;
    private String title;
    private String contents;

    @AssertTrue(message = "하나 이상 수정해야 합니다.")
    public boolean hasPendingChanges() {
        return title!= null || contents!= null;
    }
}
