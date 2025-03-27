package com.example.educationalsystembackend.controller;

import com.example.educationalsystembackend.pojo.Choice;
import com.example.educationalsystembackend.result.Result;
import com.example.educationalsystembackend.service.ChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@Transactional
public class ChoiceController {

    @Autowired
    private ChoiceService choiceService;

    /**
     * 查询所有选课
     *
     * @param num  页面数
     * @param size 页面大小
     * @return Reponse
     */
    @GetMapping("/queryAllChoice")
    public Result queryAllChoice(int num, int size) {
        num = size * (num - 1);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("choiceList", choiceService.queryAllChoice(num, size));
        map.put("count", choiceService.queryAllChoiceCount());
        return Result.success(200, "查询选课成功", map);
    }

    /**
     * 删除选课
     *
     * @return Response
     */
    @PostMapping("/deleteChoice")
    public Result deleteChoice(@RequestBody Choice choice) {
        choiceService.deleteChoice(choice);
        return Result.success(200, "删除选课成功", null);
    }

    /**
     * 查询选课
     *
     * @param student 学生姓名
     * @param num     页面数
     * @param size    页面大小
     * @return Response
     */
    @GetMapping("/queryChoice")
    public Result queryChoice(String student, int num, int size) {
        int count = choiceService.queryChoiceCount(student);
        if (count == 0) {
            return Result.success(400, "没有查到该学生的选课", null);
        } else {
            num = size * (num - 1);
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("choiceList", choiceService.queryChoice(student, num, size));
            map.put("count", count);
            return Result.success(200, "查询选课成功", map);
        }
    }

    @GetMapping("/queryChoiceExcelData")
    public Result queryChoiceExcelData() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("excelData", choiceService.queryChoiceExcelData());
        return Result.success(200, "导出选课成功", map);
    }
}
