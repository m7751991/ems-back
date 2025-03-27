package com.example.educationalsystembackend.service;

import com.example.educationalsystembackend.pojo.AssignExam;

import java.util.List;

public interface AssignExamService {

    List<AssignExam> queryALLAssignExam(int num, int size);

    int queryALLAssignExamCount();

    List<AssignExam> queryAssignExam(String course, int num, int size);

    int queryAssignExamCount(String course);
}
