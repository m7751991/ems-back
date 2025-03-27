package com.example.educationalsystembackend.controller;

import com.example.educationalsystembackend.result.Result;
import com.example.educationalsystembackend.service.StudentCourseService;
import com.example.educationalsystembackend.util.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@Transactional
public class StudentCourseController {

    @Autowired
    private StudentCourseService studentCourseService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping("/queryAllStudentCourse")
    public Result queryAllStudentCourse(int num, int size) {
        String student = JWT.token(httpServletRequest.getHeader("Authorization"));
        num = size * (num - 1);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("studentCourseList", studentCourseService.queryAllStudentCourse(student, num, size));
        map.put("count", studentCourseService.queryAllStudentCourseCount(student));
        return Result.success(200, "查询成功", map);
    }

    @GetMapping("/queryStudentCourse/{course}/{num}/{size}")
    public Result queryStudentCourse(@PathVariable("course") String course, @PathVariable("num") int num, @PathVariable("size") int size) {
        String student = JWT.token(httpServletRequest.getHeader("Authorization"));
        int count = studentCourseService.queryStudentCourseCount(student, course);
        if (count == 0)
            return Result.success(400, "没有搜到该课程", null);
        else {
            num = size * (num - 1);
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("studentCourseList", studentCourseService.queryStudentCourse(student, course, num, size));
            map.put("count", count);
            return Result.success(200, "查询成功", map);
        }
    }
}

