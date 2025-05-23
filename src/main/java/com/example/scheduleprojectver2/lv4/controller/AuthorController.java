package com.example.scheduleprojectver2.lv4.controller;


import com.example.scheduleprojectver2.lv4.dto.author.AuthorRequestDto;
import com.example.scheduleprojectver2.lv4.dto.author.AuthorResponseDto;
import com.example.scheduleprojectver2.lv4.dto.author.AuthorUpdateRequestDto;
import com.example.scheduleprojectver2.lv4.dto.login.LoginAuthorDto;
import com.example.scheduleprojectver2.lv4.service.AuthorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<AuthorResponseDto> createAuthor(
            @Valid @RequestBody AuthorRequestDto requestDto
    ) {
        return new ResponseEntity<>(authorService.createAuthor(requestDto), HttpStatus.CREATED);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginAuthorDto> login(
            @Valid @RequestBody LoginAuthorDto requestDto,
            HttpServletRequest request
    ) {
        LoginAuthorDto responseDto = authorService.login(requestDto.getEmail(),requestDto.getPassword(),request);
        String authorEmail = responseDto.getEmail();

        // 로그인 실패시
        if (authorEmail.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist email = " + authorEmail);
        }
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        authorService.logout(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> findById(@PathVariable Long id) {
        AuthorResponseDto authorResponseDto = authorService.findById(id);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }

    // 업데이트
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateAuthor(@PathVariable Long id,
                                             @Valid @RequestBody AuthorUpdateRequestDto updateRequestDto) {
        authorService.updateAuthor(id, updateRequestDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
