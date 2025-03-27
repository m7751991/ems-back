package com.example.educationalsystembackend.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeacherEvaluation {  //教师评价实体类
    private String student;       //学生
    private String grade;         //年级
    private String course;        //课程
    private int evaluation;       //评价
    private String comment;
}
