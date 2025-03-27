package com.example.educationalsystembackend.controller;

import com.example.educationalsystembackend.pojo.Schedule;
import com.example.educationalsystembackend.pojo.Status;
import com.example.educationalsystembackend.pojo.StudentChoice;
import com.example.educationalsystembackend.result.Result;
import com.example.educationalsystembackend.service.ChoiceService;
import com.example.educationalsystembackend.service.ElectiveCourseService;
import com.example.educationalsystembackend.service.StudentChoiceService;
import com.example.educationalsystembackend.util.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@Transactional
public class StudentChoiceController {

    @Autowired
    private StudentChoiceService studentChoiceService;

    @Autowired
    private ElectiveCourseService electiveCourseService;

    @Autowired
    private ChoiceService choiceService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 分页查询学生选课
     *
     * @param num  页面数
     * @param size 页面大小
     * @return Response
     */
    @GetMapping("/studentChoiceList")
    public Result studentChoiceList(int num, int size) {
        Map<String, Object> map = new LinkedHashMap<>();
        if (Status.choiceStatus) {
            return Result.success(400, "当前并未开启选课", null);
        }
        String student = JWT.token(httpServletRequest.getHeader("Authorization"));
        List<String> list = studentChoiceService.queryStudentChoiceCourseById(student);
        num = size * (num - 1);
        List<StudentChoice> studentChoiceList = studentChoiceService.quyeryAllStudentChoice(num, size);
        for (StudentChoice studentChoice : studentChoiceList) {
            int number = choiceService.queryChoiceNumber(studentChoice.getId());
            if (number == studentChoice.getNumber())
                studentChoice.setFlag(2);
            studentChoice.setNumber(studentChoice.getNumber() - number);
            for (String i : list) {
                if (i.equals(studentChoice.getId())) {
                    studentChoice.setFlag(1);
                }
            }
        }
        int number = studentChoiceService.queryStudentElectiveCourseNumber(student);
        if (number != 0) {
            for (StudentChoice studentChoice : studentChoiceList) {
                studentChoice.setDisabled(false);
            }
        }
        map.put("studentChoiceList", studentChoiceList);
        map.put("count", studentChoiceService.queryOpenElectiveCourseCount());
        return Result.success(200, "查询成功", map);
    }

    /**
     * 添加学生选课
     *
     * @param id 课程号
     * @return Response
     */
    @GetMapping("/addStudentChoice")
    public Result addStudentChoice(String id) {
        if (Status.choiceStatus) {
            return Result.success(500, "当前并未开启选课", null);
        }
        int week = electiveCourseService.queryElectiveCourseWeekById(id);
        int start = electiveCourseService.queryElectiveCourseStartById(id);
        int end = electiveCourseService.queryElectiveCourseEndById(id);
        String student = JWT.token(httpServletRequest.getHeader("Authorization"));
        int count = studentChoiceService.queryStudentChoiceCount(student, week, start, end);
        if (count == 0) {
            int number = electiveCourseService.queryElectiveCourseNumberById(id);
            if (number - choiceService.queryChoiceNumber(id) > 0) {
                studentChoiceService.addStudentChoice(student, id);
                return Result.success(200, "选课成功", null);
            } else {
                return Result.success(300, "该课程已满", null);
            }
        } else {
            return Result.success(400, "当前时间已选有课程", null);
        }
    }

    /**
     * 删除学生选课
     *
     * @param id 课程号
     * @return Response
     */
    @GetMapping("/deleteStudentChoice")
    public Result deleteStudentChoice(String id) {
        if (Status.choiceStatus) {
            return Result.success(400, "当前并未开启选课", null);
        }
        String student = JWT.token(httpServletRequest.getHeader("Authorization"));
        studentChoiceService.deleteStudentChoice(student, id);
        return Result.success(200, "退选成功", null);
    }

    /**
     * 搜索学生选课
     *
     * @param course 课程名
     * @param num    页面数
     * @param size   页面大小
     * @return Response
     */
    @GetMapping("/searchStudentChoiceByCourse")
    public Result searchStudentChoice(String course, int num, int size) {
        if (Status.choiceStatus) {
            return Result.success(400, "当前并未开启选课", null);
        }
        String student = JWT.token(httpServletRequest.getHeader("Authorization"));
        List<String> list = studentChoiceService.queryStudentChoiceCourseById(student);
        num = size * (num - 1);
        Map<String, Object> map = new LinkedHashMap<>();
        List<StudentChoice> studentChoiceList = studentChoiceService.queryStudentChoiceByCourse(course, num, size);
        for (StudentChoice studentChoice : studentChoiceList) {
            int number = choiceService.queryChoiceNumber(studentChoice.getId());
            if (number == studentChoice.getNumber())
                studentChoice.setFlag(2);
            studentChoice.setNumber(studentChoice.getNumber() - number);
            for (String i : list) {
                if (i.equals(studentChoice.getId())) {
                    studentChoice.setFlag(1);
                }
            }
        }
        int number = studentChoiceService.queryStudentElectiveCourseNumber(student);
        if (number != 0) {
            for (StudentChoice studentChoice : studentChoiceList) {
                studentChoice.setDisabled(false);
            }
        }
        map.put("studentChoiceList", studentChoiceList);
        map.put("count", electiveCourseService.queryCourseCountByName(course));
        return Result.success(200, "查询成功", map);
    }

    /**
     * 查询学生课表
     *
     * @return Response
     */
    @GetMapping("/queryStudentSchedule")
    public Result queryStudentSchedule() {
        String token = JWT.token(httpServletRequest.getHeader("Authorization"));
        List<Schedule> scheduleList = studentChoiceService.queryScheduleById(token);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("scheduleList", scheduleList);
        return Result.success(200, "查询成功", map);
    }
}
