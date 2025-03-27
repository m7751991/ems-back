package com.example.educationalsystembackend.service;


import com.example.educationalsystembackend.pojo.RequiredCourse;

import java.util.List;
import java.util.Map;

public interface RequiredCourseService {

    List<RequiredCourse> queryAllRequiredCourse(int num, int size);

    int queryAllRequiredCourseCount();

    void deleteRequiredCourse(String course, String grade);

    void addRequiredCourse(String course, String grade);

    List<RequiredCourse> queryRequiredCourse(String grade, int num, int size);

    int queryRequiredCourseCount(String grade);

    int queryGradeCount(String course);

    List<Map<String, Object>> queryStudentRequiredCourse(String student);

    List<Map<String, Object>> queryTeacherRequiredCourse(String teacher);

    List<Map<String, Object>> queryRequiredCourseChildren(String id);

    void updateRequiredCourse(RequiredCourse requiredCourse);

    List<RequiredCourse> queryRequiredCourseExcelData();

    int queryRequiredCourseCountById(String course);

    int queryRequiredCourseTeacherMoreDateNumber(RequiredCourse requiredCourse);
}
