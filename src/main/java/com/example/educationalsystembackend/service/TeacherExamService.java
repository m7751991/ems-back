package com.example.educationalsystembackend.service;

import com.example.educationalsystembackend.pojo.TeacherExam;

import java.util.List;

public interface TeacherExamService {

    List<TeacherExam> queryAllTeacherExam(String teacher, int num, int size);

    int queryAllTeacherExamCount(String teacher);

    List<TeacherExam> queryTeacherExam(String teacher, String course, int num, int size);

    int queryTeacherExamCount(String teacher, String course);

    List<TeacherExam> queryAllSuperviseExam(String teacher, int num, int size);

    int queryAllSuperviseExamCount(String teacher);

    List<TeacherExam> querySuperviseExam(String teacher, String grade, int num, int size);

    int querySuperviseExamCount(String teacher, String grade);
}
