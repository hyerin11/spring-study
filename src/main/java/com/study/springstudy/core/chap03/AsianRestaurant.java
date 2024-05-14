package com.study.springstudy.core.chap03;

public class AsianRestaurant implements Restaurant {

    private Chef chef;

    private Course course;

    //생성자
    public AsianRestaurant(Chef chef, Course course) {
        this.chef = chef;
        this.course = course;
    }

    public void order(){
        System.out.println("아시안 요리를 주문합니다.");
        course.combineMenu();
        chef.cook();
    }
}
