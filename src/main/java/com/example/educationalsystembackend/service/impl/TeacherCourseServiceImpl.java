package com.example.educationalsystembackend.service.impl;

import com.example.educationalsystembackend.mapper.TeacherCourseMapper;
import com.example.educationalsystembackend.pojo.AdjustCourse;
import com.example.educationalsystembackend.pojo.TeacherCourse;
import com.example.educationalsystembackend.service.TeacherCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TeacherCourseServiceImpl implements TeacherCourseService {

    @Autowired
    private TeacherCourseMapper teacherCourseMapper;

    @Override
    public List<TeacherCourse> queryAllTeacherCourse(String teacher, int num, int size) {
        return teacherCourseMapper.queryAllTeacherCourse(teacher, num, size);
    }

    @Override
    public int queryAllTeacherCourseCount(String teacher) {
        return teacherCourseMapper.queryAllTeacherCourseCount(teacher);
    }

    @Override
    public List<Map<String, Object>> queryTeacherCourseIdAndName(String teacher) {
        return teacherCourseMapper.queryTeacherCourseIdAndName(teacher);
    }

    @Override
    public List<Map<String, Object>> queryTeacherCourseId(String teacher) {
        return teacherCourseMapper.queryTeacherCourseId(teacher);
    }

    @Override
    public List<Map<String, Object>> queryTeacherCourseExcelData(String id) {
        return teacherCourseMapper.queryTeacherCourseExcelData(id);
    }

    @Override
    public void addAdjustCourse(AdjustCourse adjustCourse) {
        teacherCourseMapper.addAdjustCourse(adjustCourse);
    }

    @Override
    public List<TeacherCourse> queryTeacherCourse(String teacher, String id, int num, int size) {
        return teacherCourseMapper.queryTeacherCourse(teacher, id, num, size);
    }

    @Override
    public int queryTeacherCourseCount(String teacher, String id) {
        return teacherCourseMapper.queryTeacherCourseCount(teacher, id);
    }
}
