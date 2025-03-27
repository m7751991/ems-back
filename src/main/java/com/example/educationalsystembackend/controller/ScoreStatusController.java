package com.example.educationalsystembackend.controller;

import com.example.educationalsystembackend.pojo.ScoreStatus;
import com.example.educationalsystembackend.result.Result;
import com.example.educationalsystembackend.service.ScoreStatusService;
import com.example.educationalsystembackend.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@Transactional
@RestController
public class ScoreStatusController {

    @Autowired
    private ScoreStatusService scoreStatusService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/queryAllScoreStatus/{num}/{size}")
    public Result queryAllScoreStatus(@PathVariable("num") int num, @PathVariable("size") int size) {
        num = size * (num - 1);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("scoreStatusList", scoreStatusService.queryAllScoreStatus(num, size));
        map.put("count", scoreStatusService.queryAllScoreStatusCount());
        map.put("teacherList", teacherService.queryAllTeacherIdAndName());
        return Result.success(200, "查询成绩状态成功", map);
    }

    @PostMapping("/queryScoreStatus/{num}/{size}")
    public Result queryScoreStatus(@RequestBody ScoreStatus scoreStatus, @PathVariable("num") int num, @PathVariable("size") int size) {
        num = size * (num - 1);
        String teacher = scoreStatus.getTeacher();
        if (teacher.equals(""))
            teacher = "%";
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("scoreStatusList", scoreStatusService.queryScoreStatus(teacher, scoreStatus.getScoreList(), num, size));
        map.put("count", scoreStatusService.queryScoreStatusCount(teacher, scoreStatus.getScoreList()));
        return Result.success(200, "查询成绩状态成功", map);
    }

    @GetMapping("/queryScoreStatusExcelData")
    public Result queryScoreStatusExcelData() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("excelData", scoreStatusService.queryScoreStatusExcelData());
        return Result.success(200, "查询成绩未提交成绩名单成功", map);
    }
}
