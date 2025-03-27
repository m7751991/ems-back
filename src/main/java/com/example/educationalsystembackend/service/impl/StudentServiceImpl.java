package com.example.educationalsystembackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.educationalsystembackend.entity.StudentEntity;
import com.example.educationalsystembackend.mapper.GradeMapper;
import com.example.educationalsystembackend.mapper.StudentMapper;
import com.example.educationalsystembackend.mapper.UserMapper;
import com.example.educationalsystembackend.pojo.Student;
import com.example.educationalsystembackend.pojo.User;
import com.example.educationalsystembackend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentEntity> implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private GradeMapper gradeMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addStuent(Student student) {
        studentMapper.addStuent(student);
        int number = gradeMapper.queryNumberByGrade(student.getGrade());
        gradeMapper.alterNumberByGrade(student.getGrade(), number + 1); //对应班级人数+1
        User user = new User(student.getId(), "123456"); //密码默认123456
        userMapper.addUser(user, 2); //学生权限标记为2
    }

    @Override
    public List<Student> queryAllStudent(int num, int size) {
        return studentMapper.queryAllStudent(num, size);
    }

    @Override
    public int queryAllStudentCount() {
        return studentMapper.queryAllStudentCount();
    }

    @Override
    public void deleteStudent(String id) {
        studentMapper.deleteStudent(id);
    }

    @Override
    public List<Student> queryStudent(String id, String grade, int num, int size) {
        return studentMapper.queryStudent(id, grade, num, size);
    }

    @Override
    public int queryStudentCount(String id) {
        return studentMapper.queryStudentCount(id);
    }

    @Override
    public int queryGradeStudentCount(String id, String grade) {
        return studentMapper.queryGradeStudentCount(id, grade);
    }


    @Override
    public void updateStudent(Student student) {
        studentMapper.updateStudent(student);
    }

    @Override
    public String queryStudentIdByName(String name) {
        return studentMapper.queryStudentIdByName(name);
    }

    @Override
    public String queryStudentNameById(String id) {
        return studentMapper.queryStudentNameById(id);
    }

    @Override
    public List<String> queryStudentIdByGrade(String grade) {
        return studentMapper.queryStudentIdByGrade(grade);
    }

    @Override
    public List<Student> queryStudentExcelData(String grade) {
        return studentMapper.queryStudentExcelData(grade);
    }
}