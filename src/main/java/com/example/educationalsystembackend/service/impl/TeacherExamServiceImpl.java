package com.example.educationalsystembackend.service.impl;

import com.example.educationalsystembackend.mapper.TeacherExamMapper;
import com.example.educationalsystembackend.pojo.TeacherExam;
import com.example.educationalsystembackend.service.TeacherExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherExamServiceImpl implements TeacherExamService {

    @Autowired
    private TeacherExamMapper teacherExamMapper;

    @Override
    public List<TeacherExam> queryAllTeacherExam(String teacher, int num, int size) {
        return teacherExamMapper.queryAllTeacherExam(teacher, num, size);
    }

    @Override
    public int queryAllTeacherExamCount(String teacher) {
        return teacherExamMapper.queryAllTeacherExamCount(teacher);
    }

    @Override
    public List<TeacherExam> queryTeacherExam(String teacher, String course, int num, int size) {
        return teacherExamMapper.queryTeacherExam(teacher, course, num, size);
    }

    @Override
    public int queryTeacherExamCount(String teacher, String course) {
        return teacherExamMapper.queryTeacherExamCount(teacher, course);
    }

    @Override
    public List<TeacherExam> queryAllSuperviseExam(String teacher, int num, int size) {
        return teacherExamMapper.queryAllSuperviseExam(teacher, num, size);
    }

    @Override
    public int queryAllSuperviseExamCount(String teacher) {
        return teacherExamMapper.queryAllSuperviseExamCount(teacher);
    }

    @Override
    public List<TeacherExam> querySuperviseExam(String teacher, String grade, int num, int size) {
        return teacherExamMapper.querySuperviseExam(teacher, grade, num, size);
    }

    @Override
    public int querySuperviseExamCount(String teacher, String grade) {
        return teacherExamMapper.querySuperviseExamCount(teacher, grade);
    }

}
