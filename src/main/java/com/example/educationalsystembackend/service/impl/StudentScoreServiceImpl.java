package com.example.educationalsystembackend.service.impl;

import com.example.educationalsystembackend.mapper.StudentScoreMapper;
import com.example.educationalsystembackend.pojo.StudentScore;
import com.example.educationalsystembackend.service.StudentScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentScoreServiceImpl implements StudentScoreService {

    @Autowired
    private StudentScoreMapper studentScoreMapper;

    @Override
    public List<StudentScore> queryStudentScore(String student, int num, int size) {
        return studentScoreMapper.queryStudentScore(student, num, size);
    }

    @Override
    public List<StudentScore> queryStudentScoreByOrder(String student, String prop, String order, int num, int size) {
        return studentScoreMapper.queryStudentScoreByOrder(student, prop, order, num, size);
    }

    @Override
    public int queryStudentScoreCount(String student) {
        return studentScoreMapper.queryStudentScoreCount(student);
    }

    @Override
    public List<StudentScore> queryStudentScoreByCourese(String student, String course, int num, int size) {
        return studentScoreMapper.queryStudentScoreByCourese(student, course, num, size);
    }

    @Override
    public int queryStudentScoreCountByCourse(String student, String course) {
        return studentScoreMapper.queryStudentScoreCountByCourse(student, course);
    }

    @Override
    public Integer queryStudentCourseMaxScore(String student, String course) {
        return studentScoreMapper.queryStudentCourseMaxScore(student, course);
    }
}
