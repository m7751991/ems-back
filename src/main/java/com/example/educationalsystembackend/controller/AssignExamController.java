package com.example.educationalsystembackend.controller;

import com.example.educationalsystembackend.result.Result;
import com.example.educationalsystembackend.service.AssignExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@Transactional
public class AssignExamController {

    @Autowired
    private AssignExamService assignExamService;

    @GetMapping("/queryAllAssignExam")
    public Result queryAllAssignExam(int num, int size) {
        num = size * (num - 1);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("assignExamList", assignExamService.queryALLAssignExam(num, size));
        map.put("count", assignExamService.queryALLAssignExamCount());
        return Result.success(200, "查询考试成功", map);
    }

    @GetMapping("/queryAssignExam")
    public Result queryAssignExam(String course, int num, int size) {
        num = size * (num - 1);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("assignExamList", assignExamService.queryAssignExam(course, num, size));
        map.put("count", assignExamService.queryAssignExamCount(course));
        return Result.success(200, "查询考试成功", map);
    }
}
