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

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Arrays;
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
        // 自动生成学号
        String studentId = generateStudentId();
        student.setId(studentId);
        student.setUserName(studentId);
        student.setPassword(studentId);
        studentMapper.addStuent(student);
        int number = gradeMapper.queryNumberByGrade(student.getGrade());
        gradeMapper.alterNumberByGrade(student.getGrade(), number + 1); // 对应班级人数+1
        User user = new User();
        user.setAccount(student.getUserName());
        user.setPassword(student.getPassword());
        userMapper.addUser(user, 2); // 学生权限标记为2
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

    @Override
    public String generateStudentId() {
        // 获取年份前两位
        String yearPrefix = String.valueOf(LocalDate.now().getYear()).substring(2);
        // 生成一个6位随机数
        Random random = new Random();
        int randomNum = 100000 + random.nextInt(900000);
        // 组合成学号：年份(2位) + 随机数(6位)
        return yearPrefix + randomNum;
    }

    @Override
    public void deleteStudents(String ids) {
        List<String> idsToDelete = Arrays.asList(ids.split(","));
        studentMapper.deleteStudents(idsToDelete);
    }
}