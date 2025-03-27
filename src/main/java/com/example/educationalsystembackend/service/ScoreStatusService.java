package com.example.educationalsystembackend.service;

import com.example.educationalsystembackend.pojo.ScoreStatus;

import java.util.List;

public interface ScoreStatusService {

    List<ScoreStatus> queryAllScoreStatus(int num, int size);

    int queryAllScoreStatusCount();

    List<ScoreStatus> queryScoreStatus(String teacher, List<Integer> scoreList, int num, int size);

    int queryScoreStatusCount(String teacher, List<Integer> scoreList);

    List<ScoreStatus> queryScoreStatusExcelData();
}
