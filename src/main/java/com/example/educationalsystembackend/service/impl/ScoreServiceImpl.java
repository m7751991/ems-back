package com.example.educationalsystembackend.service.impl;

import com.example.educationalsystembackend.mapper.ScoreMapper;
import com.example.educationalsystembackend.pojo.Score;
import com.example.educationalsystembackend.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public List<Score> queryAllScore(int num, int size) {
        return scoreMapper.queryAllScore(num, size);
    }

    @Override
    public int queryAllScoreCount() {
        return scoreMapper.queryAllScoreCount();
    }

    @Override
    public List<Score> queryScore(String student, int num, int size) {
        return scoreMapper.queryScore(student, num, size);
    }

    @Override
    public int queryScoreCount(String student) {
        return scoreMapper.queryScoreCount(student);
    }

    @Override
    public void deleteScore(String student, String course) {
        scoreMapper.deleteScore(student, course);
    }

    @Override
    public List<Map<String, Object>> queryScoreExcelData(String grade) {
        return scoreMapper.queryScoreExcelData(grade);
    }
}
