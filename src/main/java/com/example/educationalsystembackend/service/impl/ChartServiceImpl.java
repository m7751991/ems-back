package com.example.educationalsystembackend.service.impl;

import com.example.educationalsystembackend.mapper.ChartMapper;
import com.example.educationalsystembackend.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ChartServiceImpl implements ChartService {

    @Autowired
    private ChartMapper chartMapper;

    @Override
    public String[] queryStudentChartX(String student) {
        return chartMapper.queryStudentChartX(student);
    }

    @Override
    public int[] queryStudentChartY(String student) {
        return chartMapper.queryStudentChartY(student);
    }

    @Override
    public float queryStudentChartZ(String course) {
        return chartMapper.queryStudentChartZ(course);
    }


    @Override
    public String[] queryStudentChartId(String student) {
        return chartMapper.queryStudentChartId(student);
    }

    @Override
    public int queryTeacherChart(String teacher, String course, String grade, int start, int end) {
        return chartMapper.queryTeacherChart(teacher, course, grade, start, end);
    }

    @Override
    public int queryChart(int start, int end) {
        return chartMapper.queryChart(start, end);
    }

    @Override
    public float queryDataMax(String teacher, String course, String grade) {
        Float f = chartMapper.queryDataMax(teacher, course, grade);
        if(Objects.isNull(f)){
            return 0;
        }else {
            return chartMapper.queryDataMax(teacher, course, grade);
        }
    }

    @Override
    public float queryDataMin(String teacher, String course, String grade) {
        Float f = chartMapper.queryDataMin(teacher, course, grade);
        if(Objects.isNull(f)){
            return 0;
        }else {
            return chartMapper.queryDataMin(teacher, course, grade);
        }
    }

    @Override
    public float queryDataAvg(String teacher, String course, String grade) {
        Float f = chartMapper.queryDataAvg(teacher, course, grade);
        if(Objects.isNull(f)){
            return 0;
        }else {
            return chartMapper.queryDataAvg(teacher, course, grade);
        }
    }

    @Override
    public float queryDataVariance(String teacher, String course, String grade) {
        Float f = chartMapper.queryDataVariance(teacher, course, grade);
        if(Objects.isNull(f)){
            return 0;
        }else {
            return chartMapper.queryDataVariance(teacher, course, grade);
        }
    }

    @Override
    public float queryDataStddev(String teacher, String course, String grade) {
        Float f = chartMapper.queryDataStddev(teacher, course, grade);
        if(Objects.isNull(f)){
            return 0;
        }else {
            return chartMapper.queryDataStddev(teacher, course, grade);
        }
    }
}
