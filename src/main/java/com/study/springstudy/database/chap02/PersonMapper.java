package com.study.springstudy.database.chap02;

import com.study.springstudy.database.chap01.Person;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PersonMapper {

    // 사람 전체 조회
    List<Person> fondAll();

    // 사람 개별 조회
    Person findOne(long id);

    // 사람 등록
    boolean save(Person p);

    // 사람 수정
    boolean update(Person newPerspn);

    // 사람 삭제
    boolean delete(long id);

    // 사람들의 이름만 조회
    List<String> findNames();

    //사람들의 총 숫자만 조회
    int count();

    List<Person> findAll();
}
