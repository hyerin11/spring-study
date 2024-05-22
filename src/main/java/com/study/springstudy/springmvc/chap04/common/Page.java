package com.study.springstudy.springmvc.chap04.common;

import lombok.*;

@Getter @ToString
@EqualsAndHashCode
@AllArgsConstructor

public class Page {

    private  int pageNo; //클라이언트가 요청한 페이지 번호
    private int amount; //클라이언트가 요청한 한 페이지당 보여질 게시물 수

    //기본 세팅 - 아무것도 입력하지 않았을 때 나오는 세팅
    public Page(){ 
        this.pageNo = 1;
        this.amount = 6;
    }

    //http://localhost:8383/board/list?pageNo=-29
    // 페이지 -29라고 했을 경우 1페이지를 보여준다.
    public void setPageNo(int pageNo) {
        if(pageNo<1 || pageNo>Integer.MAX_VALUE){
            this.pageNo = 1;
            return;
        }
        this.pageNo = pageNo;
    }

    public void setAmount(int amount) {
        if (amount < 6 || amount > 60) {
            this.amount = 6;
            return;
        }
        this.amount = amount;
    }

    /*
    * <규칙 찾기>

    * 만약 한 페이지에 게시물을 10개씩 렌더링한다면
    * 1페이지 -> LIMIT 0, 10
    * 2페이지 -> LIMIT 10, 10
    * 3페이지 -> LIMIT 20, 10
    *
    * 만약 한 페이지에 게시물을 6개씩 렌더링한다면
     * 1페이지 -> LIMIT 0, 6
     * 2페이지 -> LIMIT 6, 6
     * 3페이지 -> LIMIT 12, 6
     *
     *
     * 따라서, 한 페이지에 게시물 N개씩 렌더링 한다면
    * 1페이지 -> LIMIT 0, N
     * 2페이지 -> LIMIT 6, N
     * 3페이지 -> LIMIT 12, N
     * M페이지 -> LIMIT(M-1) * N, N
     *
    * */
    public int getPageStart(){
        return (this.pageNo - 1) * this.amount;
    }
}
