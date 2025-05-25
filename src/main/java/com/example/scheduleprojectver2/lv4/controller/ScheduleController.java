package com.example.scheduleprojectver2.lv4.controller;

import com.example.scheduleprojectver2.lv4.dto.schedule.ScheduleGetAllResponseDto;
import com.example.scheduleprojectver2.lv4.dto.schedule.ScheduleRequestDto;
import com.example.scheduleprojectver2.lv4.dto.schedule.ScheduleResponseDto;
import com.example.scheduleprojectver2.lv4.dto.schedule.ScheduleUpdateRequestDto;
import com.example.scheduleprojectver2.lv4.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<ScheduleResponseDto> save(
            @RequestBody ScheduleRequestDto requestDto,
            HttpServletRequest request) {
        return new ResponseEntity<>(scheduleService.save(requestDto,request), HttpStatus.CREATED);
    }

    // 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        List<ScheduleResponseDto> scheduleResponseDtos = scheduleService.findAll();
        return new ResponseEntity<>(scheduleResponseDtos, HttpStatus.OK);
    }

    // 모두 조회 (제목, 내용, 댓글 개수, 작성일, 수정일, 작성 유저명)
    @GetMapping("/all")
    public ResponseEntity<Page<ScheduleGetAllResponseDto>> getAllSchedule(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<ScheduleGetAllResponseDto> schedulePage = scheduleService.getAllSchedule(page, size);
        return new ResponseEntity<>(schedulePage, HttpStatus.OK);
    }

    // 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(
            @PathVariable Long id
    ) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.findById(id);
        return new ResponseEntity<>(scheduleResponseDto,HttpStatus.OK);
    }

    // 업데이트
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateSchedule(@PathVariable Long id,
                                               @Valid @RequestBody ScheduleUpdateRequestDto updateRequestDto,
                                               HttpServletRequest request) {
        scheduleService.updateSchedule(id, updateRequestDto,request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        scheduleService.delete(id,request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
