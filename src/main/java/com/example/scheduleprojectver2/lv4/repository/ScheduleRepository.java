package com.example.scheduleprojectver2.lv4.repository;

import com.example.scheduleprojectver2.lv4.dto.schedule.ScheduleGetAllResponseDto;
import com.example.scheduleprojectver2.lv4.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScheduleRepository extends JpaRepository<Schedule,Long > {

    @Query("SELECT new com.example.scheduleprojectver2.lv4.dto.schedule.ScheduleGetAllResponseDto(" +
            "s.title, s.contents, COUNT(c.contents), s.createdAt, s.modifiedAt, a.name) " +
            "FROM Schedule s " +
            "LEFT JOIN s.comments c " +
            "LEFT JOIN s.author a " +
            "GROUP BY s.id, s.title, s.contents, s.createdAt, s.modifiedAt, a.name " +
            "ORDER BY s.modifiedAt DESC")
    Page<ScheduleGetAllResponseDto> findAllSchedulesWithCommentCount(Pageable pageable);

}
