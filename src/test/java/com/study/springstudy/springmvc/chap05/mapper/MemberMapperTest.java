package com.study.springstudy.springmvc.chap05.mapper;

import com.study.springstudy.springmvc.chap05.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberMapperTest {

    @Autowired
    MemberMapper memberMapper;

    @Test
    @DisplayName("회원가입에 성공해야 한다")
    void saveTest() {
        //given
        Member member = Member.builder()
                .account("riin")
                .password("abc123!")
                .name("녜린")
                .email("hodolin@naver.com")
                .build();
        //when
            boolean flag = memberMapper.save(member);
        //then
        assertTrue(flag);
    }

    @Test
    @DisplayName("riin계정명을 조회하면 회원의 이름은 녜린이어야 한다")
    void findOne() {
        //given
        String acc = "riin";
        //when
        Member foundMember = memberMapper.findOne(acc);
        //then
        assertEquals("녜린", foundMember.getName());
    }

    
    @Test
    @DisplayName("계정명이 riin인 회원은 중복확인 결과가 true이다")
    void existsTest() {
        //given
        String acc = "riin";
        //when
        boolean flag = memberMapper.existsById("account", acc);
        //then
        assertTrue(flag);
    }



    @Test
    @DisplayName("계정명이 newjeans인 회원은 중복확인 결과가 false이다.")
    void existsTest2() {
        //given
        String acc = "newjeans";
        //when
        boolean flag = memberMapper.existsById("account", acc);
        //then
        assertFalse(flag);
    }





}