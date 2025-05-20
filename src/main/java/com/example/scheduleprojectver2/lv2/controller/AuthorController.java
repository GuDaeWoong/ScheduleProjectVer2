package com.example.scheduleprojectver2.lv2.controller;


import com.example.scheduleprojectver2.lv2.dto.AuthorResponseDto;
import com.example.scheduleprojectver2.lv2.dto.CreateAuthorRequestDto;
import com.example.scheduleprojectver2.lv2.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {


    private final AuthorService authorService;

    // 생성
    @PostMapping
    public ResponseEntity<AuthorResponseDto> createAuthor(
            @RequestBody CreateAuthorRequestDto requestDto
    ) {
        return new ResponseEntity<>(authorService.createAuthor(requestDto), HttpStatus.CREATED);
    }

    // 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> findById(@PathVariable Long id) {
        AuthorResponseDto authorResponseDto = authorService.findById(id);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
