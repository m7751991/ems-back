package com.example.educationalsystembackend.controller;

import com.example.educationalsystembackend.pojo.AdjustCourse;
import com.example.educationalsystembackend.pojo.TeacherCourse;
import com.example.educationalsystembackend.result.Result;
import com.example.educationalsystembackend.service.ChoiceService;
import com.example.educationalsystembackend.service.ElectiveCourseService;
import com.example.educationalsystembackend.service.GradeService;
import com.example.educationalsystembackend.service.TeacherCourseService;
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
public class TeacherCourseController {

    @Autowired
    private TeacherCourseService teacherCourseService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private ChoiceService choiceService;

    @Autowired
    private ElectiveCourseService electiveCourseService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 分页查询教师课程
     *
     * @param num  页面数
     * @param size 页面大小
     * @return Response
     */
    @GetMapping("/queryAllTeacherCourse")
    public Result queryAllTeacherCourse(int num, int size) {
        String teacher = JWT.token(httpServletRequest.getHeader("Authorization"));
        num = size * (num - 1);
        List<TeacherCourse> teacherCourseList = teacherCourseService.queryAllTeacherCourse(teacher, num, size);
        for (TeacherCourse teacherCourse : teacherCourseList) {
            if (teacherCourse.getKind() == 1) {
                teacherCourse.setGrade(gradeService.queryRequiredCourseGrade(teacherCourse.getId()));
                teacherCourse.setNumber(choiceService.queryChoiceNumber(teacherCourse.getId()));
            } else {
                teacherCourse.setGrade("组班");
                teacherCourse.setNumber(choiceService.queryChoiceNumber(teacherCourse.getId()));
            }
        }
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("teacherCourseList", teacherCourseList);
        map.put("count", teacherCourseService.queryAllTeacherCourseCount(teacher));
        return Result.success(200, "查询成功", map);
    }

    /**
     * 查询教师课程
     *
     * @param id   课程号
     * @param num  页面数
     * @param size 页面大小
     * @return Response
     */
    @GetMapping("/queryTeacherCourse")
    public Result queryTeacherCourse(String id, int num, int size) {
        String teacher = JWT.token(httpServletRequest.getHeader("Authorization"));
        int count = teacherCourseService.queryTeacherCourseCount(teacher, id);
        if (count == 0) return Result.success(400, "没有搜到该课程", null);
        else {
            num = size * (num - 1);
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("teacherCourseList", teacherCourseService.queryTeacherCourse(teacher, id, num, size));
            map.put("count", count);
            return Result.success(200, "查询成功", map);
        }
    }

    @GetMapping("/queryTeacherCourseId")
    public Result queryTeacherCourseId() {
        String teacher = JWT.token(httpServletRequest.getHeader("Authorization"));
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("courseList", teacherCourseService.queryTeacherCourseId(teacher));
        return Result.success(200, "查询成功", map);
    }

    /**
     * 导出课程学生名单
     *
     * @param id 课程号
     * @return Response
     */
    @GetMapping("/queryTeacherCourseExcelData/{id}")
    public Result queryTeacherCourseExcelData(@PathVariable("id") String id) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("excelData", teacherCourseService.queryTeacherCourseExcelData(id));
        map.put("courseName", electiveCourseService.queryCourseNameById(id));
        return Result.success(200, "导出成功", map);
    }

    @PostMapping("/addAdjustCourse")
    public Result addAdjustCourse(@RequestBody AdjustCourse adjustCourse) {
        int from = adjustCourse.getFromList().size();
        int to = adjustCourse.getToList().size();
        int week = adjustCourse.getWeekList().size();
        int lesson = adjustCourse.getLessonList().size();
        if (from * 2 != to * week * lesson)
            return Result.success(500, "调课前和调课后节次总数不相同", null);
        else {
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
            teacherCourseService.addAdjustCourse(adjustCourse);
            return Result.success(200, "调课添加成功", null);
        }
    }
}

