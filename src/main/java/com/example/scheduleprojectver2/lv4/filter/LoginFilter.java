package com.example.scheduleprojectver2.lv4.filter;

import com.example.scheduleprojectver2.lv4.exception.LoginException;
import com.example.scheduleprojectver2.lv4.util.Const;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    // 인증을 하지 않아도될 URL Path 배열
    private static final String[] WHITE_LIST = {"/author/login", "/author/signup", "/author/logout"};

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        // 다양한 기능을 사용하기 위해 다운 캐스팅
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI(); // 들어온 URI

        if (!isWhiteList(requestURI)) {// 화이트 리스트에 속하지 않은 경우
            HttpSession session = httpRequest.getSession(false);
            // 로그인하지 않은 사용자인 경우
            if (session == null || session.getAttribute(Const.LOGIN_AUTHOR) == null) {
                throw new LoginException(HttpStatus.UNAUTHORIZED, "로그인 정보가 유효하지 않습니다. 다시 로그인해주세요.");
            }
        }

        chain.doFilter(request, response);
    }

    // 로그인 여부를 확인하는 URL인지 체크하는 메서드
    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
