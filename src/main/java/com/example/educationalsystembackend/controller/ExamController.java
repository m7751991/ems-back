package com.example.educationalsystembackend.controller;

import com.example.educationalsystembackend.pojo.Exam;
import com.example.educationalsystembackend.result.Result;
import com.example.educationalsystembackend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@Transactional
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ElectiveCourseService courseService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private GradeService gradeService;

    /**
     * 查询所有考试
     *
     * @param num  页面数
     * @param size 页面大小
     * @return Response
     */
    @GetMapping("/queryAllExam")
    public Result examList(int num, int size) {
        num = size * (num - 1);
        Map<String, Object> map = new LinkedHashMap<>();
        List<Exam> examList = examService.queryAllExam(num, size);
        for (Exam exam : examList) {
            exam.setSupervisor(teacherService.queryTeacherNameById(exam.getSupervisor()));
        }
        map.put("examList", examList);
        map.put("gradeList", gradeService.queryAllExamGrade());
        map.put("teacherList", teacherService.queryAllTeacherIdAndName());
        map.put("count", examService.queryAllExamCount());
        return Result.success(200, "查询成功", map);
    }

    @GetMapping("/deleteExam")
    public Result deleteStudent(String course, String grade) {
        grade = gradeService.queryGradeIdByName(grade);
        examService.deleteExam(course, grade);
        return Result.success(200, "删除成功", null);
    }

    /**
     * 查询考试
     *
     * @param grade 班级
     * @param num   页面数
     * @param size  页面大小
     * @return Response
     */
    @GetMapping("/queryExam")
    public Result queryExam(String grade, int num, int size) {
        num = size * (num - 1);
        Map<String, Object> map = new LinkedHashMap<>();
        List<Exam> examList = examService.queryExam(grade, num, size);
        for (Exam exam : examList) {
            exam.setSupervisor(teacherService.queryTeacherNameById(exam.getSupervisor()));
        }
        map.put("examList", examList);
        map.put("count", examService.queryExamCount(grade));
        return Result.success(200, "查询成功", map);
    }

    /**
     * 添加考试
     *
     * @param exam 考试
     * @return Response
     */
    @PostMapping("/addExam")
    public Result addExam(@RequestBody Exam exam) {
        if (examService.queryExamClassroomMoreTimeNumber(exam) != 0)
            return Result.success(500, "当前教室已有考试安排", null);
        System.out.println(examService.queryExamClassroomMoreTimeNumber(exam));
        if (examService.queryExamTeacherMoreTimeNumber(exam) != 0)
            return Result.success(500, "当前教师已有考试安排", null);
        List<String> gradeList = exam.getGradeList();
        List<String> courseList = exam.getCourseList();
        for (int i = 0; i < gradeList.size(); i++)
            gradeList.set(i, gradeService.queryGradeIdByName(gradeList.get(i)));
        String grade = "";
        for (int i = 0; i < gradeList.size(); i++) {
            exam.setCourse(courseList.get(i));
            exam.setGrade(gradeList.get(i));
            if (examService.queryExamCourseMoreTimeNumber(exam) != 0) {
                grade = grade.concat(gradeService.queryGradeNameById(gradeList.get(i)));
                grade = grade.concat(",");
            }
        }
        if (!grade.equals("")) {
            grade = grade.substring(0, grade.length() - 1);
            return Result.success(400, grade + "当前时间有考试", null);
        }
        for (String s : gradeList) {
            exam.setGrade(s);
            examService.addExam(exam);
        }
        return Result.success(200, "添加成功", null);
    }

    /**
     * 修改考试
     *
     * @param exam 考试
     * @return Response
     */
    @PostMapping("/alterExam")
    public Result alterStudent(@RequestBody Exam exam) {
        exam.setSupervisor(teacherService.queryTeacherIdByName(exam.getSupervisor()));
        exam.setClassroom(classroomService.queryClassroomIdByName(exam.getClassroom()));
        exam.setCourse(courseService.queryCourseIdByNameAndTeacher(exam.getCourse(), exam.getTeacher()));
        exam.setGrade(gradeService.queryGradeIdByName(exam.getGrade()));
        examService.alterExam(exam);
        return Result.success(200, "修改成功", null);
    }

    @GetMapping("/queryExamExcelData")
    public Result queryExamExcelData(@DateTimeFormat(pattern = "yyyy-MM-dd") Date from, @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<Exam> examList = examService.queryExamExcelData(from, to);
        for (Exam exam : examList) {
            exam.setSupervisor(teacherService.queryTeacherNameById(exam.getSupervisor()));
        }
        map.put("excelData", examList);
        return Result.success(200, "导出成功", map);
    }
}
