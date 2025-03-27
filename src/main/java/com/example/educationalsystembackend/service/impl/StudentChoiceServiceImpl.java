package com.example.educationalsystembackend.service.impl;

import com.example.educationalsystembackend.mapper.StudentChoiceMapper;
import com.example.educationalsystembackend.pojo.Schedule;
import com.example.educationalsystembackend.pojo.StudentChoice;
import com.example.educationalsystembackend.service.StudentChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentChoiceServiceImpl implements StudentChoiceService {

    @Autowired
    private StudentChoiceMapper studentChoiceMapper;

    @Override
    public List<StudentChoice> quyeryAllStudentChoice(int num, int size) {
        return studentChoiceMapper.quyeryAllStudentChoice(num, size);
    }

    @Override
    public int queryOpenElectiveCourseCount() {
        return studentChoiceMapper.queryOpenElectiveCourseCount();
    }

    @Override
    public List<String> queryStudentChoiceCourseById(String id) {
        return studentChoiceMapper.queryStudentChoiceCourseById(id);
    }

    @Override
    public void deleteStudentChoice(String student, String course) {
        studentChoiceMapper.deleteStudentChoice(student, course);
    }

    @Override
    public void addStudentChoice(String student, String course) {
        studentChoiceMapper.addStudentChoice(student, course);
    }

    @Override
    public List<Schedule> queryScheduleById(String id) {
        return studentChoiceMapper.queryScheduleById(id);
    }

    @Override
    public int queryStudentChoiceCount(String id, int week, int start, int end) {
        return studentChoiceMapper.queryStudentChoiceCount(id, week, start, end);
    }


    @Override
    public List<StudentChoice> queryStudentChoiceByCourse(String course, int num, int size) {
        return studentChoiceMapper.queryStudentChoiceByCourse(course, num, size);
    }

    @Override
    public int queryChoiceCountByStudent(String student) {
        return studentChoiceMapper.queryChoiceCountByStudent(student);
    }

    @Override
    public int queryStudentElectiveCourseNumber(String student) {
        return studentChoiceMapper.queryStudentElectiveCourseNumber(student);
    }
}
