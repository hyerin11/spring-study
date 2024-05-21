package com.study.springstudy.springmvc.chap04.service;

import com.study.springstudy.springmvc.chap03.dto.ScoreListResponseDto;
import com.study.springstudy.springmvc.chap04.dto.BoardDetailResponseDto;
import com.study.springstudy.springmvc.chap04.dto.BoardListResponseDto;
import com.study.springstudy.springmvc.chap04.dto.BoardWriteRequestDto;
import com.study.springstudy.springmvc.chap04.entity.Board;
import com.study.springstudy.springmvc.chap04.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardMapper mapper;

    // 목록 조회 요청 중간처리
    public List<BoardListResponseDto> findList() {
        List<Board> boardList = mapper.findAll();
        return boardList.stream()
                .map(b -> new BoardListResponseDto(b))
                .collect(Collectors.toList());
    }

    // 등록 요청 중간처리
    public boolean insert(BoardWriteRequestDto dto) {
        Board b = dto.toEntity();
        return mapper.save(b);
    }

    // 삭제 요청 중간처리
    public boolean remove(int boardNo) {
        return mapper.delete(boardNo);
    }

    // 상세 조회 요청 중간처리
    public BoardDetailResponseDto detail(int bno) {
        Board b = mapper.findOne(bno);
        if (b != null) mapper.upViewCount(bno);
        return new BoardDetailResponseDto(b);
    }

}
