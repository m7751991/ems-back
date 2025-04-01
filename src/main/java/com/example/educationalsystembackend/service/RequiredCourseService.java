/*
 * @Descripttion: 
 * @version: 
 * @Author: zixi
 * @Date: 2025-03-27 00:12:47
 * @LastEditors: zixi
 * @LastEditTime: 2025-03-27 23:19:52
 */
package com.example.educationalsystembackend.service;
import com.example.educationalsystembackend.pojo.RequiredCourse;
import com.example.educationalsystembackend.util.ConflictException;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface RequiredCourseService {

    List<RequiredCourse> queryAllRequiredCourse(int num, int size);

    int queryAllRequiredCourseCount();

    void deleteRequiredCourse(String course, String grade);

    void addRequiredCourse(String course, String grade);

    List<RequiredCourse> queryRequiredCourse(String grade, int num, int size);

    int queryRequiredCourseCount(String grade);

    int queryGradeCount(String course);

    List<Map<String, Object>> queryStudentRequiredCourse(String student);

    List<Map<String, Object>> queryTeacherRequiredCourse(String teacher);

    List<Map<String, Object>> queryRequiredCourseChildren(String id);

    void updateRequiredCourse(RequiredCourse requiredCourse);

    List<RequiredCourse> queryRequiredCourseExcelData();

    int queryRequiredCourseCountById(String course);

    void queryRequiredCourseTeacherMoreDateNumber(RequiredCourse requiredCourse) throws ConflictException;
    
    /**
     * 检查班级在指定时间段是否已有课程安排
     * 
     * @param grade 班级
     * @param from 开始日期
     * @param to 结束日期
     * @param week 周几
     * @param start 开始节次
     * @param end 结束节次
     * @return 冲突课程数量
     */
    int queryGradeTimeConflict(String grade, Date from, Date to, int week, int start, int end);
}
