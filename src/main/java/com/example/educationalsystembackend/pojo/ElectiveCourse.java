package com.example.educationalsystembackend.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ElectiveCourse {    //选修课
    private String id;           //课程号
    private String name;         //课程名
    private String teacher;      //任课老师
    private int week;            //周次
    private int start;           //上课节次
    private int end;             //下课节次
    private String classroom;    //教室
    private boolean flag;        //公开标志
    private boolean score;       //成绩标志
    private int kind;            //课程类型标志
    private int number;          //人数
    private int choiceNumber;    //选课人数
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date from;           //开始日期
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date to;             //结束日期
    private int zcFrom;          //起始周次
    private int zcTo;            //结束周次
    private Float credit;        //学分
}
