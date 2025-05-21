package com.example.scheduleprojectver2.lv2.service;

import com.example.scheduleprojectver2.lv2.dto.AuthorRequestDto;
import com.example.scheduleprojectver2.lv2.dto.AuthorResponseDto;
import com.example.scheduleprojectver2.lv2.dto.AuthorUpdateRequestDto;
import com.example.scheduleprojectver2.lv2.entity.Author;
import com.example.scheduleprojectver2.lv2.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorResponseDto createAuthor(AuthorRequestDto requestDto) {
        Author author = new Author(requestDto.getName(),requestDto.getPassword(),requestDto.getEmail());

        Author save = authorRepository.save(author);

        return new AuthorResponseDto(save);
    }

    public AuthorResponseDto findById(Long id) {
        Author findAuthor = authorRepository.findByIdOrElseThrow(id);
        return new AuthorResponseDto(findAuthor.getId(),findAuthor.getName(),findAuthor.getPassword(),findAuthor.getEmail());
    }

    public void deleteAuthor(Long id) {
        Author findAuthor = authorRepository.findByIdOrElseThrow(id);
        authorRepository.delete(findAuthor);
    }

    // 데이터베이스의 상태를 변경시키는 하나의 논리적인 작업
    @Transactional
    public void updateAuthor(Long id, @Valid AuthorUpdateRequestDto updateRequestDto) {
        Author findAuthor = authorRepository.findByIdOrElseThrow(id);
        findAuthor.updateAuthor(updateRequestDto);
    }


}
