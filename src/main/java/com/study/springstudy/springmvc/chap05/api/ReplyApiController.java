package com.study.springstudy.springmvc.chap05.api;

import com.study.springstudy.springmvc.chap05.dto.request.ReplyPostDto;
import com.study.springstudy.springmvc.chap05.dto.response.ReplyDetailDto;
import com.study.springstudy.springmvc.chap05.entity.Reply;
import com.study.springstudy.springmvc.chap05.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/replies")
@RequiredArgsConstructor
@Slf4j
public class ReplyApiController {

    private final ReplyService replyService;

    // 댓글 목록 조회 요청
    //URL : /api/v1/replies/원본 글 번호  -  GET -> 목록 조회
    //@PathVariable : URL에 붙어있는 변수값을 읽는 아노테이션
    //http://localhost:8383/api/v1/replies/3 경우 콘솔창에서 3을 받아온다!

    @GetMapping("/{bno}")
    public ResponseEntity<?> list(@PathVariable long bno){

        if(bno==0){
            String message = "글 번호는 0번이 될 수 없습니다.";
            log.warn(message);
            return ResponseEntity
                    .badRequest()
                    .body(message);
        }

        log.info("/api/v1/replies/{}: GET", bno); //bno가 {}안으로 들어감

        //System.out.println("bno = " + bno);
        // >> 로그 찍어보는 것은 좋은데 System.out으로 출력하는 것은 성능상 좋지 않음
        // 그리고 언제 봤는지 시간정보가 들어가야 한다.

        List<ReplyDetailDto> replies = replyService.getReplies(bno);
        log.debug("first reply:{}", replies.get(0));

        return ResponseEntity
                .ok()
                .body(replies);

    }

    //댓글 생성 요청
    //@RequestBody : 클라이언트가 전송한 데이터를 json으로 받아서 파싱
    @PostMapping
    public ResponseEntity<?> posts(@RequestBody ReplyPostDto dto){
        log.info("/api/v1/replies : POST!");
        log.debug("parameter : {}", dto);

        boolean flag = replyService.register(dto);

        if (!flag) return ResponseEntity
                .internalServerError()
                .body("댓글 등록 실패!");

        return ResponseEntity
                .ok()
                .body(replyService.getReplies(dto.getBno()));

    }

}
