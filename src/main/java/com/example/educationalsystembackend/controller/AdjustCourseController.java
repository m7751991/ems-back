package com.example.educationalsystembackend.controller;

import com.example.educationalsystembackend.pojo.AdjustCourse;
import com.example.educationalsystembackend.result.Result;
import com.example.educationalsystembackend.service.AdjustCourseService;
import com.example.educationalsystembackend.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@Transactional
@RestController
public class AdjustCourseController {

    @Autowired
    private AdjustCourseService adjustCourseService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/queryAllAdjustCourse")
    public Result queryAllChoice(int num, int size) {
        num = size * (num - 1);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("adjustCourseList", adjustCourseService.queryAllAdjustCourse(num, size));
        map.put("count", adjustCourseService.queryAllAdjustCourseCount());
        map.put("teacherList", teacherService.queryAllTeacherIdAndName());
        return Result.success(200, "查询调课申请成功", map);
    }

    @GetMapping("/queryAdjustCourse")
    public Result queryAdjustCourse(int num, int size, String teacher, String result) {
        if (teacher.equals(""))
            teacher = "%";
        if (result.equals(""))
            result = "%";
        int count = adjustCourseService.queryAdjustCourseCount(teacher, result);
        if (count == 0)
            return Result.success(500, "没有查到该老师的调课申请", null);
        num = size * (num - 1);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("adjustCourseList", adjustCourseService.queryAdjustCourse(num, size, teacher, result));
        map.put("count", count);
        return Result.success(200, "查询调课申请成功", map);
    }

    @PostMapping("/agreeAdjustCourse")
    public Result agreeAdjustCourse(@RequestBody AdjustCourse adjustCourse) {
        for (Long id : adjustCourse.getIdList())
            adjustCourseService.agreeAdjustCourse(id);
        return Result.success(200, "同意申请成功", null);
    }

    @PostMapping("/refuseAdjustCourse")
    public Result refuseAdjustCourse(@RequestBody AdjustCourse adjustCourse) {
        for (Long id : adjustCourse.getIdList())
            adjustCourseService.refuseAdjustCourse(id);
        return Result.success(200, "拒绝申请成功", null);
    }
}
