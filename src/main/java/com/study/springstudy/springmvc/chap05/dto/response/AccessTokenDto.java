package com.study.springstudy.springmvc.chap05.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.awt.*;

@Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccessTokenDto { //Map이 아닌 Dto로 받을 때!

    @JsonProperty("access_token") //카카오 지정
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;

}
