package com.example.scheduleprojectver2.lv1.service;

import com.example.scheduleprojectver2.lv1.dto.CreateScheduleRequestDto;
import com.example.scheduleprojectver2.lv1.dto.ScheduleResponseDto;
import com.example.scheduleprojectver2.lv1.entity.ScheduleEntity;
import com.example.scheduleprojectver2.lv1.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;


    public ScheduleResponseDto save(CreateScheduleRequestDto requestDto) {


        ScheduleEntity scheduleEntity = new ScheduleEntity(requestDto.getTitle(),requestDto.getContents());

        // db에 저장
        ScheduleEntity save = scheduleRepository.save(scheduleEntity);

        return new ScheduleResponseDto(save);
    }

    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll()
                .stream().map(ScheduleResponseDto::toDto)
                .toList();
    }

    public ScheduleResponseDto findById(Long id) {
        ScheduleEntity scheduleEntity = scheduleRepository.findByIdOrElseThrow(id);

        return new ScheduleResponseDto(scheduleEntity.getId(),scheduleEntity.getTitle(),scheduleEntity.getContents());
    }

    public void delete(Long id) {
        ScheduleEntity findScheduleId = scheduleRepository.findByIdOrElseThrow(id);
        scheduleRepository.delete(findScheduleId);
    }
}
