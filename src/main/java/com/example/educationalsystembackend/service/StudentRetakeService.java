package com.example.educationalsystembackend.service;

import com.example.educationalsystembackend.pojo.StudentRetake;

import java.util.List;

public interface StudentRetakeService {

    String[] queryRetakeCourseNameList(String student);

    Float[] queryRetakeCourseCreditList(String student);

    Float queryRetakeCourseCredit(String student, String course);

    List<StudentRetake> queryAllStudentRetakeCourseKind1(String student, String course, float credit);

    List<StudentRetake> queryAllStudentRetakeCourseKind3(String student, String course, float credit);

    List<StudentRetake> queryStudentRetakeCourseKind1(String student, String course, float credit);

    List<StudentRetake> queryStudentRetakeCourseKind3(String student, String course, float credit);
}
