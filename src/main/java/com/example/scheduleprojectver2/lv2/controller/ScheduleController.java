package com.example.scheduleprojectver2.lv2.controller;

import com.example.scheduleprojectver2.lv2.dto.ScheduleRequestDto;
import com.example.scheduleprojectver2.lv2.dto.ScheduleResponseDto;
import com.example.scheduleprojectver2.lv2.dto.ScheduleUpdateRequestDto;
import com.example.scheduleprojectver2.lv2.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;


    // 생성
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody ScheduleRequestDto requestDto) {
        return new ResponseEntity<>(scheduleService.save(requestDto), HttpStatus.CREATED);
    }

    // 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        List<ScheduleResponseDto> scheduleResponseDtos = scheduleService.findAll();
        return new ResponseEntity<>(scheduleResponseDtos, HttpStatus.OK);
    }

    // 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(
            @PathVariable Long id
    ) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.findById(id);
        return new ResponseEntity<>(scheduleResponseDto,HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateSchedule(@PathVariable Long id,
                                               @Valid @RequestBody ScheduleUpdateRequestDto updateRequestDto) {
        scheduleService.updateSchedule(id, updateRequestDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        scheduleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
