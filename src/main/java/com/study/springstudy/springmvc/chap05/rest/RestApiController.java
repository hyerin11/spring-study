package com.study.springstudy.springmvc.chap05.rest;

import com.study.springstudy.database.chap01.Person;
import com.study.springstudy.springmvc.chap04.common.Page;
import com.study.springstudy.springmvc.chap04.common.PageMaker;
import com.study.springstudy.springmvc.chap04.common.Search;
import com.study.springstudy.springmvc.chap04.dto.BoardListResponseDto;
import com.study.springstudy.springmvc.chap04.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequestMapping("/rest") //rest로 시작함

//@Controller
//@ResponseBody //전부 사용한다면 맨 위에 붙여주면 전부에게 적용됨

@RestController  //@Controller + @ResponseBody 가 합쳐진 것. 여기서 json을 만들어서 클라이언트한테 보냄
@RequiredArgsConstructor
public class RestApiController {

    private final BoardService boardService;

    @GetMapping("/hello")
    //@ResponseBody //서버가 클라이언트에게 순수한 데이터를 전달할 때 사용
        public String hello(){
            return "하잉"; //responsebody가 있어서 화면에 하잉이 출력됨. 없었으면 하잉.jsp가 실행되었을 것.
    }

    @GetMapping("/hobby")
    //@ResponseBody
    public List<String> hobby(){
        List<String> hobbies = List.of("태권도", "댄스");
        return hobbies;
    }

    @GetMapping(value = "/person", produces = "application/json")
    //@ResponseBody
    public Person person(){
        Person p = new Person(100, "핑구", 10);
        return p;
    }

    @GetMapping("/board")
    public Map<String, Object> board() {

        List<BoardListResponseDto> list = boardService.findList(new Search());

        HashMap<String, Object> map = new HashMap<>();

        map.put("page",  new PageMaker(new Page(),
                boardService.getCount(new Search())));

        map.put("articles", list);

        return map;
    }


    /*
         RestController에서 리턴타입을 ResponseEntity를 쓰는 이유

         - 클라이언트에게 응답할 때는 메시지 바디안에 들어 있는 데이터도 중요하지만
         - 상태코드와 헤더정보를 포함해야 한다
         - 근데 일반 리턴타입은 상태코드와 헤더정보를 전송하기 어렵다
     */


}
