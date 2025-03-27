package com.example.educationalsystembackend.service.impl;

import com.example.educationalsystembackend.mapper.TeacherEvaluationMapper;
import com.example.educationalsystembackend.pojo.TeacherEvaluation;
import com.example.educationalsystembackend.service.TeacherEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherEvaluationServiceImpl implements TeacherEvaluationService {

    @Autowired
    private TeacherEvaluationMapper teacherEvaluationMapper;

    @Override
    public List<TeacherEvaluation> queryAllTeacherEvaluation(String teacher, int num, int size) {
        return teacherEvaluationMapper.queryAllTeacherEvaluation(teacher, num, size);
    }

    @Override
    public int queryAllTeacherEvaluationCount(String teacher) {
        return teacherEvaluationMapper.queryAllTeacherEvaluationCount(teacher);
    }

    @Override
    public List<TeacherEvaluation> queryTeacherEvaluation(String teacher, String course, String grade, int num, int size) {
        return teacherEvaluationMapper.queryTeacherEvaluation(teacher, course, grade, num, size);
    }

    @Override
    public int queryTeacherEvaluationCount(String teacher, String course, String grade) {
        return teacherEvaluationMapper.queryTeacherEvaluationCount(teacher, course, grade);
    }


}
