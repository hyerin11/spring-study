package com.study.springstudy.springmvc.util;

import com.study.springstudy.springmvc.chap05.dto.response.LoginUserInfoDto;
import com.study.springstudy.springmvc.chap05.entity.Auth;

import javax.servlet.http.HttpSession;

public class LoginUtil {

    public static final String LOGIN = "login";

    // 로그인 여부 확인
    public static boolean isLoggedIn(HttpSession session) {
        return session.getAttribute(LOGIN) != null;
    }

    // 로그인한 회원의 계정명 얻기
    public static String getLoggedInUserAccount(HttpSession session) {
        LoginUserInfoDto loggedInUser = getLoggedInUser(session);
        return loggedInUser != null ? loggedInUser.getAccount() : null;
    }

    //로그인 정보 가져와서
    public static LoginUserInfoDto getLoggedInUser(HttpSession session) {
        return (LoginUserInfoDto) session.getAttribute(LOGIN);
    }

    //관리자인자 확인하는 코드
    //로그인한 사람의 정보를 가져오고
    public static boolean isAdmin(HttpSession session) {
        LoginUserInfoDto loggedInUser = getLoggedInUser(session);
        Auth auth = null;
        //정보가 있다면
        if (isLoggedIn(session)) {
            auth = Auth.valueOf(loggedInUser.getAuth());
        }//없다면
        return auth == Auth.ADMIN;
    }


    public static boolean isMine(String boardAccount, String loggedInUserAccount) {
    return boardAccount.equals(loggedInUserAccount);
    }
}
