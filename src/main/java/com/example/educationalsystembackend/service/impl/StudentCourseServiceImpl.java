package com.example.educationalsystembackend.service.impl;

import com.example.educationalsystembackend.mapper.StudentCourseMapper;
import com.example.educationalsystembackend.pojo.StudentCourse;
import com.example.educationalsystembackend.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {

    @Autowired
    private StudentCourseMapper studentCourseMapper;

    @Override
    public List<StudentCourse> queryAllStudentCourse(String student, int num, int size) {
        return studentCourseMapper.queryAllStudentCourse(student, num, size);
    }

    @Override
    public int queryAllStudentCourseCount(String student) {
        return studentCourseMapper.queryAllStudentCourseCount(student);
    }

    @Override
    public List<StudentCourse> queryStudentCourse(String student, String course, int num, int size) {
        return studentCourseMapper.queryStudentCourse(student, course, num, size);
    }

    @Override
    public int queryStudentCourseCount(String student, String course) {
        return studentCourseMapper.queryStudentCourseCount(student, course);
    }
}

