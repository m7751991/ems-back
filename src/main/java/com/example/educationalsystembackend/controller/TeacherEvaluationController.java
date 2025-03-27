package com.example.educationalsystembackend.controller;

import com.example.educationalsystembackend.result.Result;
import com.example.educationalsystembackend.service.ElectiveCourseService;
import com.example.educationalsystembackend.service.GradeService;
import com.example.educationalsystembackend.service.TeacherEvaluationService;
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
public class TeacherEvaluationController {

    @Autowired
    private TeacherEvaluationService teacherEvaluationService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private ElectiveCourseService courseService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 教师查询所有评价
     *
     * @param num  页面数
     * @param size 页面大小
     * @return Response
     */
    @GetMapping("/queryAllTeacherEvaluation")
    public Result queryAllTeacherEvaluation(int num, int size) {
        String teacher = JWT.token(httpServletRequest.getHeader("Authorization"));
        num = size * (num - 1);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("evaluationList", teacherEvaluationService.queryAllTeacherEvaluation(teacher, num, size));
        map.put("count", teacherEvaluationService.queryAllTeacherEvaluationCount(teacher));
        map.put("courseList", courseService.queryCourseByTeacher(teacher));
        map.put("gradeList", gradeService.queryChoiceGradeByTeacher(teacher));
        return Result.success(200, "查询成功", map);
    }

    /**
     * 教师查询评价
     *
     * @param course 课程
     * @param grade  班级
     * @param num    页面数
     * @param size   页面大小
     * @return Response
     */
    @GetMapping("/queryTeacherEvaluation")
    public Result queryEvaluation(String course, String grade, int num, int size) {
        if (course.equals(""))
            course = "%";
        if (grade.equals(""))
            grade = "%";
        String teacher = JWT.token(httpServletRequest.getHeader("Authorization"));
        int count = teacherEvaluationService.queryTeacherEvaluationCount(teacher, course, grade);
        if (count == 0)
            return Result.success(400, "当前无评价", null);
        else {
            num = size * (num - 1);
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("evaluationList", teacherEvaluationService.queryTeacherEvaluation(teacher, course, grade, num, size));
            map.put("count", count);
            return Result.success(200, "查询成功", map);
        }
    }
}
