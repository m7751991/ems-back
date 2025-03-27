package com.example.educationalsystembackend.service.impl;

import com.example.educationalsystembackend.mapper.EvaluationMapper;
import com.example.educationalsystembackend.pojo.Evaluation;
import com.example.educationalsystembackend.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    private EvaluationMapper evaluationMapper;

    @Override
    public List<Evaluation> queryAllEvaluation(int num, int size) {
        return evaluationMapper.queryAllEvaluation(num, size);
    }

    @Override
    public int queryAllEvaluationCount() {
        return evaluationMapper.queryAllEvaluationCount();
    }

    @Override
    public List<Evaluation> queryEvaluation(String teacher, int num, int size) {
        return evaluationMapper.queryEvaluation(teacher, num, size);
    }

    @Override
    public int queryEvaluationCount(String teacher) {
        return evaluationMapper.queryEvaluationCount(teacher);
    }
}
