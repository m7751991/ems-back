package com.example.educationalsystembackend.service.impl;

import com.example.educationalsystembackend.mapper.ElectiveCourseMapper;
import com.example.educationalsystembackend.pojo.ElectiveCourse;
import com.example.educationalsystembackend.service.ElectiveCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ElectiveCourseServiceImpl implements ElectiveCourseService {

    @Autowired
    private ElectiveCourseMapper electiveCourseMapper;

    @Override
    public List<ElectiveCourse> queryAllElectiveCourse(int num, int size) {
        return electiveCourseMapper.queryAllElectiveCourse(num, size);
    }

    @Override
    public int queryAllElectiveCourseCount() {
        return electiveCourseMapper.queryAllElectiveCourseCount();
    }

    @Override
    public void deleteElectiveCourse(String id) {
        electiveCourseMapper.deleteElectiveCourse(id);
    }

    @Override
    public List<ElectiveCourse> queryElectiveCourse(String id) {
        return electiveCourseMapper.queryElectiveCourse(id);
    }

    @Override
    public int queryElectiveCourseCount(String id) {
        return electiveCourseMapper.queryElectiveCourseCount(id);
    }

    @Override
    public void addElectiveCourse(ElectiveCourse electiveCourse) {
        electiveCourseMapper.addElectiveCourse(electiveCourse);
    }

    @Override
    public int queryElectiveCourseWeekById(String id) {
        return electiveCourseMapper.queryElectiveCourseWeekById(id);
    }

    @Override
    public int queryElectiveCourseStartById(String id) {
        return electiveCourseMapper.queryElectiveCourseStartById(id);
    }

    @Override
    public int queryElectiveCourseEndById(String id) {
        return electiveCourseMapper.queryElectiveCourseEndById(id);
    }

    @Override
    public int queryElectiveCourseNumberById(String id) {
        return electiveCourseMapper.queryElectiveCourseNumberById(id);
    }

    @Override
    public void updateElectiveCourse(ElectiveCourse electiveCourse) {
        electiveCourseMapper.updateElectiveCourse(electiveCourse);
    }

    @Override
    public String queryCourseIdByNameAndTeacher(String name, String teacher) {
        return electiveCourseMapper.queryCourseIdByNameAndTeacher(name, teacher);
    }

    @Override
    public List<Map<String, Object>> queryAllCourseNameByTeacher(String teacher) {
        return electiveCourseMapper.queryAllCourseNameByTeacher(teacher);
    }

    @Override
    public int queryCourseCountByName(String name) {
        return electiveCourseMapper.queryCourseCountByName(name);
    }

    @Override
    public List<Map<String, Object>> queryCourseByStudent(String student) {
        return electiveCourseMapper.queryCourseByStudent(student);
    }

    @Override
    public String queryCourseNameById(String id) {
        return electiveCourseMapper.queryCourseNameById(id);
    }

    @Override
    public void changeElectiveCourseStatus(String course, boolean flag) {
        electiveCourseMapper.changeElectiveCourseStatus(course, flag);
    }

    @Override
    public List<Map<String, Object>> queryCourseByTeacher(String teacher) {
        return electiveCourseMapper.queryCourseByTeacher(teacher);
    }

    @Override
    public List<Map<String, Object>> queryCourseByTeacherAndGrade(String teacher, String grade) {
        return electiveCourseMapper.queryCourseByTeacherAndGrade(teacher, grade);
    }

    @Override
    public List<Map<String, Object>> queryChoiceCourseByTeacher(String student, String teacher) {
        return electiveCourseMapper.queryChoiceCourseByTeacher(student, teacher);
    }

    @Override
    public List<ElectiveCourse> queryElectiveCourseExcelData() {
        return electiveCourseMapper.queryElectiveCourseExcelData();
    }

    @Override
    public int queryElectiveCourseMoreDateNumber(ElectiveCourse electiveCourse) {
        return electiveCourseMapper.queryElectiveCourseMoreDateNumber(electiveCourse);
    }

    @Override
    public void updateScoreProportion(int usual, int experiment, int exam, String course) {
        electiveCourseMapper.updateScoreProportion(usual, experiment, exam, course);
    }

    @Override
    public int queryUsual(String course) {
        return electiveCourseMapper.queryUsual(course);
    }

    @Override
    public int queryExam(String course) {
        return electiveCourseMapper.queryExam(course);
    }

    @Override
    public int queryExperiment(String course) {
        return electiveCourseMapper.queryExperiment(course);
    }
}
