package com.example.educationalsystembackend.controller;

import com.example.educationalsystembackend.pojo.AddScore;
import com.example.educationalsystembackend.pojo.TeacherScore;
import com.example.educationalsystembackend.result.Result;
import com.example.educationalsystembackend.service.ChoiceService;
import com.example.educationalsystembackend.service.ElectiveCourseService;
import com.example.educationalsystembackend.service.GradeService;
import com.example.educationalsystembackend.service.TeacherScoreService;
import com.example.educationalsystembackend.util.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@Transactional
public class TeacherScoreController {

    @Autowired
    private TeacherScoreService teacherScoreService;

    @Autowired
    private ElectiveCourseService electiveCourseService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private ChoiceService choiceService;

    /**
     * 教师查询所有需要录入成绩的课程
     *
     * @param num  页面数
     * @param size 页面大小
     * @return Response
     */
    @GetMapping("/queryAllTeacherScore")
    public Result queryAllTeacherScore(int num, int size) {
        String teacher = JWT.token(httpServletRequest.getHeader("Authorization"));
        num = size * (num - 1);
        List<TeacherScore> teacherCourseList = teacherScoreService.queryAllTeacherCourseList(teacher, num, size);
        for (TeacherScore teacherScore : teacherCourseList) {
            if (teacherScore.getKind() == 1) {
                teacherScore.setGrade(gradeService.queryRequiredCourseGrade(teacherScore.getId()));
                teacherScore.setNumber(choiceService.queryChoiceNumber(teacherScore.getId()));
            } else {
                teacherScore.setGrade("组班");
                teacherScore.setNumber(choiceService.queryChoiceNumber(teacherScore.getId()));
            }
        }
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("teacherCourseList", teacherCourseList);
        map.put("count", teacherScoreService.queryAllTeacherCourseCount(teacher));
        map.put("courseList", electiveCourseService.queryCourseByTeacher(teacher));
        return Result.success(200, "查询成功", map);
    }

    @PostMapping("/addScore/{course}/{usual}/{experiment}/{exam}")
    public Result addScore(@RequestBody AddScore addScore, @PathVariable("course") String course, @PathVariable("usual") int usual, @PathVariable("experiment") int experiment, @PathVariable("exam") int exam) {
        electiveCourseService.updateScoreProportion(usual, experiment, exam, course);
        for (TeacherScore teacherScore : addScore.getStudentList()) {
            teacherScore.setCourse(course);
            teacherScoreService.addScore(teacherScore);
        }
        return Result.success(200, "成绩保存成功", null);
    }


    /**
     * 教师查询需要录入成绩的课程
     *
     * @param course 课程名
     * @param num    页面数
     * @param size   页面大小
     * @return Response
     */
    @GetMapping("/queryTeacherScore")
    public Result queryTeacherScore(String course, int num, int size) {
        String teacher = JWT.token(httpServletRequest.getHeader("Authorization"));
        num = size * (num - 1);
        List<TeacherScore> teacherCourseList = teacherScoreService.queryTeacherCourse(teacher, course, num, size);
        for (TeacherScore teacherScore : teacherCourseList) {
            if (teacherScore.getKind() == 1) {
                teacherScore.setGrade(gradeService.queryRequiredCourseGrade(teacherScore.getId()));
                teacherScore.setNumber(choiceService.queryChoiceNumber(teacherScore.getId()));
            } else {
                teacherScore.setGrade("组班");
                teacherScore.setNumber(choiceService.queryChoiceNumber(teacherScore.getId()));
            }
        }
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("teacherCourseList", teacherCourseList);
        map.put("count", teacherScoreService.queryTeacherCourseCount(teacher, course));
        return Result.success(200, "查询成功", map);
    }

    @GetMapping("/queryTeacherScoreStudentList")
    public Result queryTeacherScoreStudentList(String course) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("studentList", teacherScoreService.queryTeacherScoreStudentList(course));
        map.put("usual", electiveCourseService.queryUsual(course));
        map.put("exam", electiveCourseService.queryExam(course));
        map.put("experiment", electiveCourseService.queryExperiment(course));
        return Result.success(200, "查询成功", map);
    }

    /**
     * 是否公开成绩
     *
     * @param course 课程
     * @param score  成绩
     * @return Response
     */
    @GetMapping("/changeScoreStatus")
    public Result changeScoreStatus(String course, boolean score) {
        if (teacherScoreService.queryCourseScoreIsNULLCount(course, electiveCourseService.queryExperiment(course)) != 0 && !score)
            return Result.success(400, "无法公开，有学生未录入成绩", null);
        teacherScoreService.changeScoreStatus(course, !score);
        return Result.success(200, "修改成功", null);
    }
}
