package com.example.educationalsystembackend.controller;


import com.example.educationalsystembackend.result.Result;
import com.example.educationalsystembackend.service.GradeService;
import com.example.educationalsystembackend.service.RequiredCourseService;
import com.example.educationalsystembackend.service.StudentExamService;
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
public class StudentExamController {

    @Autowired
    private StudentExamService studentExamService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private RequiredCourseService requiredCourseService;

    @Autowired
    private GradeService gradeService;

    /**
     * 查询学生所有考试
     *
     * @param num  页面数
     * @param size 页面大小
     * @return Response
     */
    @GetMapping("/studentExamList")
    public Result studentExamList(int num, int size) {
        num = size * (num - 1);
        Map<String, Object> map = new LinkedHashMap<>();
        String student = JWT.token(httpServletRequest.getHeader("Authorization"));
        String grade = gradeService.queryGradeByStudent(student);
        map.put("studentExamList", studentExamService.queryStudentExam(grade, num, size));
        map.put("courseList", requiredCourseService.queryStudentRequiredCourse(student));
        map.put("count", studentExamService.queryStudentExamCount(grade));
        return Result.success(200, "查询成功", map);
    }

    /**
     * 搜索学生考试
     *
     * @param course 课程
     * @return Response
     */
    @GetMapping("/searchStudentExam")
    public Result searchStudentExam(String course) {
        String student = JWT.token(httpServletRequest.getHeader("Authorization"));
        String grade = gradeService.queryGradeByStudent(student);
        int count = studentExamService.queryStudentExamCountByCourse(grade, course);
        if (count == 0)
            return Result.success(400, "该课程还没有安排考试", null);
        else {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("studentExamList", studentExamService.queryStudentExamByCourse(grade, course));
            map.put("count", count);
            return Result.success(200, "查询成功", map);
        }
    }
}
