package com.study.springstudy.springmvc.chap03.entity;

import com.study.springstudy.springmvc.chap03.dto.ScorePostDto;
import lombok.Getter;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;

// 역할: 데이터베이스의 테이블의 컬럼과 1대1로 매칭되는 필드를 가진 객체
@Getter
public class Score {

    private long stuNum;
    private String stuName;
    private int kor;
    private int eng;
    private int math;
    private int total;
    private double average;
    private Grade grade;


    public Score(ResultSet rs) throws SQLException{
        this.stuNum = rs.getLong("stu_num");
        this.stuName = rs.getString("stu_name");
        this.kor = rs.getInt("kor");
        this.eng = rs.getInt("eng");
        this.math = rs.getInt("math");
        this.total = rs.getInt("total");
        this.average = rs.getDouble("average");
        this.grade = Grade.valueOf(rs.getString("grade"));
    }

    public Score(ScorePostDto dto) {
        this.stuName = dto.getName();
        this.kor = dto.getKor();
        this.eng = dto.getEng();
        this.math = dto.getMath();
        this.total = kor + eng + math;
        this.average = total / 3.0;
        this.grade = calcGrade();
    }

    private Grade calcGrade() {
        if (average >= 90) {
            return Grade.A;
        } else if (average >= 80) {
            return Grade.B;
        } else if (average >= 70) {
            return Grade.C;
        } else if (average >= 60) {
            return Grade.D;
        } else {
            return Grade.F;
        }
    }


    public void setStuNum(long stuNum) {
        this.stuNum = stuNum;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public void setKor(int kor) {
        this.kor = kor;
    }

    public void setEng(int eng) {
        this.eng = eng;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Score{" +
                "stuNum=" + stuNum +
                ", stuName='" + stuName + '\'' +
                ", kor=" + kor +
                ", eng=" + eng +
                ", math=" + math +
                ", total=" + total +
                ", average=" + average +
                ", grade=" + grade +
                '}';
    }
}
