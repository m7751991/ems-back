package com.example.educationalsystembackend.controller;

import com.example.educationalsystembackend.pojo.Status;
import com.example.educationalsystembackend.pojo.StudentRetake;
import com.example.educationalsystembackend.result.Result;
import com.example.educationalsystembackend.service.*;
import com.example.educationalsystembackend.util.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@Transactional
public class StudentRetakeController {

    @Autowired
    private StudentRetakeService studentRetakeService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private RetakeCourseService retakeCourseService;

    @Autowired
    private ChoiceService choiceService;

    @Autowired
    private StudentChoiceService studentChoiceService;

    @Autowired
    private ElectiveCourseService electiveCourseService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private StudentScoreService studentScoreService;

    @GetMapping("/queryAllStudentRetakeCourse")
    public Result queryAllStudentRetakeCourse() {
        if (Status.retakeStatus) {
            return Result.success(400, "当前并未开启重修", null);
        }
        String student = JWT.token(httpServletRequest.getHeader("Authorization"));
        String[] courseList = studentRetakeService.queryRetakeCourseNameList(student);
        Float[] creditList = studentRetakeService.queryRetakeCourseCreditList(student);
        List<StudentRetake> studentRetakeList = new ArrayList<>();
        for (int i = 0; i < courseList.length; i++) {
            studentRetakeList.addAll(studentRetakeService.queryAllStudentRetakeCourseKind1(student, courseList[i], creditList[i]));
            studentRetakeList.addAll(studentRetakeService.queryAllStudentRetakeCourseKind3(student, courseList[i], creditList[i]));
        }
        List<String> list = studentChoiceService.queryStudentChoiceCourseById(student);
        for (StudentRetake studentRetake : studentRetakeList) {
            int number;
            if (studentRetake.getKind() == 1) {
                number = classroomService.queryClassroomNumberByName(studentRetake.getClassroom());
                if (number - studentRetake.getNumber() == 0) studentRetake.setKind(2);
                studentRetake.setNumber(number - studentRetake.getNumber());
            } else if (studentRetake.getKind() == 3) {
                number = retakeCourseService.queryNumberById(studentRetake.getId());
                studentRetake.setNumber(number - choiceService.queryChoiceNumber(studentRetake.getId()));
                if (number - choiceService.queryChoiceNumber(studentRetake.getId()) == 0) studentRetake.setKind(2);
            }
            for (String i : list) {
                if (i.equals(studentRetake.getId())) {
                    studentRetake.setKind(4);
                    String course = electiveCourseService.queryCourseNameById(i);
                    for (StudentRetake s : studentRetakeList) {
                        if ((s.getKind() == 1 || s.getKind() == 3) && s.getCourse().equals(course) && !course.equals(i)) {
                            s.setDisabled(false);
                        }
                    }
                }
            }
        }
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("studentRetakeList", studentRetakeList);
        return Result.success(200, "查询成功", map);
    }

    @GetMapping("/addStudentRetake")
    public Result addStudentRetake(String id, String classroom) {
        if (Status.retakeStatus) {
            return Result.success(500, "当前并未开启重修", null);
        }
        int number = classroomService.queryClassroomNumberByName(classroom);
        if (choiceService.queryChoiceNumber(id) == number) {
            return Result.success(300, "该课程已满", null);
        } else {
            String student = JWT.token(httpServletRequest.getHeader("Authorization"));
            int week = electiveCourseService.queryElectiveCourseWeekById(id);
            int start = electiveCourseService.queryElectiveCourseStartById(id);
            int end = electiveCourseService.queryElectiveCourseEndById(id);
            int count = studentChoiceService.queryStudentChoiceCount(student, week, start, end);
            if (count == 0) {
                studentChoiceService.addStudentChoice(student, id);
                number = retakeCourseService.queryNumberById(id);
                retakeCourseService.updateCourseNumber(id, number + 1);
                return Result.success(200, "选课成功", null);
            } else {
                return Result.success(400, "当前时间已选有课程", null);
            }
        }
    }

    @GetMapping("/deleteStudentRetake")
    public Result deleteStudentRetake(String id) {
        if (Status.retakeStatus) {
            return Result.success(400, "当前并未开启重修", null);
        }
        String student = JWT.token(httpServletRequest.getHeader("Authorization"));
        studentChoiceService.deleteStudentChoice(student, id);
        int number = retakeCourseService.queryNumberById(id);
        retakeCourseService.updateCourseNumber(id, number - 1);
        return Result.success(200, "退选成功", null);
    }

    @GetMapping("/queryStudentRetake/{course}")
    public Result queryStudentRetake(@PathVariable("course") String course) {
        if (Status.retakeStatus) {
            return Result.success(400, "当前并未开启重修", null);
        }
        String student = JWT.token(httpServletRequest.getHeader("Authorization"));
        Integer score = studentScoreService.queryStudentCourseMaxScore(student, course);
        if (score == null || score >= 60)
            return Result.success(500, "当前课程无需重修", null);
        float credit = studentRetakeService.queryRetakeCourseCredit(student, course);
        List<StudentRetake> studentRetakeList = studentRetakeService.queryStudentRetakeCourseKind1(student, course, credit);
        studentRetakeList.addAll(studentRetakeService.queryStudentRetakeCourseKind3(student, course, credit));
        List<String> list = studentChoiceService.queryStudentChoiceCourseById(student);
        for (StudentRetake studentRetake : studentRetakeList) {
            int number;
            if (studentRetake.getKind() == 1) {
                number = classroomService.queryClassroomNumberByName(studentRetake.getClassroom());
                if (number - studentRetake.getNumber() == 0) studentRetake.setKind(2);
                studentRetake.setNumber(number - studentRetake.getNumber());
            } else if (studentRetake.getKind() == 3) {
                number = retakeCourseService.queryNumberById(studentRetake.getId());
                studentRetake.setNumber(number - choiceService.queryChoiceNumber(studentRetake.getId()));
                if (number - choiceService.queryChoiceNumber(studentRetake.getId()) == 0) studentRetake.setKind(2);
            }
            for (String i : list) {
                if (i.equals(studentRetake.getId())) {
                    studentRetake.setKind(4);
                    course = electiveCourseService.queryCourseNameById(i);
                    for (StudentRetake s : studentRetakeList) {
                        if ((s.getKind() == 1 || s.getKind() == 3) && s.getCourse().equals(course) && !course.equals(i)) {
                            s.setDisabled(false);
                        }
                    }
                }
            }
        }
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("studentRetakeList", studentRetakeList);
        return Result.success(200, "查询成功", map);
    }
}