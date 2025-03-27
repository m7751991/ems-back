package com.example.educationalsystembackend.service;

import com.example.educationalsystembackend.pojo.Score;

import java.util.List;
import java.util.Map;

public interface ScoreService {

    List<Score> queryAllScore(int num, int size);

    int queryAllScoreCount();

    List<Score> queryScore(String student, int num, int size);

    int queryScoreCount(String student);

    void deleteScore(String student, String course);

    List<Map<String, Object>> queryScoreExcelData(String grade);
}
