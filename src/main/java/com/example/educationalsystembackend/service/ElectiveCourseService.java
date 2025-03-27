package com.example.educationalsystembackend.service;

import com.example.educationalsystembackend.pojo.ElectiveCourse;

import java.util.List;
import java.util.Map;

public interface ElectiveCourseService {

    List<ElectiveCourse> queryAllElectiveCourse(int num, int size);

    int queryAllElectiveCourseCount();

    void deleteElectiveCourse(String id);

    List<ElectiveCourse> queryElectiveCourse(String id);

    int queryElectiveCourseCount(String id);

    void addElectiveCourse(ElectiveCourse electiveCourse);

    int queryElectiveCourseWeekById(String id);

    int queryElectiveCourseStartById(String id);

    int queryElectiveCourseEndById(String id);

    int queryElectiveCourseNumberById(String id);

    void updateElectiveCourse(ElectiveCourse electiveCourse);

    String queryCourseIdByNameAndTeacher(String name, String teacher);

    List<Map<String, Object>> queryAllCourseNameByTeacher(String teacher);

    int queryCourseCountByName(String name);

    List<Map<String, Object>> queryCourseByStudent(String student);

    String queryCourseNameById(String id);

    void changeElectiveCourseStatus(String course, boolean flag);

    List<Map<String, Object>> queryCourseByTeacher(String teacher);

    List<Map<String, Object>> queryCourseByTeacherAndGrade(String teacher, String grade);

    List<Map<String, Object>> queryChoiceCourseByTeacher(String student, String teacher);

    List<ElectiveCourse> queryElectiveCourseExcelData();

    int queryElectiveCourseMoreDateNumber(ElectiveCourse electiveCourse);

    void updateScoreProportion(int usual, int experiment, int exam, String course);

    int queryUsual(String course);

    int queryExam(String course);

    int queryExperiment(String course);
}
