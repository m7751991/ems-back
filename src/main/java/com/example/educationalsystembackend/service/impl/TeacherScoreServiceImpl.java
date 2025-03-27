package com.example.educationalsystembackend.service.impl;

import com.example.educationalsystembackend.mapper.TeacherScoreMapper;
import com.example.educationalsystembackend.pojo.TeacherScore;
import com.example.educationalsystembackend.service.TeacherScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherScoreServiceImpl implements TeacherScoreService {

    @Autowired
    private TeacherScoreMapper teacherScoreMapper;

    @Override
    public List<TeacherScore> queryAllTeacherCourseList(String teacher, int num, int size) {
        return teacherScoreMapper.queryAllTeacherCourseList(teacher, num, size);
    }

    @Override
    public int queryAllTeacherCourseCount(String teacher) {
        return teacherScoreMapper.queryAllTeacherCourseCount(teacher);
    }

    @Override
    public List<TeacherScore> queryTeacherCourse(String teacher, String course, int num, int size) {
        return teacherScoreMapper.queryTeacherCourse(teacher, course, num, size);
    }

    @Override
    public int queryTeacherCourseCount(String teacher, String course) {
        return teacherScoreMapper.queryTeacherCourseCount(teacher, course);
    }

    @Override
    public List<TeacherScore> queryTeacherScoreStudentList(String course) {
        return teacherScoreMapper.queryTeacherScoreStudentList(course);
    }

    @Override
    public void changeScoreStatus(String course, boolean score) {
        teacherScoreMapper.changeScoreStatus(course, score);
    }

    @Override
    public int queryCourseScoreIsNULLCount(String course, int experiment) {
        return teacherScoreMapper.queryCourseScoreIsNULLCount(course, experiment);
    }

    @Override
    public void addScore(TeacherScore teacherScore) {
        teacherScoreMapper.addScore(teacherScore);
    }
}
