package com.study.springstudy;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.study.springstudy") //안써도 됨.
public class Main {
    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }
}

