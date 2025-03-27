package com.example.educationalsystembackend.service;

import com.example.educationalsystembackend.pojo.StudentExam;

import java.util.List;

public interface StudentExamService {

    List<StudentExam> queryStudentExam(String grade, int num, int size);

    int queryStudentExamCount(String grade);

    List<StudentExam> queryStudentExamByCourse(String grade, String course);

    int queryStudentExamCountByCourse(String grade, String course);
}
