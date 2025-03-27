package com.example.educationalsystembackend.controller;

import com.example.educationalsystembackend.pojo.RetakeCourse;
import com.example.educationalsystembackend.pojo.Status;
import com.example.educationalsystembackend.result.Result;
import com.example.educationalsystembackend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@Transactional
public class RetakeCourseController {

    @Autowired
    private RetakeCourseService retakeCourseService;

    @Autowired
    private ChoiceService choiceService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private ElectiveCourseService electiveCourseService;

    /**
     * 查询所有选修课
     *
     * @param num  页面数
     * @param size 页面大小
     * @return Response
     */
    @GetMapping("/queryAllRetakeCourse")
    public Result queryAllRetakeCourse(int num, int size) {
        num = size * (num - 1);
        List<RetakeCourse> retakeCourseList = retakeCourseService.queryAllRetakeCourse(num, size);
        for (RetakeCourse retakeCourse : retakeCourseList) {
            retakeCourse.setChoiceNumber(choiceService.queryChoiceNumber(retakeCourse.getId()));
        }
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("retakeCourseList", retakeCourseList);
        map.put("count", retakeCourseService.queryAllRetakeCourseCount());
        map.put("retakeStatus", Status.retakeStatus);
        return Result.success(200, "查询成功", map);
    }

    /**
     * 查询所有选修课
     *
     * @param name 课程名
     * @param num  页面数
     * @param size 页面大小
     * @return Response
     */
    @GetMapping("/queryRetakeCourse")
    public Result queryRetakeCourse(String name, int num, int size) {
        num = size * (num - 1);
        List<RetakeCourse> retakeCourseList = retakeCourseService.queryRetakeCourse(name, num, size);
        for (RetakeCourse retakeCourse : retakeCourseList) {
            retakeCourse.setChoiceNumber(choiceService.queryChoiceNumber(retakeCourse.getId()));
        }
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("retakeCourseList", retakeCourseList);
        map.put("count", retakeCourseService.queryRetakeCourseCount(name));
        return Result.success(200, "查询成功", map);
    }

    /**
     * 添加重修课
     *
     * @param retakeCourse 重修课
     * @return Response
     */
    @PostMapping("/addRetakeCourse")
    public Result addRetakeCourse(@RequestBody RetakeCourse retakeCourse) {
        if (retakeCourseService.queryRetakeCourseMoreDateNumber(retakeCourse) != 0)
            return Result.success(400, "该老师当前时间已安排课程", null);
        if (classroomService.queryClassroomMoreDateNumber(retakeCourse.getId(), retakeCourse.getFrom(), retakeCourse.getTo(), retakeCourse.getWeek(), retakeCourse.getStart(), retakeCourse.getEnd(), retakeCourse.getClassroom()) != 0)
            return Result.success(400, "当前教室已有课程安排", null);
        if (retakeCourseService.queryRetakeCourseCount(retakeCourse.getId()) == 0) {
            retakeCourse.setFlag(true);
            retakeCourse.setScore(false);
            retakeCourse.setKind(3);
            retakeCourseService.addRetakeCourse(retakeCourse);
            electiveCourseService.updateScoreProportion(20, 10, 70, retakeCourse.getId());
            return Result.success(200, "添加课程成功", null);
        } else {
            return Result.success(400, "添加失败，当前课程号已存在", null);
        }
    }

    /**
     * 删除重修课程
     *
     * @param course 课程号
     * @return Response
     */
    @GetMapping("/deleteRetakeCourse")
    public Result deleteRetakeCourse(String course) {
        retakeCourseService.deleteRetakeCourse(course);
        return Result.success(200, "删除课程成功", null);
    }

    /**
     * @param retakeCourse 重修课
     * @return Response
     */
    @PostMapping("/updateRetakeCourse")
    public Result updateRetakeCourse(@RequestBody RetakeCourse retakeCourse) {
        retakeCourse.setTeacher(teacherService.queryTeacherIdByName(retakeCourse.getTeacher()));
        if (retakeCourseService.queryRetakeCourseMoreDateNumber(retakeCourse) != 0)
            return Result.success(400, "该老师当前时间已安排课程", null);
        retakeCourse.setClassroom(classroomService.queryClassroomIdByName(retakeCourse.getClassroom()));
        if (classroomService.queryClassroomMoreDateNumber(retakeCourse.getId(), retakeCourse.getFrom(), retakeCourse.getTo(), retakeCourse.getWeek(), retakeCourse.getStart(), retakeCourse.getEnd(), retakeCourse.getClassroom()) != 0)
            return Result.success(400, "当前教室已有课程安排", null);
        retakeCourseService.updateRetakeCourse(retakeCourse);
        return Result.success(200, "修改课程成功", null);
    }

    /**
     * 开启重修课
     *
     * @return Response
     */
    @GetMapping("/startRetakeCourse")
    public Result startRetakeCourse() {
        Status.retakeStatus = !Status.retakeStatus;
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("retakeStatus", Status.retakeStatus);
        return Result.success(200, "开启重修成功", map);
    }

    /**
     * 关闭重修课
     *
     * @return Response
     */
    @GetMapping("/closeRetakeCourse")
    public Result closeRetakeCourse() {
        Status.retakeStatus = !Status.retakeStatus;
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("retakeStatus", Status.retakeStatus);
        return Result.success(200, "关闭重修成功", map);
    }
}