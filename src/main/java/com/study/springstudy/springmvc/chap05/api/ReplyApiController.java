package com.study.springstudy.springmvc.chap05.api;

import com.study.springstudy.springmvc.chap05.dto.response.ReplyDetailDto;
import com.study.springstudy.springmvc.chap05.entity.Reply;
import com.study.springstudy.springmvc.chap05.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/replies")
@RequiredArgsConstructor
public class ReplyApiController {

    private final ReplyService replyService;

    // 댓글 목록 조회 요청
    //URL : /api/v1/replies/원본 글 번호  -  GET -> 목록 조회
    //@PathVariable : URL에 붙어있는 변수값을 읽는 아노테이션
    //http://localhost:8383/api/v1/replies/3 경우 콘솔창에서 3을 받아온다!

    @GetMapping("/{bno}")
    public ResponseEntity<?> list(@PathVariable long bno){

        List<ReplyDetailDto> replies = replyService.getReplies(bno);

        return ResponseEntity
                .ok()
                .body(replies);

        //System.out.println("bno = " + bno);

    }

}
