package com.study.springstudy.springmvc.chap05.service;

import com.study.springstudy.springmvc.chap05.dto.request.LoginDto;
import com.study.springstudy.springmvc.chap05.dto.request.SignUpDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("회원가입을 하면 비밀번호가 인코딩된다")
    void joinTest() {
        //given
        SignUpDto dto = SignUpDto.builder()
                .account("kitty11")
                .password("kkk123!")
                .email("sanrio11@gmail.com")
                .name("헬로키티")
                .build();

        //when
        boolean flag = memberService.join(dto);
        //then
        Assertions.assertTrue(flag);
    }



    @Test
    @DisplayName("id가 존재하지 않는 경우를 테스트한다.")
    void noAccTest() {
        //given
        LoginDto dto = LoginDto.builder()
                .account("akfjlk")
                .password("kjlksd")
                .build();
        //when
        LoginResult result = memberService.authenticate(dto, ra);
        //then
        assertEquals(LoginResult.NO_ACC, result);
    }

    @Test
    @DisplayName("Pw가 존재하지 않는 경우를 테스트한다.")
    void noPwTest() {
        //given
        LoginDto dto = LoginDto.builder()
                .account("kitty11")
                .password("kjlksd")
                .build();
        //when
        LoginResult result = memberService.authenticate(dto, ra);
        //then
        assertEquals(LoginResult.NO_PW, result);
    }

    @Test
    @DisplayName("로그인이 성공하는 경우를 테스트한다")
    void successTest() {
        //given
        LoginDto dto = LoginDto.builder()
                .account("kitty11")
                .password("kkk123!")
                .build();
        //when
        LoginResult result = memberService.authenticate(dto, ra);
        //then
        assertEquals(LoginResult.SUCCESS, result);
    }


}