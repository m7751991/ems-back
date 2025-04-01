/*
 * @Descripttion: 
 * @version: 
 * @Author: zixi
 * @Date: 2025-03-27 00:12:47
 * @LastEditors: zixi
 * @LastEditTime: 2025-03-28 14:25:19
 */
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
       /**
     * 生成唯一学号
     * @return 自动生成的学号
     */
    String generateStudentId();

    void deleteStudents(String ids);
}

