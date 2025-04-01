package com.example.educationalsystembackend.controller;

import com.example.educationalsystembackend.pojo.Student;
import com.example.educationalsystembackend.pojo.User;
import com.example.educationalsystembackend.result.Result;
import com.example.educationalsystembackend.service.GradeService;
import com.example.educationalsystembackend.service.StudentService;
import com.example.educationalsystembackend.service.UserService;
import com.example.educationalsystembackend.validated.AddStudentGroup;
import com.example.educationalsystembackend.validated.UpdateStudentGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

@RestController
@Transactional
@Slf4j
@Validated
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private UserService userService;

    /**
     * 查询所有学生
     *
     * @param num  页面数
     * @param size 页面大小
     * @return Response
     */
    @GetMapping("/queryAllStudent")
    public Result queryAllStudent(int num, int size) {
        num = size * (num - 1);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("studentList", studentService.queryAllStudent(num, size));
        map.put("gradeList", gradeService.queryAllGradeIdAndName());
        map.put("count", studentService.queryAllStudentCount());
        return Result.success(200, "查询成功", map);
    }

    /**
     * 添加学生
     *
     * @param student 学生
     * @return Response
     */
    @PostMapping("/addStudent")
    public Result addStudent(@Validated(value = { AddStudentGroup.class }) @RequestBody Student student) {
        studentService.addStuent(student);
        return Result.success(200, "添加学生成功", null);
    }

    /**
     * 删除学生
     *
     * @param id 学号
     * @return Response
     */
    @GetMapping("/deleteStudent")
    public Result deleteStudent(@NotBlank(message = "学号不能为空") String id) {
        userService.deleteUser(id); // 用户表中删除该学生
        String grade = gradeService.queryGradeByStudent(id);
        int number = gradeService.queryNumberByGrade(grade);
        gradeService.alterNumberByGrade(grade, number - 1); // 对应班级人数-1
        studentService.deleteStudent(id); // 学生表中删除
        return Result.success(200, "删除成功", null);
    }

    /**
     * 批量删除学生
     *
     * @param ids 学号
     * @return Response
     */
    @GetMapping("/deleteStudents")
    public Result deleteStudents(String ids) {
        System.out.println("删除学生: " + ids);
        List<String> idsToDelete = Arrays.asList(ids.split(","));
        for (String id : idsToDelete) {
            String grade = gradeService.queryGradeByStudent(id);
            int number = gradeService.queryNumberByGrade(grade);
            gradeService.alterNumberByGrade(grade, number - 1); // 对应班级人数-1
        }
        userService.deleteUsers(ids); // 用户表中删除
        studentService.deleteStudents(ids); // 学生表中删除
        return Result.success(200, "删除成功", null);
    }

    /**
     * 查询学生
     *
     * @param id 学号
     * @return Response
     */
    @GetMapping("/queryStudent")
    public Result queryStudent(String id, String grade, int num, int size) {
        num = size * (num - 1);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("studentList", studentService.queryStudent(id, grade, num, size));
        map.put("count", studentService.queryGradeStudentCount(id, grade));
        return Result.success(200, "查询成功", map);
    }

    /**
     * 修改学生
     *
     * @param student 学生
     * @return Response
     */
    @PostMapping("/alterStudent")
    public Result alterStudent(@Validated({ UpdateStudentGroup.class }) @RequestBody Student student) {

        String grade = gradeService.queryGradeByStudent(student.getId()); // 查询学生所在班级id
        // 获取修改前的班级人数
        int number = gradeService.queryNumberByGrade(grade);
        // 修改前班级人数-1
        gradeService.alterNumberByGrade(grade, number - 1);
        // 当前要修改的班级id
        grade = student.getGrade();
        // 修改后班级人数+1
        number = gradeService.queryNumberByGrade(grade);
        gradeService.alterNumberByGrade(grade, number + 1);
        // student.setGrade(gradeService.queryGradeIdByName(grade));
        studentService.updateStudent(student);
        return Result.success(200, "修改成功", null);
    }

    @GetMapping("/queryStudentExcelData")
    public Result queryStudentExcelData(String grade) {
        if (grade == null || grade.equals(""))
            grade = "%";
        else
            grade = gradeService.queryGradeIdByName(grade);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("excelData", studentService.queryStudentExcelData(grade));
        return Result.success(200, "导出成功", map);
    }

    @PostMapping("/uploadStudent")
    public Result uploadStudent(@RequestBody List<Student> studentList) {
        for (Student student : studentList) {
            User user = new User(student.getId(), student.getId().substring(6, 12)); // 截取学号后六位作为学生登录密码
            userService.addUser(user, 2); // 学生权限标记为2
            student.setGrade(gradeService.queryGradeIdByName(student.getGrade()));
            studentService.addStuent(student);
            int number = gradeService.queryNumberByGrade(student.getGrade());
            gradeService.alterNumberByGrade(student.getGrade(), number + 1); // 对应班级人数-1
        }
        return Result.success(200, "导入成功", null);
    }
}
