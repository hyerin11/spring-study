package com.study.springstudy.database.chap01;

import lombok.*;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private long id;
    private String personName;
    private int personAge;
}
