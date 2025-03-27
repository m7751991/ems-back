package com.example.educationalsystembackend.controller;

import com.example.educationalsystembackend.result.Result;
import com.example.educationalsystembackend.service.GradeService;
import com.example.educationalsystembackend.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@Transactional
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private GradeService gradeService;

    /**
     * 查询所有成绩
     *
     * @param num  页面数
     * @param size 页面大小
     * @return Response
     */
    @GetMapping("/queryAllScore")
    public Result queryAllScore(int num, int size) {
        num = size * (num - 1);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("scoreList", scoreService.queryAllScore(num, size));
        map.put("count", scoreService.queryAllScoreCount());
        return Result.success(200, "查询成功", map);
    }

    /**
     * 删除成绩
     *
     * @param course  课程
     * @param student 学生
     * @return Response
     */
    @GetMapping("/deleteScore/{student}/{course}")
    public Result deleteScore(@PathVariable String student, @PathVariable String course) {
        scoreService.deleteScore(student, course);
        return Result.success(200, "删除成功", null);
    }

    /**
     * 查询学生成绩
     *
     * @param student 学生
     * @param num     页面数
     * @param size    页面大小
     * @return Response
     */
    @GetMapping("/queryScore")
    public Result queryScore(String student, int num, int size) {
        if (scoreService.queryScoreCount(student) == 0) return Result.success(400, "没有查到该学生的成绩", null);
        else {
            num = size * (num - 1);
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("scoreList", scoreService.queryScore(student, num, size));
            map.put("count", scoreService.queryScoreCount(student));
            return Result.success(200, "查询成功", map);
        }
    }

    @GetMapping("/queryScoreExcelData/{grade}")
    public Result queryScoreExcelData(@PathVariable("grade") String grade) {
        grade = gradeService.queryGradeIdByName(grade);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("excelData", scoreService.queryScoreExcelData(grade));
        return Result.success(200, "导出成功", map);
    }
}
