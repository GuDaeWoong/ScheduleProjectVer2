package com.example.scheduleprojectver2.lv4.entity;

import com.example.scheduleprojectver2.lv4.dto.schedule.ScheduleUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "schedule")
public class Schedule extends BaseEntity {

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

//    public Schedule( String title, String contents) {
//        this.title = title;
//        this.contents = contents;
//    }

    public Schedule(Author author, String title, String contents) {
        this.author = author; // 이 부분 추가
        this.title = title;
        this.contents = contents;
    }

    public Schedule() {
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void updateSchedule(ScheduleUpdateRequestDto updateRequestDto) {
        if (updateRequestDto.getTitle() != null) {
            this.title = updateRequestDto.getTitle();
        }
        if (updateRequestDto.getContents() != null) {
            this.contents = updateRequestDto.getContents();
        }
    }

}
