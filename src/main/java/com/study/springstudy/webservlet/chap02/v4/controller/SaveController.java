package com.study.springstudy.webservlet.chap02.v4.controller;

import com.study.springstudy.webservlet.MemberMemoryRepo;
import com.study.springstudy.webservlet.MyModel;
import com.study.springstudy.webservlet.entity.Member;

import java.util.Map;

public class SaveController implements ControllerV4 {

    private MemberMemoryRepo repo = MemberMemoryRepo.getInstance();

    @Override
    public String process(Map<String, String> paramMap, MyModel model) {
        String userName = paramMap.get("account");
        String password = paramMap.get("password");
        String account = paramMap.get("userName");

        Member member = new Member(account, password, userName);

        repo.save(member);

        return "redirect:/chap02/v4/show";
    }
}
