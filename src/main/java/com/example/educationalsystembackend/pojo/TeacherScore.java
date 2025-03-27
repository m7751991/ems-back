package com.example.educationalsystembackend.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeacherScore {
    private String id;
    private String course;
    private String grade;
    private int credit;
    private int number;
    private int kind;
    private boolean score;
    private String student;
    private Integer usual;
    private Integer exam;
    private Integer experiment;
    private int total;
}
