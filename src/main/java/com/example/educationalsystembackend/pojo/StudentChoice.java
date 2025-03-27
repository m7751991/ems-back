package com.example.educationalsystembackend.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentChoice {
    private String id;
    private String name;
    private String teacher;
    private String week;
    private int start;
    private int end;
    private String classroom;
    private int number;
    private int flag;
    private boolean disabled;    //是否可选
    private int from;
    private int to;
    private float credit;
}
