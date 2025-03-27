package com.example.educationalsystembackend.service;

import com.example.educationalsystembackend.pojo.Schedule;
import com.example.educationalsystembackend.pojo.StudentChoice;

import java.util.List;

public interface StudentChoiceService {

    List<StudentChoice> quyeryAllStudentChoice(int num, int size);

    int queryOpenElectiveCourseCount();

    List<String> queryStudentChoiceCourseById(String id);

    void deleteStudentChoice(String student, String course);

    void addStudentChoice(String student, String course);

    List<Schedule> queryScheduleById(String id);

    int queryStudentChoiceCount(String id, int week, int start, int end);

    List<StudentChoice> queryStudentChoiceByCourse(String course, int num, int size);

    int queryChoiceCountByStudent(String student);

    int queryStudentElectiveCourseNumber(String student);
}
