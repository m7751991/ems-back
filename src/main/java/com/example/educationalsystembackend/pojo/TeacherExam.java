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
public class TeacherExam {
    private String course;
    private String teacher;
    private String supervisor;
    private String grade;
    private String classroom;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;
    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    private Date start;
    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    private Date end;
}
