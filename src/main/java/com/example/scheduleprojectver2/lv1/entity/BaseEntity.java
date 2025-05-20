package com.example.scheduleprojectver2.lv1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
//JPA에서 엔티티의 특정 이벤트 발생 시 자동으로 특정 로직을 수행하도록 설정
@EntityListeners(AuditingEntityListener.class)
// abstract 추상 클래스
public abstract class BaseEntity {

    // @CreatedDate 만들때 시간을 갱신
    @CreatedDate
    @Column(updatable = false)
    //    @Temporal(TemporalType.TIMESTAMP) 생략가능
    private LocalDateTime createdAt;

    // @LastModifiedDate 수정할때 시간을 갱신
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
