package com.example.educationalsystembackend.service;

import com.example.educationalsystembackend.pojo.Schedule;
import com.example.educationalsystembackend.pojo.TeacherChoice;

import java.util.List;

public interface TeacherChoiceService {

    List<TeacherChoice> queryAllTeacherChoice(String teacher, int num, int size);

    int queryAllTeacherChoiceCount(String teacher);

    List<TeacherChoice> queryTeacherChoice(String teacher, String course, int num, int size);

    int queryTeacherChoiceCount(String teacher, String course);

    void deleteTeacherChoice(String student, String course);

    List<Schedule> queryTeacherSchedule(String teacher);
}
