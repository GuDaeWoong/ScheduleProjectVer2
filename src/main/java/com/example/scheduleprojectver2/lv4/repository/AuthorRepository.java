package com.example.scheduleprojectver2.lv4.repository;

import com.example.scheduleprojectver2.lv4.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByEmail(String email);
}
