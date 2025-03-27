package com.example.educationalsystembackend.service.impl;

import com.example.educationalsystembackend.mapper.StudentExamMapper;
import com.example.educationalsystembackend.pojo.StudentExam;
import com.example.educationalsystembackend.service.StudentExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentExamServiceImpl implements StudentExamService {

    @Autowired
    private StudentExamMapper studentExamMapper;

    @Override
    public List<StudentExam> queryStudentExam(String grade, int num, int size) {
        return studentExamMapper.queryStudentExam(grade, num, size);
    }

    @Override
    public int queryStudentExamCount(String grade) {
        return studentExamMapper.queryStudentExamCount(grade);
    }

    @Override
    public List<StudentExam> queryStudentExamByCourse(String grade, String course) {
        return studentExamMapper.queryStudentExamByCourse(grade, course);
    }

    @Override
    public int queryStudentExamCountByCourse(String grade, String course) {
        return studentExamMapper.queryStudentExamCountByCourse(grade, course);
    }
}
