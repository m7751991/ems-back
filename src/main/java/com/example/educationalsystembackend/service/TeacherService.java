package com.example.educationalsystembackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.educationalsystembackend.entity.TeacherEntity;
import com.example.educationalsystembackend.pojo.Teacher;

import java.util.List;
import java.util.Map;

public interface TeacherService extends IService<TeacherEntity> {

    void addTeacher(Teacher teacher);

    List<Teacher> queryAllTeacher(int num, int size);

    int queryAllTeacherCount();

    void deleteTeacher(String id);

    List<Teacher> queryTeacher(String id);

    int queryTeacherCount(String id);

    void updateTeacher(Teacher teacher);

    List<Map<String, Object>> queryAllTeacherNameByCourse(String course);

    List<Map<String, Object>> queryAllTeacherIdAndName();

    String queryTeacherNameById(String id);

    String queryTeacherIdByName(String name);

    List<Map<String, Object>> queryStudentChoiceTeacher(String student);

    List<Map<String, Object>> queryStudentChoiceTeacherByCourse(String student, String course);

    List<Teacher> queryTeacherExcelData();

    /**
     * 生成唯一工号
     * 
     * @return 自动生成的工号
     */
    String generateTeacherId();

    void deleteTeachers(String ids);
}
