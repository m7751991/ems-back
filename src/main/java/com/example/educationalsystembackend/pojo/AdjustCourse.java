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
public class AdjustCourse {
    private Long id;
    private List<Long> idList;
    private String course;
    private String name;
    private String teacher;
    private List<String> fromList;
    private List<String> toList;
    private List<String> weekList;
    private List<String> lessonList;
    private String from = "";
    private String to = "";
    private String week = "";
    private String lesson = "";
    private String reason;
    private int result;
    private List<Integer> resultList;
}
