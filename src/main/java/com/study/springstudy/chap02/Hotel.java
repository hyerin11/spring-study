package com.study.springstudy.chap02;

/*
 * @solution
 * 1. 먼저 DIP를 해결하기 위해 구체적인 객체 대신 추상적인 역할에 의존하게 코드 개선 => restaurant / chef로 개선
 *
 * @problem
 * 2. 추상화를 했지만 여전히 의존객체를 바꾸려면 코드를 직접 변경해야 한다.
 *  */


public class Hotel {

    //1-1)레스토랑 => 여기서 아시안인지 웨스턴인지 수정해줘야 했음. hotel에서
    private Restaurant restaurant = new AsianRestaurant();


    //1-2)헤드쉐프
    private Chef headChef = new KimuraChef(); //=> 의존적이다

    //1. 호텔을 소개하는 기능
    public void inform() {
        System.out.printf("우리 호텔의 레스토랑은 %s입니다. " + "그리고 헤드쉐프는 %s입니다.\n"
                , restaurant.getClass().getSimpleName()
                , headChef.getClass().getSimpleName());

        restaurant.order();
    }

}
