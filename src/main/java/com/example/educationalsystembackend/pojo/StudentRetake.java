package com.example.educationalsystembackend.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentRetake {
    private String id;           //课程号
    private String course;       //课程名
    private String grade;        //上课班级
    private String teacher;      //任课老师
    private int from;            //起（周）
    private int to;              //止（周）
    private int week;            //周次
    private int start;           //上课节次
    private int end;             //下课节次
    private String classroom;    //教室
    private int kind;            //课程类型标志
    private int number;          //人数
    private boolean disabled;    //是否可选
    private float credit;        //学分
}
