package com.example.scheduleprojectver2.lv2.service;

import com.example.scheduleprojectver2.lv2.dto.ScheduleRequestDto;
import com.example.scheduleprojectver2.lv2.dto.ScheduleResponseDto;
import com.example.scheduleprojectver2.lv2.dto.ScheduleUpdateRequestDto;
import com.example.scheduleprojectver2.lv2.entity.Schedule;
import com.example.scheduleprojectver2.lv2.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;


    public ScheduleResponseDto save(ScheduleRequestDto requestDto) {

        Schedule schedule = new Schedule(requestDto.getTitle(),requestDto.getContents());
        // db에 저장
        Schedule save = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(save);
    }

    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll()
                .stream().map(ScheduleResponseDto::toDto)
                .toList();
    }

    public ScheduleResponseDto findById(Long id) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);

        return new ScheduleResponseDto(schedule.getId(),schedule.getTitle(),schedule.getContents());
    }

    public void delete(Long id) {
        Schedule findScheduleId = scheduleRepository.findByIdOrElseThrow(id);
        scheduleRepository.delete(findScheduleId);
    }

    @Transactional
    public void updateSchedule(Long id, @Valid ScheduleUpdateRequestDto updateRequestDto) {
        Schedule findScheduleId = scheduleRepository.findByIdOrElseThrow(id);
        findScheduleId.updateSchedule(updateRequestDto);
    }
}
