package com.example.educationalsystembackend.controller;

import com.example.educationalsystembackend.pojo.AdjustCourse;
import com.example.educationalsystembackend.result.Result;
import com.example.educationalsystembackend.service.TeacherAdjustCourseService;
import com.example.educationalsystembackend.util.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@Transactional
public class TeacherAdjustController {

    @Autowired
    private TeacherAdjustCourseService teacherAdjustCourseService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping("/queryAllTeacherAdjustCourse")
    public Result queryAllTeacherAdjustCourse(int num, int size) {
        String teacher = JWT.token(httpServletRequest.getHeader("Authorization"));
        num = size * (num - 1);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("teacherAdjustCourseList", teacherAdjustCourseService.queryAllTeacherAdjustCourse(teacher, num, size));
        map.put("count", teacherAdjustCourseService.queryAllTeacherAdjustCourseCount(teacher));
        map.put("courseList", teacherAdjustCourseService.queryTeacherAdjustCourseList(teacher));
        return Result.success(200, "查询调课申请成功", map);
    }

    @PostMapping("/queryTeacherAdjustCourse/{num}/{size}")
    public Result queryAdjustCourse(@RequestBody AdjustCourse adjustCourse, @PathVariable("num") int num, @PathVariable("size") int size) {
        String teacher = JWT.token(httpServletRequest.getHeader("Authorization"));
        if (adjustCourse.getCourse().equals("")) adjustCourse.setCourse("%");
        num = size * (num - 1);
        int count = teacherAdjustCourseService.queryTeacherAdjustCourseCount(teacher, adjustCourse.getCourse(), adjustCourse.getResultList());
        Map<String, Object> map = new LinkedHashMap<>();
        if (count == 0) return Result.success(500, "没有查到该调课申请", null);
        else {
            map.put("teacherAdjustCourseList", teacherAdjustCourseService.queryTeacherAdjustCourse(teacher, adjustCourse.getCourse(), adjustCourse.getResultList(), num, size));
            map.put("count", count);
            return Result.success(200, "查询成功", map);
        }
    }

    @GetMapping("/queryTeacherAdjustCourseForm/{id}")
    public Result queryTeacherAdjustCourseForm(@PathVariable("id") Long id) {
        AdjustCourse adjustCourse = teacherAdjustCourseService.queryTeacherAdjustCourseForm(id);
        adjustCourse.setFromList(Arrays.asList(adjustCourse.getFrom().split(",")));
        adjustCourse.setToList(Arrays.asList(adjustCourse.getTo().split(",")));
        adjustCourse.setWeekList(Arrays.asList(adjustCourse.getWeek().split(",")));
        adjustCourse.setLessonList(Arrays.asList(adjustCourse.getLesson().split(",")));
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("form", adjustCourse);
        map.put("from", teacherAdjustCourseService.queryAdjustCourseFrom(id));
        map.put("to", teacherAdjustCourseService.queryAdjustCourseTo(id));
        return Result.success(200, "查询成功", map);
    }

    @PostMapping("/updateAdjustCourse")
    public Result updateAdjustCourse(@RequestBody AdjustCourse adjustCourse) {
        adjustCourse.getFromList().sort((o1, o2) -> {
            Integer num1 = Integer.parseInt(o1);
            Integer num2 = Integer.parseInt(o2);
            return num1.compareTo(num2);
        });
        adjustCourse.getToList().sort((o1, o2) -> {
            Integer num1 = Integer.parseInt(o1);
            Integer num2 = Integer.parseInt(o2);
            return num1.compareTo(num2);
        });
        adjustCourse.getWeekList().sort((o1, o2) -> {
            Integer num1 = Integer.parseInt(o1);
            Integer num2 = Integer.parseInt(o2);
            return num1.compareTo(num2);
        });
        adjustCourse.getLessonList().sort((o1, o2) -> {
            Integer num1 = Integer.parseInt(o1);
            Integer num2 = Integer.parseInt(o2);
            return num1.compareTo(num2);
        });
        adjustCourse.setFrom(String.join(",", adjustCourse.getFromList()));
        adjustCourse.setTo(String.join(",", adjustCourse.getToList()));
        adjustCourse.setWeek(String.join(",", adjustCourse.getWeekList()));
        adjustCourse.setLesson(String.join(",", adjustCourse.getLessonList()));
        teacherAdjustCourseService.updateAdjustCourse(adjustCourse);
        return Result.success(200, "修改调课成功", null);
    }

    @GetMapping("/deleteTeacherAdjustCourse/{id}")
    public Result deleteTeacherAdjustCourse(@PathVariable("id") Long id) {
        teacherAdjustCourseService.deleteTeacherAdjustCourse(id);
        return Result.success(200, "删除调课申请成功", null);
    }
}
