package com.example.educationalsystembackend.controller;

import com.example.educationalsystembackend.pojo.Classroom;
import com.example.educationalsystembackend.result.Result;
import com.example.educationalsystembackend.service.ClassroomService;
import com.example.educationalsystembackend.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@Transactional
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private TeacherService teacherService;

    /**
     * 查询所有教室
     *
     * @param num  页面数
     * @param size 页面大小
     * @return Response
     */
    @GetMapping("/queryAllClassroom")
    public Result queryAllClassroom(int num, int size) {
        num = size * (num - 1);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("classroomList", classroomService.queryAllClassroom(num, size));
        map.put("count", classroomService.queryAllClassroomCount());
        return Result.success(200, "查询教室成功", map);
    }

    /**
     * 添加教室
     *
     * @param classroom 教室
     * @return Response
     */
    @PostMapping("/addClassroom")
    public Result addClassroom(@RequestBody Classroom classroom) {
        if (classroomService.queryClassroomCount(classroom.getId()) == 0) {
            // 设置教室初始状态为空闲
            classroom.setStatus(0);
            classroomService.addClassroom(classroom);
            return Result.success(200, "添加教室成功", null);
        } else {
            return Result.success(400, "添加失败，当前教室号已存在", null);
        }
    }

    /**
     * 删除教室
     *
     * @param id 教室号
     * @return Response
     */
    @GetMapping("/deleteClassroom")
    public Result deleteClassroom(String id) {
        classroomService.deleteClassroom(id);
        return Result.success(200, "删除教室成功", null);
    }

    /**
     * 修改教室
     *
     * @param classroom 教室
     * @return Response
     */
    @PostMapping("/alterClassroom")
    public Result alterClassroom(@RequestBody Classroom classroom) {
        classroomService.updateClassroom(classroom);
        return Result.success(200, "修改教室成功", null);
    }

    /**
     * 查询教室
     *
     * @param id 教室号
     * @return Response
     */
    @GetMapping("/queryClassroom")
    public Result queryClassroom(String id) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("classroomList", classroomService.queryClassroom(id));
        map.put("count", classroomService.queryClassroomCount(id));
        return Result.success(200, "查询教室成功", map);
    }

    /**
     * 查询满足必修课班级人数的教室
     *
     * @param number 人数
     * @return Response
     */
    @GetMapping("/queryExamClassroomAndSupervisorByGrade")
    public Result queryClassroomByGrade(int number) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("classroomList", classroomService.queryClassroomByNumber(number));
        map.put("teacherList", teacherService.queryAllTeacherIdAndName());
        return Result.success(200, "查询成功", map);
    }

    /**
     * 查询满足选修课人数的教室
     *
     * @return Reponse
     */
    @GetMapping("/queryAllClassroomIdAndName")
    public Result queryAllClassroomIdAndName() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("classroomList", classroomService.queryClassroomByNumber(0));
        return Result.success(200, "查询教室成功", map);
    }

    /**
     * 查询满足课程人数的教室
     *
     * @return Reponse
     */
    @GetMapping("/queryClassroomByNumber")
    public Result queryClassroomByNumber(int number) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("classroomList", classroomService.queryClassroomByNumber(number));
        return Result.success(200, "查询成功", map);
    }

    @GetMapping("/queryClassroomExcelData")
    public Result queryClassroomExcelData() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("excelData", classroomService.queryClassroomExcelData());
        return Result.success(200, "导出教室成功", map);
    }

    @PostMapping("/uploadClassroom")
    public Result uploadClassroom(@RequestBody List<Classroom> classroomList) {
        // 设置教室初始状态为空闲
        // for (Classroom classroom : classroomList) {
        //     classroom.setStatus(0);
        // }
        classroomService.addClassrooms(classroomList);
        return Result.success(200, "导入教室成功", null);
    }

    /**
     * 更新教室状态
     *
     * @param id     教室号
     * @param status 状态（空闲/使用中）
     * @return Response
     */
    @GetMapping("/updateClassroomStatus")
    public Result updateClassroomStatus(String id, String status) {
        if (!status.equals("空闲") && !status.equals("使用中")) {
            return Result.success(400, "状态值无效", null);
        }
        classroomService.updateClassroomStatus(id, status);
        return Result.success(200, "更新教室状态成功", null);
    }

}
