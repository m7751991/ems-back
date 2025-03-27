package com.example.educationalsystembackend.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Score {
    private String id;
    private String student;
    private String course;
    private String name;
    private int credit;
    private String teacher;
    private int kind;
    private int usual;
    private int exam;
    private int experiment;
    private int total;
    private int usualRate;
    private int experimentRate;
    private int examRate;
}
