/*
 * @Descripttion: 
 * @version: 
 * @Author: zixi
 * @Date: 2025-03-27 00:12:47
 * @LastEditors: zixi
 * @LastEditTime: 2025-03-29 00:07:15
 */
package com.example.educationalsystembackend.controller;

import com.example.educationalsystembackend.pojo.ElectiveCourse;
import com.example.educationalsystembackend.pojo.RequiredCourse;
import com.example.educationalsystembackend.result.Result;
import com.example.educationalsystembackend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.educationalsystembackend.util.ConflictException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@Transactional
public class RequiredCourseController {

    @Autowired
    private ElectiveCourseService electiveCourseService;

    @Autowired
    private RequiredCourseService requiredCourseService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private StudentChoiceService studentChoiceService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ClassroomService classroomService;

    /**
     * 查询所有选必修课
     *
     * @param num  页面数
     * @param size 页面大小
     * @return Response
     */
    @GetMapping("/queryAllRequiredCourse")
    public Result queryRequiredCourse(int num, int size) {
        num = size * (num - 1);
        List<RequiredCourse> requiredCourseList = requiredCourseService.queryAllRequiredCourse(num, size);
        requiredCourseList.forEach(requiredCourse -> requiredCourse
                .setChildren(requiredCourseService.queryRequiredCourseChildren(requiredCourse.getId())));

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("requiredCourseList", requiredCourseList);
        map.put("count", requiredCourseService.queryAllRequiredCourseCount());
        map.put("teacherList", teacherService.queryAllTeacherIdAndName());
        return Result.success(200, "查询成功", map);
    }

    /**
     * 查询必修课
     *
     * @param grade 班级
     * @param num   页面数
     * @param size  页面大小
     * @return Response
     */
    @GetMapping("/queryRequiredCourse")
    public Result queryRequiredCourse(String grade, int num, int size) {
        String id = grade;
        grade = "%" + grade + "%";
        num = size * (num - 1);
        List<RequiredCourse> requiredCourseList = requiredCourseService.queryRequiredCourse(grade, num, size);
        requiredCourseList.forEach(requiredCourse -> requiredCourse
                .setChildren(requiredCourseService.queryRequiredCourseChildren(requiredCourse.getId())));
        id = gradeService.queryGradeIdByName(id);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("requiredCourseList", requiredCourseList);
        map.put("count", requiredCourseService.queryRequiredCourseCount(id));
        return Result.success(200, "查询成功", map);
    }

    /**
     * 按班级删除必修课
     *
     * @param course 课程
     * @param grade  班级
     * @return Response
     */
    @GetMapping("/deleteRequiredCourseChildren")
    public Result deleteRequiredCourseChildren(String course, String grade) {
        grade = gradeService.queryGradeIdByName(grade);
        requiredCourseService.deleteRequiredCourse(course, grade); // 必修课表中删除
        if (requiredCourseService.queryGradeCount(course) == 0) { // 此时只有一个班级上该必修课
            electiveCourseService.deleteElectiveCourse(course); // 课程表中删除
        }
        return Result.success(200, "删除成功", null);
    }

    /**
     * 删除课程
     *
     * @param course 课程
     * @return Response
     */
    @GetMapping("/deleteRequiredCourse")
    public Result deleteRequiredCourse(String course) {
        electiveCourseService.deleteElectiveCourse(course);
        return Result.success(200, "删除成功", null);
    }

    /**
     * 添加必修课
     *
     * @param requiredCourse 必修课
     * @return Response
     */
    @PostMapping("/addRequiredCourse")
    public Result addRequiredCourse(@RequestBody RequiredCourse requiredCourse) {
        if (requiredCourseService.queryRequiredCourseCountById(requiredCourse.getId()) != 0)
            return Result.success(400, "该课程号已存在", null);

        // 检查教师在相同时间段是否已安排其他课程
        try {
            requiredCourseService.queryRequiredCourseTeacherMoreDateNumber(requiredCourse);
        } catch (ConflictException e) {
            return Result.success(400, e.getMessage(), null);
        }
    
        // 检查教室在相同时间段是否已被占用
        if (classroomService.queryClassroomMoreDateNumber(requiredCourse.getId(), requiredCourse.getFrom(),
                requiredCourse.getTo(), requiredCourse.getWeek(), requiredCourse.getStart(), requiredCourse.getEnd(),
                requiredCourse.getClassroom()) != 0)
            return Result.success(400, "当前教室已有课程安排", null);

        // 检查所选班级在相同时间段是否已有其他课程安排
        for (String grade : requiredCourse.getRequiredGradeList()) {
            if (requiredCourseService.queryGradeTimeConflict(grade, requiredCourse.getFrom(), requiredCourse.getTo(),
                    requiredCourse.getWeek(), requiredCourse.getStart(), requiredCourse.getEnd()) != 0) {
                return Result.success(400, "班级 " + grade + " 在该时间段已有课程安排", null);
            }
        }

        ElectiveCourse electiveCourse = new ElectiveCourse(); // 添加一条课程信息
        electiveCourse.setId(requiredCourse.getId());
        electiveCourse.setName(requiredCourse.getName());
        electiveCourse.setTeacher(requiredCourse.getTeacher());
        electiveCourse.setWeek(requiredCourse.getWeek());
        electiveCourse.setStart(requiredCourse.getStart());
        electiveCourse.setEnd(requiredCourse.getEnd());
        electiveCourse.setClassroom(requiredCourse.getClassroom());
        electiveCourse.setFlag(true); // 表示公开
        electiveCourse.setScore(false); // 不公开成绩
        electiveCourse.setKind(1); // 表示为必修课
        electiveCourse.setNumber(requiredCourse.getNumber());
        electiveCourse.setFrom(requiredCourse.getFrom());
        electiveCourse.setTo(requiredCourse.getTo());
        electiveCourse.setCredit(requiredCourse.getCredit());
        electiveCourseService.addElectiveCourse(electiveCourse);
        electiveCourseService.updateScoreProportion(20, 10, 70, requiredCourse.getId());

        for (String grade : requiredCourse.getRequiredGradeList()) { // 为所选班级添加必修课
            requiredCourseService.addRequiredCourse(requiredCourse.getId(), grade);
        }
        for (String grade : requiredCourse.getRequiredGradeList()) {
            List<String> studentList = studentService.queryStudentIdByGrade(grade); // 班级中每个学生选择该课程
            for (String student : studentList) {
                studentChoiceService.addStudentChoice(student, requiredCourse.getId());
            }
        }
        return Result.success(200, "添加成功", null);
    }

    /**
     * 修改必修课
     *
     * @param requiredCourse 必修课
     * @return Response
     */
    @PostMapping("/updateRequiredCourse")
    public Result updateCourse(@RequestBody RequiredCourse requiredCourse) {
        requiredCourse.setTeacher(teacherService.queryTeacherIdByName(requiredCourse.getTeacher()));
        try {
            requiredCourseService.queryRequiredCourseTeacherMoreDateNumber(requiredCourse);
        } catch (ConflictException e) {
            return Result.success(400, e.getMessage(), null);
        }
        // if (requiredCourseService.queryRequiredCourseTeacherMoreDateNumber(requiredCourse) != 0)
        //     return Result.success(400, "该老师当前时间已安排课程", null);
        if (classroomService.queryClassroomMoreDateNumber(requiredCourse.getId(), requiredCourse.getFrom(),
                requiredCourse.getTo(), requiredCourse.getWeek(), requiredCourse.getStart(), requiredCourse.getEnd(),
                classroomService.queryClassroomIdByName(requiredCourse.getClassroom())) != 0)
            return Result.success(400, "当前教室已有课程安排", null);
        requiredCourse.setClassroom(classroomService.queryClassroomIdByName(requiredCourse.getClassroom()));
        requiredCourseService.updateRequiredCourse(requiredCourse);
        return Result.success(200, "修改成功", null);
    }

    @GetMapping("/queryRequiredCourseExcelData")
    public Result queryRequiredCourseExcelData() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("excelData", requiredCourseService.queryRequiredCourseExcelData());
        return Result.success(200, "导出成功", map);
    }
}
