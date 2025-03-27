package com.example.educationalsystembackend.controller;

import com.example.educationalsystembackend.pojo.Status;
import com.example.educationalsystembackend.result.Result;
import com.example.educationalsystembackend.service.EvaluationService;
import com.example.educationalsystembackend.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@Transactional
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private TeacherService teacherService;

    /**
     * 查询所有评价
     *
     * @param num  页面数
     * @param size 页面大小
     * @return Response
     */
    @GetMapping("/queryAllEvaluation")
    public Result queryAllEvaluation(int num, int size) {
        num = size * (num - 1);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("evaluationList", evaluationService.queryAllEvaluation(num, size));
        map.put("teacherList", teacherService.queryAllTeacherIdAndName());
        map.put("count", evaluationService.queryAllEvaluationCount());
        map.put("evaluationStatus", Status.evaluationStatus);
        return Result.success(200, "查询成功", map);
    }

    /**
     * 查询评价
     *
     * @param teacher 教师
     * @param num     页面数
     * @param size    页面大小
     * @return Response
     */
    @GetMapping("/queryEvaluation")
    public Result queryEvaluation(String teacher, int num, int size) {
        int count = evaluationService.queryEvaluationCount(teacher);
        if (count == 0)
            return Result.success(400, "当前老师无评价", null);
        else {
            num = size * (num - 1);
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("evaluationList", evaluationService.queryEvaluation(teacher, num, size));
            map.put("count", count);
            return Result.success(200, "查询成功", map);
        }
    }

    /**
     * 开启教学评价
     *
     * @return Response
     */
    @GetMapping("/startEvaluation")
    public Result startEvaluation() {
        Status.evaluationStatus = !Status.evaluationStatus;
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("evaluationStatus", Status.evaluationStatus);
        return Result.success(200, "开启教评成功", map);
    }

    /**
     * 关闭教学评价
     *
     * @return Response
     */
    @GetMapping("/closeEvaluation")
    public Result closeEvaluation() {
        Status.evaluationStatus = !Status.evaluationStatus;
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("evaluationStatus", Status.evaluationStatus);
        return Result.success(200, "关闭教评成功", map);
    }
}
