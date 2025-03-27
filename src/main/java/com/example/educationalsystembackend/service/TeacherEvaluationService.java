package com.example.educationalsystembackend.service;

import com.example.educationalsystembackend.pojo.TeacherEvaluation;

import java.util.List;

public interface TeacherEvaluationService {

    List<TeacherEvaluation> queryAllTeacherEvaluation(String teacher, int num, int size);

    int queryAllTeacherEvaluationCount(String teacher);

    List<TeacherEvaluation> queryTeacherEvaluation(String teacher, String course, String grade, int num, int size);

    int queryTeacherEvaluationCount(String teacher, String course, String grade);
}
