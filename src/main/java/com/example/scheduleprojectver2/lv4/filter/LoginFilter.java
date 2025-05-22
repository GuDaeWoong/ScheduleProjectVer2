package com.example.scheduleprojectver2.lv4.filter;

import com.example.scheduleprojectver2.lv4.util.Const;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;
import com.example.scheduleprojectver2.lv4.exception.LoginException;

import java.io.IOException;

@Slf4j
public class LoginFilter  implements Filter {

    // 인증을 하지 않아도될 URL Path 배열
    private static final String[] WHITE_LIST = {"/author/login", "/author/signup", "/author/logout"};

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        // 다양한 기능을 사용하기 위해 다운 캐스팅
        HttpServletRequest httpRequest =(HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI(); // 들어온 URI

        if (!isWhiteList(requestURI)) {// 화이트 리스트에 속하지 않은 경우
            // 로그인 확인 -> 로그인하면 session에 값이 저장되어 있다는 가정.
            // 세션이 존재하면 가져온다. 세션이 없으면 session = null
            HttpSession session = httpRequest.getSession(false);
            // 로그인하지 않은 사용자인 경우
            if (session == null || session.getAttribute(Const.LOGIN_AUTHOR) == null) {
                throw new LoginException();
            }
        }
        chain.doFilter(request, response);
    }

    // 로그인 여부를 확인하는 URL인지 체크하는 메서드
    private boolean isWhiteList(String requestURI) {
        // request URI가 whiteListURL에 포함되는지 확인 포함되면 true 반환
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }

}
