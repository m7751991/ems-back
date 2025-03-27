package com.example.educationalsystembackend.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScoreStatus {
    private String id;
    private String course;
    private int kind;
    private int score;
    private float credit;
    private int usual;
    private int experiment;
    private int exam;
    private String teacher;
    private List<Integer> scoreList;
}
