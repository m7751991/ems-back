package com.example.educationalsystembackend.service.impl;

import com.example.educationalsystembackend.mapper.RequiredCourseMapper;
import com.example.educationalsystembackend.pojo.RequiredCourse;
import com.example.educationalsystembackend.service.RequiredCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.educationalsystembackend.util.ConflictException;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RequiredCourseServiceImpl implements RequiredCourseService {

    @Autowired
    private RequiredCourseMapper requiredCourseMapper;

    @Override
    public List<RequiredCourse> queryAllRequiredCourse(int num, int size) {
        return requiredCourseMapper.queryAllRequiredCourse(num, size);
    }

    @Override
    public int queryAllRequiredCourseCount() {
        return requiredCourseMapper.queryAllRequiredCourseCount();
    }

    @Override
    public void deleteRequiredCourse(String course, String grade) {
        requiredCourseMapper.deleteRequiredCourse(course, grade);
    }

    @Override
    public void addRequiredCourse(String course, String grade) {
        requiredCourseMapper.addRequiredCourse(course, grade);
    }

    @Override
    public List<RequiredCourse> queryRequiredCourse(String grade, int num, int size) {
        return requiredCourseMapper.queryRequiredCourse(grade, num, size);
    }

    @Override
    public int queryRequiredCourseCount(String grade) {
        return requiredCourseMapper.queryRequiredCourseCount(grade);
    }

    @Override
    public int queryGradeCount(String course) {
        return requiredCourseMapper.queryGradeCount(course);
    }

    @Override
    public List<Map<String, Object>> queryStudentRequiredCourse(String student) {
        return requiredCourseMapper.queryStudentRequiredCourse(student);
    }

    @Override
    public List<Map<String, Object>> queryTeacherRequiredCourse(String teacher) {
        return requiredCourseMapper.queryTeacherRequiredCourse(teacher);
    }

    @Override
    public List<Map<String, Object>> queryRequiredCourseChildren(String id) {
        return requiredCourseMapper.queryRequiredCourseChildren(id);
    }

    @Override
    public void updateRequiredCourse(RequiredCourse requiredCourse) {
        requiredCourseMapper.updateRequiredCourse(requiredCourse);
    }

    @Override
    public List<RequiredCourse> queryRequiredCourseExcelData() {
        return requiredCourseMapper.queryRequiredCourseExcelData();
    }

    @Override
    public int queryRequiredCourseCountById(String course) {
        return requiredCourseMapper.queryRequiredCourseCountById(course);
    }

    @Override
    public void queryRequiredCourseTeacherMoreDateNumber(RequiredCourse requiredCourse) throws ConflictException {
        String conflictMessage = requiredCourseMapper.checkConflicts(requiredCourse);
        if (conflictMessage != null) {
            throw new ConflictException(conflictMessage);
        }
    }

    @Override
    public int queryGradeTimeConflict(String grade, Date from, Date to, int week, int start, int end) {
        return 0;
    }
}
