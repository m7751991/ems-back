package com.example.educationalsystembackend.service;

import com.example.educationalsystembackend.pojo.RetakeCourse;
import com.example.educationalsystembackend.util.ConflictException;
import java.util.List;

public interface RetakeCourseService {

    List<RetakeCourse> queryAllRetakeCourse(int num, int size);

    int queryAllRetakeCourseCount();

    List<RetakeCourse> queryRetakeCourse(String course, int num, int size);

    int queryRetakeCourseCount(String course);

    void addRetakeCourse(RetakeCourse retakeCourse);

    void deleteRetakeCourse(String course);

    void updateRetakeCourse(RetakeCourse retakeCourse);

    int queryNumberById(String id);

    void updateCourseNumber(String id, int number);

    void queryRetakeCourseMoreDateNumber(RetakeCourse retakeCourse) throws ConflictException;
}
