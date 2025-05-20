package com.example.scheduleprojectver2.lv1.repository;

import com.example.scheduleprojectver2.lv1.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity,Long > {
    default ScheduleEntity findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Does not exist id = " + id));
    }
}
