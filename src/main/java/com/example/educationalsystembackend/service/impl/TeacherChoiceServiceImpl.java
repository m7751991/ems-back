package com.example.educationalsystembackend.service.impl;

import com.example.educationalsystembackend.mapper.TeacherChoiceMapper;
import com.example.educationalsystembackend.pojo.Schedule;
import com.example.educationalsystembackend.pojo.TeacherChoice;
import com.example.educationalsystembackend.service.TeacherChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherChoiceServiceImpl implements TeacherChoiceService {

    @Autowired
    private TeacherChoiceMapper teacherChoiceMapper;

    @Override
    public List<TeacherChoice> queryAllTeacherChoice(String teacher, int num, int size) {
        return teacherChoiceMapper.queryAllTeacherChoice(teacher, num, size);
    }

    @Override
    public int queryAllTeacherChoiceCount(String teacher) {
        return teacherChoiceMapper.queryAllTeacherChoiceCount(teacher);
    }

    @Override
    public List<TeacherChoice> queryTeacherChoice(String teacher, String course, int num, int size) {
        return teacherChoiceMapper.queryTeacherChoice(teacher, course, num, size);
    }

    @Override
    public int queryTeacherChoiceCount(String teacher, String course) {
        return teacherChoiceMapper.queryTeacherChoiceCount(teacher, course);
    }

    @Override
    public void deleteTeacherChoice(String student, String course) {
        teacherChoiceMapper.deleteTeacherChoice(student, course);
    }

    @Override
    public List<Schedule> queryTeacherSchedule(String teacher) {
        return teacherChoiceMapper.queryTeacherSchedule(teacher);
    }

}
