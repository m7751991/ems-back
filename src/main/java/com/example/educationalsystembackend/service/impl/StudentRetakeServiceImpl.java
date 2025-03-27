package com.example.educationalsystembackend.service.impl;

import com.example.educationalsystembackend.mapper.StudentRetakeMapper;
import com.example.educationalsystembackend.pojo.StudentRetake;
import com.example.educationalsystembackend.service.StudentRetakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentRetakeServiceImpl implements StudentRetakeService {

    @Autowired
    private StudentRetakeMapper studentRetakeMapper;

    @Override
    public String[] queryRetakeCourseNameList(String student) {
        return studentRetakeMapper.queryRetakeCourseNameList(student);
    }

    @Override
    public Float[] queryRetakeCourseCreditList(String student) {
        return studentRetakeMapper.queryRetakeCourseCreditList(student);
    }

    @Override
    public Float queryRetakeCourseCredit(String student, String course) {
        return studentRetakeMapper.queryRetakeCourseCredit(student, course);
    }

    @Override
    public List<StudentRetake> queryAllStudentRetakeCourseKind1(String student, String course, float credit) {
        return studentRetakeMapper.queryAllStudentRetakeCourseKind1(student, course, credit);
    }

    @Override
    public List<StudentRetake> queryAllStudentRetakeCourseKind3(String student, String course, float credit) {
        return studentRetakeMapper.queryAllStudentRetakeCourseKind3(student, course, credit);
    }

    @Override
    public List<StudentRetake> queryStudentRetakeCourseKind1(String student, String course, float credit) {
        return studentRetakeMapper.queryStudentRetakeCourseKind1(student, course, credit);
    }

    @Override
    public List<StudentRetake> queryStudentRetakeCourseKind3(String student, String course, float credit) {
        return studentRetakeMapper.queryStudentRetakeCourseKind3(student, course, credit);
    }
}
