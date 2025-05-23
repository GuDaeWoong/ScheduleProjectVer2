package com.example.scheduleprojectver2.lv4.entity;

import com.example.scheduleprojectver2.lv4.dto.schedule.ScheduleUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

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

    // 연결만하는것이고 변수가 생성되진않음
    // 관계성 주인임을 명명
    @OneToMany(mappedBy = "schedule")
    private List<Comment> comments;

    public Schedule(Author author, String title, String contents) {
        this.author = author;
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
