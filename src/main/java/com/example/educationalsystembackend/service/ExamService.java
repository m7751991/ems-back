package com.example.educationalsystembackend.service;

import com.example.educationalsystembackend.pojo.Exam;

import java.util.Date;
import java.util.List;

public interface ExamService {
    List<Exam> queryAllExam(int num, int size);

    int queryAllExamCount();

    void deleteExam(String course, String grade);

    List<Exam> queryExam(String grade, int num, int size);

    int queryExamCount(String grade);

    void addExam(Exam exam);

    void alterExam(Exam exam);

    List<Exam> queryExamExcelData(Date from, Date to);

    int queryExamCourseMoreTimeNumber(Exam exam);

    int queryExamClassroomMoreTimeNumber(Exam exam);

    int queryExamTeacherMoreTimeNumber(Exam exam);
}
