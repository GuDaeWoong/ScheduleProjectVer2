package com.example.scheduleprojectver2.lv2.entity;

import com.example.scheduleprojectver2.lv2.dto.ScheduleUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;


    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String contents;

    public Schedule(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public Schedule() {

    }
    public void setAuthor(Author author) {
        this.author=author;
    }

    public void updateSchedule(ScheduleUpdateRequestDto updateRequestDto) {
        if (updateRequestDto.getTitle() != null && !updateRequestDto.getTitle().isEmpty()) {
            this.title = updateRequestDto.getTitle();
        }

        if (updateRequestDto.getContents() != null && !updateRequestDto.getContents().isEmpty()) {
            this.contents = updateRequestDto.getContents();
        }
    }

}
