package com.example.educationalsystembackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.educationalsystembackend.entity.StudentEntity;
import com.example.educationalsystembackend.pojo.Student;

import java.util.List;

public interface StudentService extends IService<StudentEntity> {

    void addStuent(Student student);

    List<Student> queryAllStudent(int num, int size);

    int queryAllStudentCount();

    void deleteStudent(String id);

    List<Student> queryStudent(String id, String grade, int num, int size);

    int queryStudentCount(String id);

    int queryGradeStudentCount(String id, String grade);

    void updateStudent(Student student);

    String queryStudentIdByName(String name);

    String queryStudentNameById(String id);

    List<String> queryStudentIdByGrade(String grade);

    List<Student> queryStudentExcelData(String grade);
}

