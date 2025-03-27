package com.example.educationalsystembackend.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Schedule {
    private String course;
    private String teacher;
    private String classroom;
    private int week;
    private int start;
    private int end;
    private int kind;
    private int from;
    private int to;
}
