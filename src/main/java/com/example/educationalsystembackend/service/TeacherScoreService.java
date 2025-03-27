package com.example.educationalsystembackend.service;

import com.example.educationalsystembackend.pojo.TeacherScore;

import java.util.List;

public interface TeacherScoreService {

    List<TeacherScore> queryAllTeacherCourseList(String teacher, int num, int size);

    int queryAllTeacherCourseCount(String teacher);

    List<TeacherScore> queryTeacherCourse(String teacher, String course, int num, int size);

    int queryTeacherCourseCount(String teacher, String course);

    List<TeacherScore> queryTeacherScoreStudentList(String course);

    void changeScoreStatus(String course, boolean score);

    int queryCourseScoreIsNULLCount(String course, int experiment);

    void addScore(TeacherScore teacherScore);
}
