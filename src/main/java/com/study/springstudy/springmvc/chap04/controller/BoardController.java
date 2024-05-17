package com.study.springstudy.springmvc.chap04.controller;

import com.study.springstudy.springmvc.chap03.repository.ScoreRepository;
import com.study.springstudy.springmvc.chap04.dto.BoardDto;
import com.study.springstudy.springmvc.chap04.entity.Board;
import com.study.springstudy.springmvc.chap04.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {

    // 1. 목록 조회 요청 (/board/list : GET)

    // 2. 게시글 쓰기 양식 화면 열기 요청 (/board/write : GET)

    // 3. 게시글 등록 요청 (/board/write : POST)
    // -> 목록조회 요청 리다이렉션

    // 4. 게시글 삭제 요청 (/board/delete : GET)
    // -> 목록조회 요청 리다이렉션

    // 5. 게시글 상세 조회 요청 (/board/detail : GET)

    ///--------------------------------------///

    // 의존 객체 설정
    private final BoardRepository repository;
    // => 들어갈 곳이 있어야 하니까 새로 BoardJdbcRepository를 만들어서 @Repository로 연결해준다!

    //1. 목록 조회 요청
    @GetMapping("/board/list")
   public String list(Model model){
        System.out.println("/board/list : GET!");
        return "board/list";
    }

    //2. 화면 열기 요청
    @GetMapping("/board/write")
    public String write(Model model){
        System.out.println("/board/write : GET!");
        return "board/write";
    }

    //3. 게시글 등록 요청
    @PostMapping("/board/write")
    public String register(BoardDto dto){
        System.out.println("/board/write : POST!");
        System.out.println("dto = " + dto);

        //데이터베이스에 저장하기
        Board board = new Board(dto);
        repository.save(board);

        return "redirect:/board/list";

    }

}