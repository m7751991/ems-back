package com.example.educationalsystembackend.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Exam {                   //考试实体类
    private List<String> courseList;  //课程列表
    private List<String> gradeList;   //班级列表
    private String id;                //课程号
    private String course;            //课程
    private float credit;             //学分
    private String grade;             //班级
    private String teacher;           //任课老师
    private String supervisor;        //监考老师
    private String classroom;         //教室
    private int number;               //人数
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;                //日期
    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    private Date start;               //开始时间
    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    private Date end;                 //结束时间
}
