package com.example.educationalsystembackend.service;

import com.example.educationalsystembackend.pojo.StudentCourse;

import java.util.List;

public interface StudentCourseService {

    List<StudentCourse> queryAllStudentCourse(String student, int num, int size);

    int queryAllStudentCourseCount(String student);

    List<StudentCourse> queryStudentCourse(String student, String course, int num, int size);

    int queryStudentCourseCount(String student, String course);
}

