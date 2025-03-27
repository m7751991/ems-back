package com.example.educationalsystembackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.educationalsystembackend.entity.TeacherEntity;
import com.example.educationalsystembackend.mapper.TeacherMapper;
import com.example.educationalsystembackend.pojo.Teacher;
import com.example.educationalsystembackend.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, TeacherEntity> implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public void addTeacher(Teacher teacher) {
        teacherMapper.addTeacher(teacher);
    }

    @Override
    public List<Teacher> queryAllTeacher(int num, int size) {
        return teacherMapper.queryAllTeacher(num, size);
    }

    @Override
    public int queryAllTeacherCount() {
        return teacherMapper.queryAllTeacherCount();
    }

    @Override
    public void deleteTeacher(String id) {
        teacherMapper.deleteTeacher(id);
    }

    @Override
    public List<Teacher> queryTeacher(String id) {
        return teacherMapper.queryTeacher(id);
    }

    @Override
    public int queryTeacherCount(String id) {
        return teacherMapper.queryTeacherCount(id);
    }


    @Override
    public void updateTeacher(Teacher teacher) {
        teacherMapper.updateTeacher(teacher);
    }

    @Override
    public List<Map<String, Object>> queryAllTeacherNameByCourse(String course) {
        return teacherMapper.queryAllTeacherNameByCourse(course);
    }

    @Override
    public List<Map<String, Object>> queryAllTeacherIdAndName() {
        return teacherMapper.queryAllTeacherIdAndName();
    }

    @Override
    public String queryTeacherNameById(String id) {
        return teacherMapper.queryTeacherNameById(id);
    }

    @Override
    public String queryTeacherIdByName(String name) {
        return teacherMapper.queryTeacherIdByName(name);
    }

    @Override
    public List<Map<String, Object>> queryStudentChoiceTeacher(String student) {
        return teacherMapper.queryStudentChoiceTeacher(student);
    }

    @Override
    public List<Map<String, Object>> queryStudentChoiceTeacherByCourse(String student, String course) {
        return teacherMapper.queryStudentChoiceTeacherByCourse(student, course);
    }

    @Override
    public List<Teacher> queryTeacherExcelData() {
        return teacherMapper.queryTeacherExcelData();
    }

}
