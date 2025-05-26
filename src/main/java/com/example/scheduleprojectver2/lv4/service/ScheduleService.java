package com.example.scheduleprojectver2.lv4.service;

import com.example.scheduleprojectver2.lv4.dto.schedule.ScheduleGetAllResponseDto;
import com.example.scheduleprojectver2.lv4.dto.schedule.ScheduleRequestDto;
import com.example.scheduleprojectver2.lv4.dto.schedule.ScheduleResponseDto;
import com.example.scheduleprojectver2.lv4.dto.schedule.ScheduleUpdateRequestDto;
import com.example.scheduleprojectver2.lv4.entity.Author;
import com.example.scheduleprojectver2.lv4.entity.Schedule;
import com.example.scheduleprojectver2.lv4.repository.ScheduleRepository;
import com.example.scheduleprojectver2.lv4.util.Const;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final AuthorService authorService;

    @Transactional
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        Optional<Author> loginAuthor = Optional.ofNullable((Author) session.getAttribute(Const.LOGIN_AUTHOR));

        Schedule schedule = new Schedule(loginAuthor.get(), requestDto.getTitle(), requestDto.getContents());
        Schedule save = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(save);
    }

    @Transactional
    public List<ScheduleResponseDto> findAllSchedule() {
        return scheduleRepository.findAll()
                .stream().map(ScheduleResponseDto::toDto)
                .toList();
    }

    @Transactional
    public Page<ScheduleGetAllResponseDto> getAllSchedule(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("modifiedAt").descending());

        return scheduleRepository.findAllSchedulesWithCommentCount(pageable);
    }

    @Transactional
    public ScheduleResponseDto findByScheduleId(Long id) {

        Optional<Schedule> findSchedule = scheduleRepository.findById(id);
        if (findSchedule.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
        Schedule findScheduleById = findSchedule.get();

        return new ScheduleResponseDto(findScheduleById.getId(), findScheduleById.getTitle(), findScheduleById.getContents());
    }

    @Transactional
    public void updateSchedule(Long id, @Valid ScheduleUpdateRequestDto updateRequestDto, HttpServletRequest request) {
        // 로그인 작가 정보
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Session not found.");
        }
        Author loginAuthor = (Author) session.getAttribute(Const.LOGIN_AUTHOR);

        // id로 스케줄을 탐색
        Optional<Schedule> findSchedule = scheduleRepository.findById(id);
        if (findSchedule.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        Schedule scheduleToUpdate = findSchedule.get();

        if (!scheduleToUpdate.getAuthor().getId().equals(loginAuthor.getId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Not identical to author's Id");
        }

        findSchedule.get().updateSchedule(updateRequestDto);
    }

    @Transactional
    public void delete(Long id, HttpServletRequest request) {

        // 로그인한 작성자 정보 가져오기
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Session not found.");
        }
        Author loginAuthor = (Author) session.getAttribute(Const.LOGIN_AUTHOR);

        // 삭제할 스케줄
        Optional<Schedule> findSchedule = scheduleRepository.findById(id);
        if (findSchedule.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        Schedule scheduleToDelete = findSchedule.get();

        if (!scheduleToDelete.getAuthor().getId().equals(loginAuthor.getId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Not identical to author's Id");
        }
        scheduleRepository.delete(findSchedule.get());
    }

    @Transactional
    public Schedule getId(Long id) {
        Optional<Schedule> findSchedule = scheduleRepository.findById(id);
        return findSchedule.get();
    }

}
