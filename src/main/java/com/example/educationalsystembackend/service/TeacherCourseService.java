package com.example.educationalsystembackend.service;

import com.example.educationalsystembackend.pojo.AdjustCourse;
import com.example.educationalsystembackend.pojo.TeacherCourse;

import java.util.List;
import java.util.Map;

public interface TeacherCourseService {

    List<TeacherCourse> queryAllTeacherCourse(String teacher, int num, int size);

    int queryAllTeacherCourseCount(String teacher);

    List<TeacherCourse> queryTeacherCourse(String teacher, String id, int num, int size);

    int queryTeacherCourseCount(String teacher, String id);

    List<Map<String, Object>> queryTeacherCourseIdAndName(String teacher);

    List<Map<String, Object>> queryTeacherCourseId(String teacher);

    List<Map<String, Object>> queryTeacherCourseExcelData(String id);

    void addAdjustCourse(AdjustCourse adjustCourse);
}
