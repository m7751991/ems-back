package com.example.educationalsystembackend.service.impl;

import com.example.educationalsystembackend.mapper.GradeMapper;
import com.example.educationalsystembackend.pojo.Grade;
import com.example.educationalsystembackend.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public List<Grade> queryAllGrade(int num, int size) {
        return gradeMapper.queryAllGrade(num, size);
    }

    @Override
    public int queryAllGradeCount() {
        return gradeMapper.queryAllGradeCount();
    }

    @Override
    public void addGrade(Grade grade) {
        gradeMapper.addGrade(grade);
    }

    @Override
    public List<Grade> queryGrade(String id) {
        return gradeMapper.queryGrade(id);
    }

    @Override
    public int queryGradeCount(String id) {
        return gradeMapper.queryGradeCount(id);
    }

    @Override
    public void deleteGrade(String id) {
        gradeMapper.deleteGrade(id);
    }

    @Override
    public void updateGrade(Grade grade) {
        gradeMapper.updateGrade(grade);
    }

    @Override
    public List<Map<String, Object>> queryAllGradeIdAndName() {
        return gradeMapper.queryAllGradeIdAndName();
    }

    @Override
    public int queryNumberByGrade(String grade) {
        return gradeMapper.queryNumberByGrade(grade);
    }

    @Override
    public void alterNumberByGrade(String grade, int number) {
        gradeMapper.alterNumberByGrade(grade, number);
    }

    @Override
    public String queryGradeIdByName(String name) {
        return gradeMapper.queryGradeIdByName(name);
    }

    @Override
    public String queryGradeNameById(String id) {
        return gradeMapper.queryGradeNameById(id);
    }

    @Override
    public String queryGradeByStudent(String student) {
        return gradeMapper.queryGradeByStudent(student);
    }

    @Override
    public List<Map<String, Object>> queryAllExamGrade() {
        return gradeMapper.queryAllExamGrade();
    }

    @Override
    public List<Map<String, Object>> querySuperviseExamGrade(String supervisor) {
        return gradeMapper.querySuperviseExamGrade(supervisor);
    }


    @Override
    public List<Map<String, Object>> queryChoiceGradeByTeacher(String teacher) {
        return gradeMapper.queryChoiceGradeByTeacher(teacher);
    }

    @Override
    public List<Map<String, Object>> queryTeacherChoiceGradeByCourse(String course, String teacher) {
        return gradeMapper.queryTeacherChoiceGradeByCourse(course, teacher);
    }

    @Override
    public List<Grade> queryGradeExcelData() {
        return gradeMapper.queryGradeExcelData();
    }

    @Override
    public String queryRequiredCourseGrade(String course) {
        return gradeMapper.queryRequiredCourseGrade(course);
    }
}
