package com.example.scheduleprojectver2.lv1.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "schedule")
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String contents;

    public ScheduleEntity(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public ScheduleEntity() {

    }


}
