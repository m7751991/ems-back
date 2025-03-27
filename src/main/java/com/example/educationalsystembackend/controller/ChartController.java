package com.example.educationalsystembackend.controller;

import com.example.educationalsystembackend.pojo.Chart;
import com.example.educationalsystembackend.result.Result;
import com.example.educationalsystembackend.service.ChartService;
import com.example.educationalsystembackend.service.ElectiveCourseService;
import com.example.educationalsystembackend.service.GradeService;
import com.example.educationalsystembackend.util.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@Transactional
public class ChartController {

    @Autowired
    private ChartService chartService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private ElectiveCourseService electiveCourseService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping("queryStudentChart")
    public Result queryStudentChart() {
        Map<String, Object> map = new LinkedHashMap<>();
        String student = JWT.token(httpServletRequest.getHeader("Authorization"));
        List<Float> scoreList = new ArrayList<>();
        for (String id : chartService.queryStudentChartId(student)) {
            scoreList.add(chartService.queryStudentChartZ(id));
        }
        map.put("studentChartX", chartService.queryStudentChartX(student));
        map.put("studentChartY", chartService.queryStudentChartY(student));
        map.put("studentChartZ", scoreList);
        return Result.success(200, "查询图表成功", map);
    }

    /**
     * 教师查询图表
     *
     * @param course 课程
     * @param grade  年级
     * @return Response
     */
    @GetMapping("queryTeacherChart")
    public Result queryTeacherChart(String course, String grade) {
        if (course.equals("")) course = "%";
        if (grade.equals("")) grade = "%";
        Map<String, Object> map = new LinkedHashMap<>();
        String teacher = JWT.token(httpServletRequest.getHeader("Authorization"));
        List<Chart> chartList = new ArrayList<>();
        Chart chart1 = new Chart();
        chart1.setName("不及格(0-59)");
        chart1.setValue(chartService.queryTeacherChart(teacher, course, grade, 0, 59));
        if (chart1.getValue() != 0) chartList.add(chart1);
        Chart chart2 = new Chart();
        chart2.setName("及格(60-69)");
        chart2.setValue(chartService.queryTeacherChart(teacher, course, grade, 60, 69));
        if (chart2.getValue() != 0) chartList.add(chart2);
        Chart chart3 = new Chart();
        chart3.setName("中等(70-79)");
        chart3.setValue(chartService.queryTeacherChart(teacher, course, grade, 70, 79));
        if (chart3.getValue() != 0) chartList.add(chart3);
        Chart chart4 = new Chart();
        chart4.setName("良好(80-89)");
        chart4.setValue(chartService.queryTeacherChart(teacher, course, grade, 80, 89));
        if (chart4.getValue() != 0) chartList.add(chart4);
        Chart chart5 = new Chart();
        chart5.setName("优秀(90-100)");
        chart5.setValue(chartService.queryTeacherChart(teacher, course, grade, 90, 100));
        if (chart5.getValue() != 0) chartList.add(chart5);
        map.put("teacherChartList", chartList);
        map.put("courseList", electiveCourseService.queryCourseByTeacherAndGrade(teacher, grade));
        map.put("gradeList", gradeService.queryTeacherChoiceGradeByCourse(course, teacher));
        return Result.success(200, "查询图表成功", map);
    }

    @GetMapping("queryTeacherDataChart")
    public Result queryTeacherDataChart(String course, String grade) {
        if (course.equals("")) course = "%";
        if (grade.equals("")) grade = "%";
        else grade = gradeService.queryGradeIdByName(grade);
        Map<String, Object> map = new LinkedHashMap<>();
        String teacher = JWT.token(httpServletRequest.getHeader("Authorization"));
        String[] x = {"最高分", "最低分", "平均分", "方差", "标准差"};
        float[] y = new float[5];
        y[0] = chartService.queryDataMax(teacher, course, grade);
        y[1] = chartService.queryDataMin(teacher, course, grade);
        y[2] = chartService.queryDataAvg(teacher, course, grade);
        y[3] = chartService.queryDataVariance(teacher, course, grade);
        y[4] = chartService.queryDataStddev(teacher, course, grade);
        map.put("dataChartX", x);
        map.put("dataChartY", y);
        return Result.success(200, "查询图表成功", map);
    }

    /**
     * 管理员图表
     *
     * @return Response
     */
    @GetMapping("queryChart")
    public Result queryChart() {
        Map<String, Object> map = new LinkedHashMap<>();
        List<Chart> chartList = new ArrayList<>();
        Chart chart1 = new Chart();
        chart1.setName("不及格(0-59)");
        chart1.setValue(chartService.queryChart(0, 59));
        if (chart1.getValue() != 0) chartList.add(chart1);
        Chart chart2 = new Chart();
        chart2.setName("及格(60-69)");
        chart2.setValue(chartService.queryChart(60, 69));
        if (chart2.getValue() != 0) chartList.add(chart2);
        Chart chart3 = new Chart();
        chart3.setName("中等(70-79)");
        chart3.setValue(chartService.queryChart(70, 79));
        if (chart3.getValue() != 0) chartList.add(chart3);
        Chart chart4 = new Chart();
        chart4.setName("良好(80-89)");
        chart4.setValue(chartService.queryChart(80, 89));
        if (chart4.getValue() != 0) chartList.add(chart4);
        Chart chart5 = new Chart();
        chart5.setName("优秀(90-100)");
        chart5.setValue(chartService.queryChart(90, 100));
        if (chart5.getValue() != 0) chartList.add(chart5);
        map.put("chartList", chartList);
        return Result.success(200, "查询图表成功", map);
    }
}
