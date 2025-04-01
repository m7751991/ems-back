package com.example.educationalsystembackend.controller;

import com.example.educationalsystembackend.pojo.Teacher;
import com.example.educationalsystembackend.pojo.User;
import com.example.educationalsystembackend.result.Result;
import com.example.educationalsystembackend.service.TeacherService;
import com.example.educationalsystembackend.service.UserService;
import com.example.educationalsystembackend.util.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@Transactional
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 查询所有老师
     *
     * @param num  页面数
     * @param size 页面大小
     * @return Response
     */
    @GetMapping("/queryAllTeacher")
    public Result queryAllTeacher(int num, int size) {
        num = size * (num - 1);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("teacherList", teacherService.queryAllTeacher(num, size));
        map.put("count", teacherService.queryAllTeacherCount());
        return Result.success(200, "查询成功", map);
    }

    /**
     * 添加教师
     *
     * @param teacher 教师
     * @return Response
     */
    @PostMapping("/addTeacher")
    public Result addTeacher(@RequestBody Teacher teacher) {
        String userName = teacherService.generateTeacherId();
        teacher.setId(userName);
        teacher.setUserName(userName);
        teacher.setPassword(userName);
        System.out.println("添加教师: " + teacher);
        teacherService.addTeacher(teacher);
        User user = new User();
        user.setAccount(userName);
        user.setPassword(userName);
        System.out.println("添加用户: " + user);
        userService.addUser(user, 3);
        return Result.success(200, "添加教师成功", null);
    }

    /**
     * 删除老师
     *
     * @param ids 老师工号
     * @return Response
     */
    @GetMapping("/deleteTeachers")
    public Result deleteTeachers(String ids) {
        System.out.println("删除教师: " + ids);
        teacherService.deleteTeachers(ids);
        userService.deleteUsers(ids);
        return Result.success(200, "删除成功", null);
    }
    /**
     * 删除老师
     *
     * @param id 老师工号
     * @return Response
     */
    @GetMapping("/deleteTeacher")
    public Result deleteTeacher(String id) {
        teacherService.deleteTeacher(id);
        userService.deleteUser(id);
        return Result.success(200, "删除成功", null);
    }

    /**
     * 查询老师
     *
     * @param id 老师工号
     * @return Response
     */
    @GetMapping("/queryTeacher")
    public Result queryTeacher(String id) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("teacherList", teacherService.queryTeacher(id));
        map.put("count", teacherService.queryTeacherCount(id));
        return Result.success(200, "查询成功", map);
    }

    /**
     * 修改教师
     *
     * @param teacher 教师
     * @return Response
     */
    @PostMapping("/alterTeacher")
    public Result alterTeacher(@RequestBody Teacher teacher) {
        teacherService.updateTeacher(teacher);
        return Result.success(200, "修改成功", null);
    }

    /**
     * 查询所有老师工号和姓名
     *
     * @return Response
     */
    @GetMapping("/queryAllTeacherIdAndName")
    public Result queryAllTeacherIdAndName() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("teacherList", teacherService.queryAllTeacherIdAndName());
        return Result.success(200, "查询成功", map);
    }

    /**
     * 通过课程查询老师
     *
     * @param course 课程
     * @return Response
     */
    @GetMapping("/queryChoiceTeacherByCourse")
    public Result queryChoiceGradeByCourse(String course) {
        String student = JWT.token(httpServletRequest.getHeader("Authorization"));
        if (course.equals(""))
            course = "%";
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("teacherList", teacherService.queryStudentChoiceTeacherByCourse(student, course));
        return Result.success(200, "查询成功", map);
    }

    @GetMapping("/queryTeacherExcelData")
    public Result queryTeacherExcelData() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("excelData", teacherService.queryTeacherExcelData());
        return Result.success(200, "导出成功", map);
    }

    @PostMapping("/uploadTeacher")
    public Result uploadTeacher(@RequestBody List<Teacher> teacherList) {
        for (Teacher teacher : teacherList) {
            User user = new User(teacher.getId(), teacher.getId().substring(4, 6)); // 截取学号后两位作为学生登录密码
            userService.addUser(user, 3); // 教师权限标记为3
            teacherService.addTeacher(teacher);
        }
        return Result.success(200, "导入成功", null);
    }
}
