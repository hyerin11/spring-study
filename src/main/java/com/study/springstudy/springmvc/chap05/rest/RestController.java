package com.study.springstudy.springmvc.chap05.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/rest") //rest로 시작함

@Controller
public class RestController {
    @GetMapping("/hello")
    @ResponseBody //서버가 클라이언트에게 순수한 데이터를 전달할 때 사용
        public String hello(){
            return "하잉"; //responsebody가 있어서 화면에 하잉이 출력됨. 없었으면 하잉.jsp가 실행되었을 것.
    }

    @GetMapping("/hobby")
    @ResponseBody
    public List<String> hobby(){
        List<String> hobbies = List.of("태권도", "댄스");
        return hobbies;
    }
}