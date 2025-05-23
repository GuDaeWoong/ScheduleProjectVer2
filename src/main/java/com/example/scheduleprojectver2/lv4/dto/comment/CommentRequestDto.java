package com.example.scheduleprojectver2.lv4.dto.comment;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentRequestDto {

    @NotNull
    private String contents;

}
