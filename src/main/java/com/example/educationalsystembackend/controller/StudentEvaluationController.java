package com.example.educationalsystembackend.controller;

import com.example.educationalsystembackend.pojo.Status;
import com.example.educationalsystembackend.pojo.StudentEvaluation;
import com.example.educationalsystembackend.pojo.StudentEvaluationList;
import com.example.educationalsystembackend.result.Result;
import com.example.educationalsystembackend.service.ElectiveCourseService;
import com.example.educationalsystembackend.service.StudentEvaluationService;
import com.example.educationalsystembackend.service.TeacherService;
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
public class StudentEvaluationController {

    @Autowired
    private StudentEvaluationService studentEvaluationService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ElectiveCourseService courseService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 学生查询所有评价
     *
     * @return Response
     */
    @GetMapping("queryStudentEvaluationList")
    public Result queryStudentEvaluationList() {
        if (Status.evaluationStatus) {
            return Result.success(400, "当前并未开启教学评价", null);
        }
        String student = JWT.token(httpServletRequest.getHeader("Authorization"));
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("studentEvaluationList", studentEvaluationService.queryStudentEvaluationList(student));
        map.put("teacherList", teacherService.queryStudentChoiceTeacher(student));
        map.put("courseList", courseService.queryCourseByStudent(student));
        return Result.success(200, "查询成功", map);
    }

    /**
     * 学生添加评价
     *
     * @param studentEvaluationList 学生评价列表
     * @return Response
     */
    @PostMapping("/addEvaluation")
    public Result addEvaluation(@RequestBody StudentEvaluationList studentEvaluationList) {
        if (Status.evaluationStatus) {
            return Result.success(400, "当前并未开启教学评价", null);
        }
        String student = JWT.token(httpServletRequest.getHeader("Authorization"));
        for (StudentEvaluation studentEvaluation : studentEvaluationList.getStudentEvaluationList()) {
            String teacher = teacherService.queryTeacherIdByName(studentEvaluation.getTeacher());
            studentEvaluationService.addEvaluation(student, studentEvaluation.getCourse(), teacher, studentEvaluation.getEvaluation(), studentEvaluation.getComment());
        }
        return Result.success(200, "评价成功", null);
    }

    /**
     * 学生查询评价
     *
     * @param course  课程
     * @param teacher 教师
     * @return Response
     */
    @GetMapping("/searchStudentEvaluation")
    public Result searchStudentEvaluation(String course, String teacher) {
        if (Status.evaluationStatus) {
            return Result.success(400, "当前并未开启教学评价", null);
        }
        if (course.equals(""))
            course = "%";
        if (teacher.equals(""))
            teacher = "%";
        String student = JWT.token(httpServletRequest.getHeader("Authorization"));
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("evaluationList", studentEvaluationService.searchStudentEvaluation(student, course, teacher));
        return Result.success(200, "查询成功", map);
    }
}
