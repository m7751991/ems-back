package com.example.educationalsystembackend.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeacherCourse {   //教师课程实体类
    private String id;         //课程号
    private String course;     //课程名
    private String grade;      //上课班级
    private int credit;        //学分
    private int week;          //周次
    private int start;         //上课节次
    private int end;           //下课节次
    private int number;        //选课人数
    private String classroom;  //教室
    private int kind;          //课程类型
    private int from;          //开始日期
    private int to;            //结束日期
}
