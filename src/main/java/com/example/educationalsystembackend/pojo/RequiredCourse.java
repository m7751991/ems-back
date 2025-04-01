package com.example.educationalsystembackend.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequiredCourse {               //必修课实体类
    private List<String> requiredGradeList; //班级列表
    private String grade;                   //班级
    private String id;                      //课程号
    private String name;                    //课程名
    private String teacher;                 //任课老师
    private int number;                     //人数
    private int week;                       //星期
    private int start;                      //上课节次
    private int end;                        //下课节次
    private String classroom;               //教室
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date from;                      //开始日期
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date to;                        //结束日期
    private int zcFrom;                     //开始周次
    private int zcTo;                       //结束周次
    private Float credit;                   //学分
    private List<Map<String, Object>> children;
}
