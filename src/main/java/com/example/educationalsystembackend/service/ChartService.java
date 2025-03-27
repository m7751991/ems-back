package com.example.educationalsystembackend.service;

public interface ChartService {

    String[] queryStudentChartX(String student);

    int[] queryStudentChartY(String student);

    float queryStudentChartZ(String course);

    String[] queryStudentChartId(String student);

    int queryTeacherChart(String teacher, String course, String grade, int start, int end);

    int queryChart(int start, int end);

    float queryDataMax(String teacher, String course, String grade);

    float queryDataMin(String teacher, String course, String grade);

    float queryDataAvg(String teacher, String course, String grade);

    float queryDataVariance(String teacher, String course, String grade);

    float queryDataStddev(String teacher, String course, String grade);
}
