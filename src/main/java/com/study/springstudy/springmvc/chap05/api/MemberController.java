package com.study.springstudy.springmvc.chap05.api;

import com.study.springstudy.springmvc.chap05.dto.request.SignUpDto;
import com.study.springstudy.springmvc.chap05.entity.Member;
import com.study.springstudy.springmvc.chap05.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/members")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입 양식 열기
    @GetMapping("/sign-up")
    public void signUp() {
        log.info("/members/sign-up GET : forwarding to sign-up.jsp");
        // return "members/sign-up";
    }

    //회원가입 요청 처리
    @PostMapping("/sign-up")
    public String signUp(SignUpDto dto){
        log.info("/members.sign-up POST");
        log.debug("parameter: {}", dto);

        boolean flag = memberService.join(dto);

        return flag ? "redirect:/board/list" : "redirect:/members/sign-up";
    }
}