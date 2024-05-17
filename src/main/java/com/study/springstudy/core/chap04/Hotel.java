package com.study.springstudy.core.chap04;

/*
 * @problem
 * 2. 추상화를 했지만 여전히 의존객체를 바꾸려면 코드를 직접 변경해야 한다.
 *
 * @solution
 * 1. 객체 생성의 제어권을 이 클래스에서 다른 클래스로 이전한다.🌟
 * ex) new생성자(); => 이 문법에 담당 클래스를 정해 몰아서 수행시킨다.
 * 2. 호텔 객체 생성 시 반드시 의존객체를 전달하도록 강요
 *
 *    ✨🙊중요!✨
 * // 제어의 역전(IoC) : 객체 생성의 제어권을 외부로 넘긴다. =>  hotelManager한테 넘김
   // 의존성 주입(DI) : 외부에서 생성된 객체를 주입받는 개념
 *
 *
 *  */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component  //hOTEL의 객체 생성 제어권을 스프링에게 넘긴다🔅⚡✨

// 만약에 해당 클래스의 생성자가 단 한개뿐이라면 자동으로 @Autowired를 붙여준다
public class Hotel {
    public Restaurant getRestaurant() {
        return restaurant;
    }

//    @Autowired
//    public void setRestaurant(Restaurant restaurant) {
//        this.restaurant = restaurant;
//    }

//    @Autowired
//    public Chef getHeadChef() {
//        return headChef;
//    }

//    public void setHeadChef(Chef headChef) {
//        this.headChef = headChef;
//    }

    //1-1)레스토랑
//    @Autowired //자동으로 객체를 주입(결합)해줘!
    private Restaurant restaurant;


    //1-2)헤드쉐프
//    @Autowired
    private Chef headChef; //=> 의존적이다

    public Hotel(@Qualifier("western") Restaurant restaurant, Chef headChef) {
        this.restaurant = restaurant;
        this.headChef = headChef;
    }

    //1. 호텔을 소개하는 기능
    public void inform() {
        System.out.printf("우리 호텔의 레스토랑은 %s입니다. " + "그리고 헤드쉐프는 %s입니다.\n"
                , restaurant.getClass().getSimpleName()
                , headChef.getClass().getSimpleName());

        restaurant.order();
        headChef.cook();
    }

}
