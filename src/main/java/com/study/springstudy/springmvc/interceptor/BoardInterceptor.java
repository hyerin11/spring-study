package com.study.springstudy.springmvc.interceptor;

import com.study.springstudy.springmvc.chap04.entity.Board;
import com.study.springstudy.springmvc.chap04.mapper.BoardMapper;
import com.study.springstudy.springmvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.study.springstudy.springmvc.util.LoginUtil.*;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class BoardInterceptor implements HandlerInterceptor {


    private final BoardMapper boardMapper;

    // preHandle을 구현하여
    // 로그인을 안한 회원은 글쓰기, 글수정, 글삭제 요청을 거부할 것!
    // 거부하고 로그인 페이지로 리다이렉션할 것!
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();


        // 요청 URL
        String redirectUri = request.getRequestURI();

        if (!isLoggedIn(session)) {
            log.info("origin: {}", redirectUri);
            response.sendRedirect("/members/sign-in?message=login-required&redirect=" + redirectUri);
            return false;
        }


        //삭제요청이 들어오면 서버에서 한번 더 관리자인지 or 자신이 쓴 글 인지 체크한다
        // 1. 관리자인가
        if (isAdmin(session)) {
            return true;
        }


        // 2. 내가 쓴 글 아닌가
        // 삭제 요청인지
        if (redirectUri.equals("/board/delete")){
            //내가 쓴 글이 아닌지?
            // 1) 현재 삭제하려는 글의 글쓴이 계정명과
            // -> db에서 조회해보면 된다. -> 글 번호가 필요하다(bno)
            int bno = Integer.parseInt(request.getParameter("bno")); // 글 번호 구하기
            Board board = boardMapper.findOne(bno); //계정명을 조회한 후
            String boardAccount = board.getAccount(); //계정을 가지고 와라!

            // 2) 현재 로그인한 회원의 계정명을 구해서
            String loggedInUserAccount = LoginUtil.getLoggedInUserAccount(session);

            // 3) 대조해보는 작업이 필요
            if (!isMine(boardAccount, loggedInUserAccount)) {
                response.setStatus(403);
                response.sendRedirect("/access-deny?message=authorization");
                return false;

            }
        }


        return true;
    }
}