package com.example.scheduleprojectver2.lv4.entity;

import com.example.scheduleprojectver2.lv4.dto.author.AuthorUpdateRequestDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
@Table(name = "author")
public class Author extends BaseEntity {

    @Id
    // 엔티티의 기본 키(Primary Key) 값을 자동으로 생성하는 방식을 지정할 때 사용
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 연결만하는것이고 변수가 생성되진않음
    // 관계성 주인임을 명명
    @OneToMany(mappedBy = "author")
    private List<Schedule> schedules;

    @NotBlank
    @Column(nullable = false, unique = true, name = "name")
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Pattern(regexp="^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+[.][a-zA-Z]{2,3}$", message="이메일 주소 양식을 확인해주세요")
//    @Email(message = "이메일 주소 양식을 확인해주세요")  // 구멍이 많다
    @Column(nullable = false, unique = true)
    private String email;

    public Author(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public Author() {
    }

    public void updateAuthor(@Valid AuthorUpdateRequestDto updateRequestDto) {
        if (updateRequestDto.getEmail() != null) {
            this.email = updateRequestDto.getEmail();
        }
        if (updateRequestDto.getPassword() != null) {
            this.password = updateRequestDto.getPassword();
        }
        if (updateRequestDto.getName() != null) {
            this.name = updateRequestDto.getName();
        }
    }
}
