package com.example.educationalsystembackend.controller;

import com.example.educationalsystembackend.pojo.TeacherChoice;
import com.example.educationalsystembackend.result.Result;
import com.example.educationalsystembackend.service.*;
import com.example.educationalsystembackend.util.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@Transactional
public class TeacherChoiceController {

    @Autowired
    private TeacherChoiceService teacherChoiceService;

    @Autowired
    private ElectiveCourseService courseService;

    @Autowired
    private TeacherCourseService teacherCourseService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    /**
     * 教师查询所有选课
     *
     * @param num  页面数
     * @param size 页面大小
     * @return Response
     */
    @GetMapping("/queryAllTeacherChoice")
    public Result queryAllTeacherChoice(int num, int size) {
        String teacher = JWT.token(httpServletRequest.getHeader("Authorization"));
        num = size * (num - 1);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("teacherChoiceList", teacherChoiceService.queryAllTeacherChoice(teacher, num, size));
        map.put("count", teacherChoiceService.queryAllTeacherChoiceCount(teacher));
        map.put("teacherCourseList", teacherCourseService.queryTeacherCourseIdAndName(teacher));
        return Result.success(200, "查询成功", map);
    }

    /**
     * 教师删除选课
     *
     * @param teacherChoice 教师选课
     * @return Response
     */
    @PostMapping("/deleteTeacherChoice")
    public Result deleteTeacherChoice(@RequestBody TeacherChoice teacherChoice) {
        teacherChoiceService.deleteTeacherChoice(teacherChoice.getStudent(), teacherChoice.getCourse());
        return Result.success(200, "删除成功", null);
    }

    /**
     * 教师查询选课
     *
     * @param course 课程
     * @param num    页面数
     * @param size   页面大小
     * @return Response
     */
    @GetMapping("/queryTeacherChoice")
    public Result queryTeacherChoice(String course, int num, int size) {
        String teacher = JWT.token(httpServletRequest.getHeader("Authorization"));
        int count = teacherChoiceService.queryTeacherChoiceCount(teacher, course);
        if (count == 0)
            return Result.success(400, "没有学生选择该课程", null);
        else {
            num = size * (num - 1);
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("teacherChoiceList", teacherChoiceService.queryTeacherChoice(teacher, course, num, size));
            map.put("count", count);
            return Result.success(200, "查询成功", map);
        }
    }

    /**
     * 教师查询课表
     *
     * @return Response
     */
    @GetMapping("/queryTeacherSchedule")
    public Result queryTeacherSchedule() {
        String teacher = JWT.token(httpServletRequest.getHeader("Authorization"));
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("scheduleList", teacherChoiceService.queryTeacherSchedule(teacher));
        return Result.success(200, "查询成功", map);
    }
}
