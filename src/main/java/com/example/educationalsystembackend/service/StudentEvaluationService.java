package com.example.educationalsystembackend.service;

import com.example.educationalsystembackend.pojo.StudentEvaluation;

import java.util.List;

public interface StudentEvaluationService {

    List<StudentEvaluation> queryStudentEvaluationList(String student);

    void addEvaluation(String student, String course, String teacher, int evaluation, String comment);

    List<StudentEvaluation> searchStudentEvaluation(String student, String course, String teacher);
}
