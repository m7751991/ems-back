/*
 * @Descripttion: 
 * @version: 
 * @Author: zixi
 * @Date: 2025-03-27 00:12:47
 * @LastEditors: zixi
 * @LastEditTime: 2025-03-28 23:53:57
 */
package com.example.educationalsystembackend.controller;

import com.example.educationalsystembackend.result.Result;
import com.example.educationalsystembackend.service.GradeService;
import com.example.educationalsystembackend.service.RequiredCourseService;
import com.example.educationalsystembackend.service.TeacherExamService;
import com.example.educationalsystembackend.util.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@Transactional
public class TeacherExamController {

    @Autowired
    private TeacherExamService teacherExamService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private RequiredCourseService requiredCourseService;

    /**
     * 教师查看所有考试
     *
     * @param num  页面数
     * @param size 页面大小
     * @return Response
     */
    @GetMapping("/queryAllTeacherExam")
    public Result queryAllTeacherExam(int num, int size) {
        num = size * (num - 1);
        Map<String, Object> map = new LinkedHashMap<>();
        String teacher = JWT.token(httpServletRequest.getHeader("Authorization"));
        map.put("teacherExamList", teacherExamService.queryAllTeacherExam(teacher, num, size));
        map.put("courseList", requiredCourseService.queryTeacherRequiredCourse(teacher));
        map.put("count", teacherExamService.queryAllTeacherExamCount(teacher));
        return Result.success(200, "查询成功", map);
    }

    /**
     * 教师查询考试
     *
     * @param course 课程
     * @param num    页面数
     * @param size   页面大小
     * @return Reponse
     */
    @GetMapping("/queryTeacherExam")
    public Result queryTeacherExam(String course, int num, int size) {
        String teacher = JWT.token(httpServletRequest.getHeader("Authorization"));
        int count = teacherExamService.queryTeacherExamCount(teacher, course);
        if (count == 0)
            return Result.success(400, "该课程还没有安排考试", null);
        else {
            num = size * (num - 1);
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("teacherExamList", teacherExamService.queryTeacherExam(teacher, course, num, size));
            map.put("count", count);
            return Result.success(200, "查询成功", map);
        }
    }

    /**
     * 教师查看所有监考
     *
     * @param num  页面数
     * @param size 页面大小
     * @return Response
     */
    @GetMapping("/queryAllSuperviseExam")
    public Result queryAllSuperviseExam(int num, int size) {
        num = size * (num - 1);
        Map<String, Object> map = new LinkedHashMap<>();
        String teacher = JWT.token(httpServletRequest.getHeader("Authorization"));
        map.put("superviseExamList", teacherExamService.queryAllSuperviseExam(teacher, num, size));
        map.put("gradeList", gradeService.querySuperviseExamGrade(teacher));
        map.put("count", teacherExamService.queryAllSuperviseExamCount(teacher));
        return Result.success(200, "查询成功", map);
    }

    /**
     * 教师查询选课
     *
     * @param grade 班级
     * @param num   页面数
     * @param size  页面大小
     * @return Response
     */
    @GetMapping("/querySuperviseExam")
    public Result querySuperviseExam(String grade, int num, int size) {
        String teacher = JWT.token(httpServletRequest.getHeader("Authorization"));
        int count = teacherExamService.querySuperviseExamCount(teacher, grade);
        if (count == 0)
            return Result.success(400, "您没有安排该班的监考", null);
        else {
            num = size * (num - 1);
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("superviseExamList", teacherExamService.querySuperviseExam(teacher, grade, num, size));
            map.put("count", count);
            return Result.success(200, "查询成功", map);
        }
    }
}
