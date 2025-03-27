package com.example.educationalsystembackend.service.impl;

import com.example.educationalsystembackend.mapper.TeacherAdjustCourseMapper;
import com.example.educationalsystembackend.pojo.AdjustCourse;
import com.example.educationalsystembackend.service.TeacherAdjustCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TeacherAdjustCourseServiceImpl implements TeacherAdjustCourseService {

    @Autowired
    private TeacherAdjustCourseMapper teacherAdjustCourseMapper;

    @Override
    public List<AdjustCourse> queryAllTeacherAdjustCourse(String teacher, int num, int size) {
        return teacherAdjustCourseMapper.queryAllTeacherAdjustCourse(teacher, num, size);
    }

    @Override
    public int queryAllTeacherAdjustCourseCount(String teacher) {
        return teacherAdjustCourseMapper.queryAllTeacherAdjustCourseCount(teacher);
    }

    @Override
    public List<Map<String, Object>> queryTeacherAdjustCourseList(String teacher) {
        return teacherAdjustCourseMapper.queryTeacherAdjustCourseList(teacher);
    }

    @Override
    public List<AdjustCourse> queryTeacherAdjustCourse(String teacher, String course, List<Integer> resultList, int num, int size) {
        return teacherAdjustCourseMapper.queryTeacherAdjustCourse(teacher, course, resultList, num, size);
    }

    @Override
    public int queryTeacherAdjustCourseCount(String teacher, String course, List<Integer> resultList) {
        return teacherAdjustCourseMapper.queryTeacherAdjustCourseCount(teacher, course, resultList);
    }

    @Override
    public AdjustCourse queryTeacherAdjustCourseForm(Long id) {
        return teacherAdjustCourseMapper.queryTeacherAdjustCourseForm(id);
    }

    @Override
    public void updateAdjustCourse(AdjustCourse adjustCourse) {
        teacherAdjustCourseMapper.updateAdjustCourse(adjustCourse);
    }

    @Override
    public int queryAdjustCourseFrom(Long id) {
        return teacherAdjustCourseMapper.queryAdjustCourseFrom(id);
    }

    @Override
    public int queryAdjustCourseTo(Long id) {
        return teacherAdjustCourseMapper.queryAdjustCourseTo(id);
    }

    @Override
    public void deleteTeacherAdjustCourse(Long id) {
        teacherAdjustCourseMapper.deleteTeacherAdjustCourse(id);
    }
}
