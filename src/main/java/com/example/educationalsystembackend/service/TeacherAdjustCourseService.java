package com.example.educationalsystembackend.service;

import com.example.educationalsystembackend.pojo.AdjustCourse;

import java.util.List;
import java.util.Map;

public interface TeacherAdjustCourseService {

    List<AdjustCourse> queryAllTeacherAdjustCourse(String teacher, int num, int size);

    int queryAllTeacherAdjustCourseCount(String teacher);

    List<Map<String, Object>> queryTeacherAdjustCourseList(String teacher);

    List<AdjustCourse> queryTeacherAdjustCourse(String teacher, String course, List<Integer> resultList, int num, int size);

    int queryTeacherAdjustCourseCount(String teacher, String course, List<Integer> resultList);

    AdjustCourse queryTeacherAdjustCourseForm(Long id);

    void updateAdjustCourse(AdjustCourse adjustCourse);

    int queryAdjustCourseFrom(Long id);

    int queryAdjustCourseTo(Long id);

    void deleteTeacherAdjustCourse(Long id);
}
