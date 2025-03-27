package com.example.educationalsystembackend.controller;

import com.example.educationalsystembackend.pojo.StudentScore;
import com.example.educationalsystembackend.result.Result;
import com.example.educationalsystembackend.service.ElectiveCourseService;
import com.example.educationalsystembackend.service.StudentScoreService;
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
public class StudentScoreController {

    @Autowired
    private StudentScoreService studentScoreService;

    @Autowired
    private ElectiveCourseService courseService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping("/studentScoreList")
    public Result studentScoreList(int num, int size, String prop, String order) {
        String student = JWT.token(httpServletRequest.getHeader("Authorization"));
        num = size * (num - 1);
        Map<String, Object> map = new LinkedHashMap<>();
        List<StudentScore> studentScoreList;
        if (order == null)
            studentScoreList = studentScoreService.queryStudentScore(student, num, size);
        else
            studentScoreList = studentScoreService.queryStudentScoreByOrder(student, prop, order, num, size);
        for (StudentScore studentScore : studentScoreList) {
            if (studentScore.getTotal() == 100) studentScore.setGpa(5);
            else if (studentScore.getTotal() < 60) studentScore.setGpa(0);
            else studentScore.setGpa(Math.floor((studentScore.getTotal() - 60) * 1.0 / 5) * 0.5 + 1);
        }
        map.put("studentScoreList", studentScoreList);
        map.put("courseList", courseService.queryCourseByStudent(student));
        map.put("count", studentScoreService.queryStudentScoreCount(student));
        return Result.success(200, "查询成功", map);
    }

    @GetMapping("/searchStudentScore")
    public Result searchStudentScore(String course, int num, int size) {
        num = size * (num - 1);
        String student = JWT.token(httpServletRequest.getHeader("Authorization"));
        if (studentScoreService.queryStudentScoreCountByCourse(student, course) == 0) {
            return Result.success(400, "该课程还未公布成绩", null);
        }
        Map<String, Object> map = new LinkedHashMap<>();
        List<StudentScore> studentScoreList = studentScoreService.queryStudentScoreByCourese(student, course, num, size);
        for (StudentScore studentScore : studentScoreList) {
            if (studentScore.getTotal() == 100) studentScore.setGpa(5);
            else if (studentScore.getTotal() < 60) studentScore.setGpa(0);
            else studentScore.setGpa(Math.floor((studentScore.getTotal() - 60) * 1.0 / 5) * 0.5 + 1);
        }
        map.put("studentScoreList", studentScoreList);
        map.put("count", studentScoreService.queryStudentScoreCountByCourse(student, course));
        return Result.success(200, "查询成功", map);
    }
}
