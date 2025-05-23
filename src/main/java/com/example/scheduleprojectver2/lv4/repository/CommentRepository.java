package com.example.scheduleprojectver2.lv4.repository;

import com.example.scheduleprojectver2.lv4.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long >{

    List<Comment> findByScheduleId(Long scheduleId);
}

