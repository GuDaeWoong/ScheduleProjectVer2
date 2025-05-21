package com.example.scheduleprojectver2.lv2.controller;


import com.example.scheduleprojectver2.lv2.dto.AuthorRequestDto;
import com.example.scheduleprojectver2.lv2.dto.AuthorResponseDto;
import com.example.scheduleprojectver2.lv2.dto.AuthorUpdateRequestDto;
import com.example.scheduleprojectver2.lv2.service.AuthorService;
import jakarta.validation.Valid;
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
            @RequestBody AuthorRequestDto requestDto
    ) {
        return new ResponseEntity<>(authorService.createAuthor(requestDto), HttpStatus.CREATED);
    }

    // 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> findById(@PathVariable Long id) {
        AuthorResponseDto authorResponseDto = authorService.findById(id);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateAuthor(@PathVariable Long id,
                                             @Valid @RequestBody AuthorUpdateRequestDto updateRequestDto
                                             ) {
        authorService.updateAuthor(id, updateRequestDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
