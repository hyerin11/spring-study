package com.study.springstudy.springmvc.chap04.repository;

import com.study.springstudy.springmvc.chap03.entity.Score;
import com.study.springstudy.springmvc.chap04.dto.BoardDto;
import com.study.springstudy.springmvc.chap04.entity.Board;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@RequiredArgsConstructor
public class BoardJdbcRepository implements BoardRepository {

    private final JdbcTemplate template;

    @Override
    public List<Board> findAll() {
        return List.of();
    }

    @Override
    public Board findOne(int boardNo) {
        return null;
    }

    @Override
    public boolean save(BoardDto dto) {
        String sql = "INSERT INTO tbl_board " +
                "(title, content, writer) " +
                "VALUES (?, ?, ?)";
        return template.update(sql,
                dto.title(), dto.content(), dto.writer();
    }

    @Override
    public List<Score> findAll(String sort) {
        String sql = "SELECT * FROM tbl_board " + orderByStatement(sort);
        return template.query(sql, (rs, n) -> new Score(rs));
    }

    private String orderByStatement(String sort) {
        String sortSql = "ORDER BY ";
        switch (sort) {
            case "title":
                sortSql += "title";
                break;
            case "content":
                sortSql += "content";
                break;
            case "writer":
                sortSql += "writer";
                break;
        }
        return sortSql;
    }

    @Override
    public boolean delete(int boardNo) {
        return false;
    }
}
