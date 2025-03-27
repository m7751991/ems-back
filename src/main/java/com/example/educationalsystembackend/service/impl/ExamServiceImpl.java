package com.example.educationalsystembackend.service.impl;

import com.example.educationalsystembackend.mapper.ExamMapper;
import com.example.educationalsystembackend.pojo.Exam;
import com.example.educationalsystembackend.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamMapper examMapper;

    @Override
    public List<Exam> queryAllExam(int num, int size) {
        return examMapper.queryAllExam(num, size);
    }

    @Override
    public int queryAllExamCount() {
        return examMapper.queryAllExamCount();
    }

    @Override
    public void deleteExam(String course, String grade) {
        examMapper.deleteExam(course, grade);
    }

    @Override
    public List<Exam> queryExam(String grade, int num, int size) {
        return examMapper.queryExam(grade, num, size);
    }

    @Override
    public int queryExamCount(String grade) {
        return examMapper.queryExamCount(grade);
    }

    @Override
    public void addExam(Exam exam) {
        examMapper.addExam(exam);
    }

    @Override
    public void alterExam(Exam exam) {
        examMapper.alterExam(exam);
    }

    @Override
    public List<Exam> queryExamExcelData(Date from, Date to) {
        return examMapper.queryExamExcelData(from, to);
    }

    @Override
    public int queryExamCourseMoreTimeNumber(Exam exam) {
        return examMapper.queryExamCourseMoreTimeNumber(exam);
    }

    @Override
    public int queryExamClassroomMoreTimeNumber(Exam exam) {
        return examMapper.queryExamClassroomMoreTimeNumber(exam);
    }

    @Override
    public int queryExamTeacherMoreTimeNumber(Exam exam) {
        return examMapper.queryExamTeacherMoreTimeNumber(exam);
    }
}
