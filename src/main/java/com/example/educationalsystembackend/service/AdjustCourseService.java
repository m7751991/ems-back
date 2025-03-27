package com.example.educationalsystembackend.service;

import com.example.educationalsystembackend.pojo.AdjustCourse;

import java.util.List;

public interface AdjustCourseService {

    List<AdjustCourse> queryAllAdjustCourse(int num, int size);

    int queryAllAdjustCourseCount();

    List<AdjustCourse> queryAdjustCourse(int num, int size, String teacher, String result);

    int queryAdjustCourseCount(String teacher, String result);

    void agreeAdjustCourse(Long id);

    void refuseAdjustCourse(Long id);
}
