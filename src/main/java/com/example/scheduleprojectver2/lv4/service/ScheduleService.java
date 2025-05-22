package com.example.scheduleprojectver2.lv4.service;

import com.example.scheduleprojectver2.lv4.dto.schedule.ScheduleRequestDto;
import com.example.scheduleprojectver2.lv4.dto.schedule.ScheduleResponseDto;
import com.example.scheduleprojectver2.lv4.dto.schedule.ScheduleUpdateRequestDto;
import com.example.scheduleprojectver2.lv4.entity.Schedule;
import com.example.scheduleprojectver2.lv4.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final AuthorService authorService;


    public ScheduleResponseDto save(ScheduleRequestDto requestDto) {


        Schedule schedule = new Schedule(requestDto.getTitle(), requestDto.getContents());
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
        Optional<Schedule> findSchedule = scheduleRepository.findById(id);
        if (findSchedule.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
        Schedule findScheduleById = findSchedule.get();
        return new ScheduleResponseDto(findScheduleById.getId(), findScheduleById.getTitle(), findScheduleById.getContents());

    }

    @Transactional
    public void updateSchedule(Long id, @Valid ScheduleUpdateRequestDto updateRequestDto) {
        Optional<Schedule> findSchedule = scheduleRepository.findById(id);
        if (findSchedule.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        findSchedule.get().updateSchedule(updateRequestDto);
    }

    public void delete(Long id) {
        Optional<Schedule> findSchedule = scheduleRepository.findById(id);
        if (findSchedule.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
        scheduleRepository.delete(findSchedule.get());
    }



}
