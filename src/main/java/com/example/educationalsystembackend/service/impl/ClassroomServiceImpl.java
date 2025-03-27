package com.example.educationalsystembackend.service.impl;

import com.example.educationalsystembackend.mapper.ClassroomMapper;
import com.example.educationalsystembackend.pojo.Classroom;
import com.example.educationalsystembackend.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    @Autowired
    private ClassroomMapper classroomMapper;

    @Override
    public List<Classroom> queryAllClassroom(int num, int size) {
        return classroomMapper.queryAllClassroom(num, size);
    }

    @Override
    public int queryAllClassroomCount() {
        return classroomMapper.queryAllClassroomCount();
    }

    @Override
    public void addClassroom(Classroom classroom) {
        classroomMapper.addClassroom(classroom);
    }

    @Override
    public List<Classroom> queryClassroom(String id) {
        return classroomMapper.queryClassroom(id);
    }

    @Override
    public int queryClassroomCount(String id) {
        return classroomMapper.queryClassroomCount(id);
    }

    @Override
    public void deleteClassroom(String id) {
        classroomMapper.deleteClassroom(id);
    }

    @Override
    public void updateClassroom(Classroom classroom) {
        classroomMapper.updateClassroom(classroom);
    }

    @Override
    public List<Map<String, Object>> queryClassroomByNumber(int number) {
        return classroomMapper.queryClassroomByNumber(number);
    }

    @Override
    public String queryClassroomIdByName(String name) {
        return classroomMapper.queryClassroomIdByName(name);
    }

    @Override
    public int queryClassroomNumberByName(String name) {
        return classroomMapper.queryClassroomNumberByName(name);
    }

    @Override
    public List<Classroom> queryClassroomExcelData() {
        return classroomMapper.queryClassroomExcelData();
    }

    @Override
    public int queryClassroomMoreDateNumber(String course, Date from, Date to, int week, int start, int end, String classroom) {
        return classroomMapper.queryClassroomMoreDateNumber(course, from, to, week, start, end, classroom);
    }

    @Transactional
    @Override
    public void addClassrooms(List<Classroom> classroomList) {
        classroomMapper.addClassrooms(classroomList);
    }
}
