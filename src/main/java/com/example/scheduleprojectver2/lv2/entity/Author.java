package com.example.scheduleprojectver2.lv2.entity;

import com.example.scheduleprojectver2.lv2.dto.AuthorUpdateRequestDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;

@Getter
@Entity
//sql 테이블 만드는 거라고 생각하면될듯
@Table(name = "author")
public class Author extends BaseEntity{

    @Id
    // 엔티티의 기본 키(Primary Key) 값을 자동으로 생성하는 방식을 지정할 때 사용
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;


    public Author(String name, String password, String email) {
        this.name = name;
        this.password =password;
        this.email = email;
    }

    public Author() {
    }

    public void updateAuthor(@Valid AuthorUpdateRequestDto updateRequestDto) {
        if (updateRequestDto.getEmail() != null && !updateRequestDto.getPassword().isEmpty()) {
            this.email = updateRequestDto.getEmail();
        }
        if (updateRequestDto.getPassword() != null && !updateRequestDto.getEmail().isEmpty()) {
            this.password = updateRequestDto.getPassword();
        }

    }

}
