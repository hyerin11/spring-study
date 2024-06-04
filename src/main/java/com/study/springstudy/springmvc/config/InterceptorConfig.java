package com.study.springstudy.springmvc.config;

import com.study.springstudy.springmvc.interceptor.AfterLoginInterceptor;
import com.study.springstudy.springmvc.interceptor.AutoLoginInterceptor;
import com.study.springstudy.springmvc.interceptor.BoardInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 만들어 놓은 인터셉터들을 스프링 컨텍스트에 등록하는 설정 파일
@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final AfterLoginInterceptor afterLoginInterceptor;
    private final BoardInterceptor boardInterceptor;
    private final AutoLoginInterceptor autoLoginInterceptor;

    // 설정 메서드
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry
                .addInterceptor(afterLoginInterceptor)
                // 해당 인터셉터가 동작할 URL을 설정
                .addPathPatterns("/members/sign-up", "/members/sign-in")
        ;

        //게시판 인터셉터 등록
        registry
                .addInterceptor(boardInterceptor)
                .addPathPatterns("/board/*") //전체 중에서
                .excludePathPatterns("/board/list", "/board/detail",
                        "/board/like", "/board/dislike")     //이거는 안 막을게
        ;

        //자동로그인 인터셉터 등록
        registry
                .addInterceptor(autoLoginInterceptor)
                .addPathPatterns("/**"); //어디로든 들어와도 검사해서 처리해준다

    }


}
