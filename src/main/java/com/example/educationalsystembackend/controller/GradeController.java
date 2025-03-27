package com.example.educationalsystembackend.controller;

import com.example.educationalsystembackend.pojo.Grade;
import com.example.educationalsystembackend.result.Result;
import com.example.educationalsystembackend.service.GradeService;
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
import java.util.List;
import java.util.Map;

@RestController
@Transactional
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 查询所有班级
     *
     * @param num  页面数
     * @param size 页面大小
     * @return Response
     */
    @GetMapping("/queryAllGrade")
    public Result queryAllGrade(int num, int size) {
        Map<String, Object> map = new LinkedHashMap<>();
        num = size * (num - 1);
        map.put("gradeList", gradeService.queryAllGrade(num, size));
        map.put("teacherList", teacherService.queryAllTeacherIdAndName());
        map.put("count", gradeService.queryAllGradeCount());
        return Result.success(200, "查询成功", map);
    }

    /**
     * 添加班级
     *
     * @param grade 班级
     * @return Response
     */
    @PostMapping("/addGrade")
    public Result addGrade(@RequestBody Grade grade) {
        if (gradeService.queryGradeCount(grade.getId()) == 0) {  //当前班级不存在
            gradeService.addGrade(grade);
            return Result.success(200, "添加班级成功", null);
        } else {
            return Result.success(400, "添加失败，当前班级已存在", null);
        }
    }

    /**
     * 删除班级
     *
     * @param id 班级号
     * @return Response
     */
    @GetMapping("/deleteGrade")
    public Result deleteGrade(String id) {
        gradeService.deleteGrade(id);
        return Result.success(200, "删除成功", null);
    }

    /**
     * 修改班级
     *
     * @param grade 班级
     * @return Response
     */
    @PostMapping("/alterGrade")
    public Result alterGrade(@RequestBody Grade grade) {
        gradeService.updateGrade(grade);
        return Result.success(200, "修改成功", null);
    }

    /**
     * 查询班级
     *
     * @param id 班级号
     * @return Response
     */
    @GetMapping("/queryGrade")
    public Result queryGrade(String id) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("gradeList", gradeService.queryGrade(id));
        map.put("count", gradeService.queryGradeCount(id));
        return Result.success(200, "查询成功", map);
    }

    /**
     * 教师通过课程查询班级
     *
     * @param course 课程名
     * @return Response
     */
    @GetMapping("/queryTeacherChoiceGradeByCourse")
    public Result queryTeacherChoiceGradeByCourse(String course) {
        if (course.equals(""))
            course = "%";
        String teacher = JWT.token(httpServletRequest.getHeader("Authorization"));
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("gradeList", gradeService.queryTeacherChoiceGradeByCourse(course, teacher));
        return Result.success(200, "查询成功", map);
    }

    @GetMapping("/queryGradeExcelData")
    public Result queryGradeExcelData() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("excelData", gradeService.queryGradeExcelData());
        return Result.success(200, "导出成功", map);
    }

    @GetMapping("/queryAllGradeIdAndName")
    public Result queryGradeIdAndName() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("gradeList", gradeService.queryAllGradeIdAndName());
        return Result.success(200, "查询成功", map);
    }

    @PostMapping("/uploadGrade")
    public Result uploadGrade(@RequestBody List<Grade> gradeList) {
        for (Grade grade : gradeList)
            gradeService.addGrade(grade);
        return Result.success(200, "导入成功", null);
    }
}
