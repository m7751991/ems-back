package com.example.educationalsystembackend.service.impl;

import com.example.educationalsystembackend.mapper.ScoreStatusMapper;
import com.example.educationalsystembackend.pojo.ScoreStatus;
import com.example.educationalsystembackend.service.ScoreStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreStatusServiceImpl implements ScoreStatusService {

    @Autowired
    private ScoreStatusMapper scoreStatusMapper;

    @Override
    public List<ScoreStatus> queryAllScoreStatus(int num, int size) {
        return scoreStatusMapper.queryAllScoreStatus(num, size);
    }

    @Override
    public int queryAllScoreStatusCount() {
        return scoreStatusMapper.queryAllScoreStatusCount();
    }

    @Override
    public List<ScoreStatus> queryScoreStatus(String teacher, List<Integer> scoreList, int num, int size) {
        return scoreStatusMapper.queryScoreStatus(teacher, scoreList, num, size);
    }

    @Override
    public int queryScoreStatusCount(String teacher, List<Integer> scoreList) {
        return scoreStatusMapper.queryScoreStatusCount(teacher, scoreList);
    }

    @Override
    public List<ScoreStatus> queryScoreStatusExcelData() {
        return scoreStatusMapper.queryScoreStatusExcelData();
    }

}
