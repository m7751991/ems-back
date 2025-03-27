package com.example.educationalsystembackend.service.impl;

import com.example.educationalsystembackend.mapper.StudentEvaluationMapper;
import com.example.educationalsystembackend.pojo.StudentEvaluation;
import com.example.educationalsystembackend.service.StudentEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentEvaluationServiceImpl implements StudentEvaluationService {

    @Autowired
    private StudentEvaluationMapper studentEvaluationMapper;

    @Override
    public List<StudentEvaluation> queryStudentEvaluationList(String student) {
        return studentEvaluationMapper.queryStudentEvaluationList(student);
    }

    @Override
    public void addEvaluation(String student, String course, String teacher, int evaluation, String comment) {
        studentEvaluationMapper.addEvaluation(student, course, teacher, evaluation, comment);
    }

    @Override
    public List<StudentEvaluation> searchStudentEvaluation(String student, String course, String teacher) {
        return studentEvaluationMapper.searchStudentEvaluation(student, course, teacher);
    }
}
