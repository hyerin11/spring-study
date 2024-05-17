package com.study.springstudy.springmvc.chap04.dto;

public class BoardDto {

    private String title; // 글제목
    private String content; // 글내용
    private String writer; // 작성자명

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}


