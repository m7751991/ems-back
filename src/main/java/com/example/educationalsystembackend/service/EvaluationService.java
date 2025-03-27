package com.example.educationalsystembackend.service;

import com.example.educationalsystembackend.pojo.Evaluation;

import java.util.List;

public interface EvaluationService {

    List<Evaluation> queryAllEvaluation(int num, int size);

    int queryAllEvaluationCount();

    List<Evaluation> queryEvaluation(String teacher, int num, int size);

    int queryEvaluationCount(String teacher);
}
