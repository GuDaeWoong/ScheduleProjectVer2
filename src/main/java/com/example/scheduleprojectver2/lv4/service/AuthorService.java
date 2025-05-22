package com.example.scheduleprojectver2.lv4.service;

import com.example.scheduleprojectver2.lv4.dto.author.AuthorRequestDto;
import com.example.scheduleprojectver2.lv4.dto.author.AuthorResponseDto;
import com.example.scheduleprojectver2.lv4.dto.author.AuthorUpdateRequestDto;
import com.example.scheduleprojectver2.lv4.dto.login.LoginAuthorDto;
import com.example.scheduleprojectver2.lv4.entity.Author;
import com.example.scheduleprojectver2.lv4.repository.AuthorRepository;
import com.example.scheduleprojectver2.lv4.util.Const;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorResponseDto createAuthor(AuthorRequestDto requestDto) {
        Author author = new Author(requestDto.getName(), requestDto.getPassword(), requestDto.getEmail());

        Author save = authorRepository.save(author);

        return new AuthorResponseDto(save);
    }

    public AuthorResponseDto findById(Long id) {

        Optional<Author> findAuthor = authorRepository.findById(id);
        if (findAuthor.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
        Author findByAuthor = findAuthor.get();
        return new AuthorResponseDto(findByAuthor.getId(), findByAuthor.getName(), findByAuthor.getPassword(), findByAuthor.getEmail());
    }

    public LoginAuthorDto findByEmail(String authorEmail) {
        Optional<Author> findAuthor = authorRepository.findByEmail(authorEmail);
        if (findAuthor.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist Email = " + authorEmail);
        }
        return new LoginAuthorDto(findAuthor.get().getEmail(),findAuthor.get().getPassword());
    }

    public void deleteAuthor(Long id) {

        Optional<Author> findAuthor = authorRepository.findById(id);
        if (findAuthor.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        authorRepository.delete(findAuthor.get());
    }


    // 데이터베이스의 상태를 변경시키는 하나의 논리적인 작업
    @Transactional
    public void updateAuthor(Long id, @Valid AuthorUpdateRequestDto updateRequestDto) {
        Optional<Author> findAuthor = authorRepository.findById(id);
        if (findAuthor.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
        findAuthor.get().updateAuthor(updateRequestDto);
    }

    @Transactional
    public LoginAuthorDto login(String email, String password, HttpServletRequest request) {
        Optional<Author> findAuthor = authorRepository.findByEmail(email);
        // 이메일이 있는지 검증
        if (findAuthor.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist email = " + email);
        }
        // 이메일과 비밀번호가 일치하지 않을 경우 HTTP Status code 401을 반환
        if (!findAuthor.get().getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        // 로그인 성공시 로직
        HttpSession session = request.getSession();

        // Session에 로그인 회원 정보를 저장한다.
        // key: Const.LOGIN_USER
        // value :  findAuthor.get()
        session.setAttribute(Const.LOGIN_AUTHOR, findAuthor.get());
        // 저장된 session을 가져온다
        Author sessionAuthor  = (Author) session.getAttribute(Const.LOGIN_AUTHOR);

        return new LoginAuthorDto(sessionAuthor.getEmail(),sessionAuthor.getPassword());
    }

    @Transactional
    public void logout(HttpServletRequest request) {
        // 로그인하지 않으면 HttpSession이 null로 반환된다.
        HttpSession session = request.getSession(false);
        // 세션이 존재하면 -> 로그인이 된 경우
        if(session != null) {
            session.invalidate(); // 해당 세션(데이터)을 삭제한다.
        }
    }
}
