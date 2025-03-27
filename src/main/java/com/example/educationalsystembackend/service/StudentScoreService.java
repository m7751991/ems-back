package com.example.educationalsystembackend.service;

import com.example.educationalsystembackend.pojo.StudentScore;

import java.util.List;

public interface StudentScoreService {

    List<StudentScore> queryStudentScore(String student, int num, int size);

    List<StudentScore> queryStudentScoreByOrder(String student, String prop, String order, int num, int size);

    int queryStudentScoreCount(String student);

    List<StudentScore> queryStudentScoreByCourese(String student, String course, int num, int size);

    int queryStudentScoreCountByCourse(String student, String course);

    Integer queryStudentCourseMaxScore(String student, String course);
}
