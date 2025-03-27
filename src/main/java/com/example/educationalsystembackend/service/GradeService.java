package com.example.educationalsystembackend.service;

import com.example.educationalsystembackend.pojo.Grade;

import java.util.List;
import java.util.Map;

public interface GradeService {

    List<Grade> queryAllGrade(int num, int size);

    int queryAllGradeCount();

    void addGrade(Grade grade);

    List<Grade> queryGrade(String id);

    int queryGradeCount(String id);

    void deleteGrade(String id);

    void updateGrade(Grade grade);

    List<Map<String, Object>> queryAllGradeIdAndName();

    int queryNumberByGrade(String grade);

    void alterNumberByGrade(String grade, int number);

    String queryGradeIdByName(String name);

    String queryGradeNameById(String id);

    String queryGradeByStudent(String student);

    List<Map<String, Object>> queryAllExamGrade();

    List<Map<String, Object>> querySuperviseExamGrade(String supervisor);

    List<Map<String, Object>> queryChoiceGradeByTeacher(String teacher);

    List<Map<String, Object>> queryTeacherChoiceGradeByCourse(String course, String teacher);

    List<Grade> queryGradeExcelData();

    String queryRequiredCourseGrade(String course);
}
