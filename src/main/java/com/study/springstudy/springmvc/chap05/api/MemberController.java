package com.study.springstudy.springmvc.chap05.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
@Slf4j
public class MemberController {
    @GetMapping("/hello")
    public String hello(){
        return "board/write"; //.jsp파일을 포워딩해라
    }
}
