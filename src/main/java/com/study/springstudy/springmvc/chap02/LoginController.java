package com.study.springstudy.springmvc.chap02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/hw/*") //공통적인 부분
public class LoginController {
    /*
        1번요청: 로그인 폼 화면 열어주기
        - 요청 URL : /hw/s-login-form : GET
        - 포워딩 jsp파일 경로:  /WEB-INF/views/mvc/s-form.jsp
        - html form: 아이디랑 비번을 입력받으세요.
*/
    @GetMapping(value = "/s-login-form")
    public String login() {
        return "mvc/s-form"; // 이 경로 안에 파일 있어야 함.
    }

    /*
        2번요청: 로그인 검증하기
        - 로그인 검증: 아이디를 grape111이라고 쓰고 비번을 ggg9999 라고 쓰면 성공
        - 요청 URL : /hw/s-login-check : POST
        - 포워딩 jsp파일 경로:  /WEB-INF/views/mvc/s-result.jsp
        - jsp에게 전달할 데이터: 로그인 성공여부, 아이디 없는경우, 비번 틀린경우
*/
    @PostMapping(value = "/s-login-check")
    public String result(String id, String pw, Model model) {

        //값 받아오는지 확인하기
        System.out.println("id = " + id);
        System.out.println("pw = " + pw);

        String message;
        if(id.equals("grape111")){
            if(pw.equals("ggg9999")){
                //sucess!
                message = "sucess";
            }
            else{
                //pw 입력 실패!
                message = "f-pw";
            }
        }else{
            //id없는 경우!
            message = "f-id";
        }

        model.addAttribute("result", message);

        return "/mvc/s-result";
    }




    }



