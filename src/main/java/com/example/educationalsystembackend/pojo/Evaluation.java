package com.example.educationalsystembackend.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Evaluation {       //评价实体类
    private String student;     //学生
    private String course;      //课程
    private String teacher;     //任课老师
    private int evaluation;     //评价
    private String comment;
}
