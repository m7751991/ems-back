package com.example.educationalsystembackend.controller;

import com.example.educationalsystembackend.pojo.ElectiveCourse;
import com.example.educationalsystembackend.pojo.Status;
import com.example.educationalsystembackend.result.Result;
import com.example.educationalsystembackend.service.ChoiceService;
import com.example.educationalsystembackend.service.ClassroomService;
import com.example.educationalsystembackend.service.ElectiveCourseService;
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
public class ElectiveCourseController {

    @Autowired
    private ElectiveCourseService electiveCourseService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ChoiceService choiceService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 查询所有选修课
     *
     * @param num  页面数
     * @param size 页面大小
     * @return Response
     */
    @GetMapping("/queryAllElectiveCourse")
    public Result queryAllElectiveCourse(int num, int size) {
        num = size * (num - 1);
        Map<String, Object> map = new LinkedHashMap<>();
        List<ElectiveCourse> electiveCourseList = electiveCourseService.queryAllElectiveCourse(num, size);
        for (ElectiveCourse electiveCourse : electiveCourseList)
            electiveCourse.setChoiceNumber(choiceService.queryChoiceNumber(electiveCourse.getId()));
        map.put("electiveCourseList", electiveCourseList);
        map.put("teacherList", teacherService.queryAllTeacherIdAndName());
        map.put("count", electiveCourseService.queryAllElectiveCourseCount());
        map.put("choiceStatus", Status.choiceStatus);
        return Result.success(200, "查询选修课成功", map);
    }

    /**
     * 添加选修课
     *
     * @param electiveCourse 选修课
     * @return Response
     */
    @PostMapping("/addElectiveCourse")
    public Result addElectiveCourse(@RequestBody ElectiveCourse electiveCourse) {
        if (electiveCourseService.queryElectiveCourseCount(electiveCourse.getId()) != 0)
            return Result.success(400, "该课程号已存在", null);
        if (electiveCourseService.queryElectiveCourseMoreDateNumber(electiveCourse) != 0)
            return Result.success(400, "该老师当前时间已安排课程", null);
        if (classroomService.queryClassroomMoreDateNumber(electiveCourse.getId(), electiveCourse.getFrom(), electiveCourse.getTo(), electiveCourse.getWeek(), electiveCourse.getStart(), electiveCourse.getEnd(), electiveCourse.getClassroom()) != 0)
            return Result.success(400, "当前教室已有课程安排", null);
        if (electiveCourseService.queryElectiveCourseCount(electiveCourse.getId()) == 0) {
            electiveCourse.setFlag(true);
            electiveCourse.setScore(false);
            electiveCourse.setKind(2);
            electiveCourseService.addElectiveCourse(electiveCourse);
            electiveCourseService.updateScoreProportion(30, 0, 70, electiveCourse.getId());
            return Result.success(200, "添加选修课成功", null);
        } else {
            return Result.success(400, "添加失败，当前课程号已存在", null);
        }
    }

    /**
     * 删除选修课
     *
     * @param id 课程号
     * @return Response
     */
    @GetMapping("/deleteElectiveCourse")
    public Result deleteElectiveCourse(String id) {
        electiveCourseService.deleteElectiveCourse(id);
        return Result.success(200, "删除选修课成功", null);
    }

    /**
     * 查询选修课
     *
     * @param id 课程号
     * @return Response
     */
    @GetMapping("/queryElectiveCourse")
    public Result queryElectiveCourse(String id) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<ElectiveCourse> electiveCourseList = electiveCourseService.queryElectiveCourse(id);
        for (ElectiveCourse electiveCourse : electiveCourseList)
            electiveCourse.setChoiceNumber(choiceService.queryChoiceNumber(electiveCourse.getId()));
        map.put("electiveCourseList", electiveCourseList);
        map.put("count", electiveCourseService.queryElectiveCourseCount(id));
        return Result.success(200, "查询选修课成功", map);
    }

    /**
     * 修改选修课
     *
     * @param electiveCourse 选修课
     * @return Response
     */
    @PostMapping("/alterElectiveCourse")
    public Result alterElectiveCourse(@RequestBody ElectiveCourse electiveCourse) {
        electiveCourse.setTeacher(teacherService.queryTeacherIdByName(electiveCourse.getTeacher()));
        electiveCourse.setClassroom(classroomService.queryClassroomIdByName(electiveCourse.getClassroom()));
        if (electiveCourseService.queryElectiveCourseMoreDateNumber(electiveCourse) != 0)
            return Result.success(400, "该老师当前时间已安排课程", null);
        electiveCourseService.updateElectiveCourse(electiveCourse);
        if (classroomService.queryClassroomMoreDateNumber(electiveCourse.getId(), electiveCourse.getFrom(), electiveCourse.getTo(), electiveCourse.getWeek(), electiveCourse.getStart(), electiveCourse.getEnd(), electiveCourse.getClassroom()) != 0)
            return Result.success(400, "当前教室已有课程安排", null);
        return Result.success(200, "修改选修课成功", null);
    }

    /**
     * 是否公开该选修课
     *
     * @param course 课程号
     * @param flag   状态
     * @return Response
     */
    @GetMapping("/changeElectiveCourseStatus")
    public Result changeElectiveCourseStatus(String course, boolean flag) {
        electiveCourseService.changeElectiveCourseStatus(course, flag);
        return Result.success(200, "修改选修课状态成功", null);
    }

    /**
     * 开启选修课
     *
     * @return Response
     */
    @GetMapping("/startElectiveCourse")
    public Result startElectiveCourse() {
        Status.choiceStatus = !Status.choiceStatus;
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("choiceStatus", Status.choiceStatus);
        return Result.success(200, "开启选课成功", map);
    }

    /**
     * 关闭选修课
     *
     * @return Response
     */
    @GetMapping("/closeElectiveCourse")
    public Result closeElectiveCourse() {
        Status.choiceStatus = !Status.choiceStatus;
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("choiceStatus", Status.choiceStatus);
        return Result.success(200, "关闭选课成功", map);
    }

    @GetMapping("/queryAllCourseNameByTeacher")
    public Result queryAllCourseNameByTeacher(String teacher) {
        if (teacher.equals("")) {
            teacher = "%";
        }
        Map<String, Object> map = new LinkedHashMap<>();
        List<Map<String, Object>> courseNameList = electiveCourseService.queryAllCourseNameByTeacher(teacher);
        int num = 0;
        for (Map<String, Object> courseName : courseNameList) {
            courseName.put("id", num++);
        }
        map.put("courseNameList", courseNameList);
        return Result.success(200, null, map);
    }

    @GetMapping("/queryCourseByStudent")
    public Result queryCourseByStudent() {
        String id = JWT.token(httpServletRequest.getHeader("Authorization"));
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("courseList", electiveCourseService.queryCourseByStudent(id));
        return Result.success(200, null, map);
    }

    /**
     * 教师查询班级选课
     *
     * @param grade 班级
     * @return Response
     */
    @GetMapping("/queryChoiceCourseByGrade")
    public Result queryChoiceCourseByGrade(String grade) {
        if (grade.equals(""))
            grade = "%";
        String teacher = JWT.token(httpServletRequest.getHeader("Authorization"));
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("courseList", electiveCourseService.queryCourseByTeacherAndGrade(teacher, grade));
        return Result.success(200, "查询成功", map);
    }

    /**
     * 通过老师查询选课
     *
     * @param teacher 教师
     * @return Response
     */
    @GetMapping("/queryChoiceCourseByTeacher")
    public Result queryChoiceCourseByTeacher(String teacher) {
        if (teacher.equals(""))
            teacher = "%";
        String student = JWT.token(httpServletRequest.getHeader("Authorization"));
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("courseList", electiveCourseService.queryChoiceCourseByTeacher(student, teacher));
        return Result.success(200, "查询成功", map);
    }

    @GetMapping("/queryElectiveCourseExcelData")
    public Result queryElectiveCourseExcelData() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("excelData", electiveCourseService.queryElectiveCourseExcelData());
        return Result.success(200, "查询成功", map);
    }
}
