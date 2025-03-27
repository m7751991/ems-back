package com.example.educationalsystembackend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ChartMapper {

    //学生图表X轴
    @Select("SELECT c.name FROM course c,choice ch WHERE ch.`course`=c.`id` AND c.`score`=TRUE AND ch.`student`=#{student} and c.kind=1 ORDER BY ch.`total`")
    String[] queryStudentChartX(String student);

    //学生图表Y轴
    @Select("SELECT ch.total FROM course c,choice ch WHERE ch.`course`=c.`id` AND c.`score`=TRUE AND ch.`student`=#{student} and c.kind=1 ORDER BY ch.`total`")
    int[] queryStudentChartY(String student);

    @Select("SELECT AVG(total) FROM choice WHERE course=#{course}")
    float queryStudentChartZ(String course);

    @Select("SELECT c.id FROM course c,choice ch WHERE ch.`course`=c.`id` AND c.`score`=TRUE AND ch.`student`=#{student} and c.kind=1 ORDER BY ch.`total`")
    String[] queryStudentChartId(String student);

    //图表总人数
    @Select("SELECT count(*) FROM choice ch,course c,student s ,grade g WHERE g.id=s.grade and ch.`course`=c.`id` AND ch.`student`=s.id AND c.`teacher`=#{teacher} AND c.name like #{course}  and g.name like #{grade} AND ch.`total` BETWEEN #{start} AND #{end} and c.score=true")
    int queryTeacherChart(String teacher, String course, String grade, int start, int end);

    //图表成绩人数
    @Select("SELECT COUNT(*) FROM choice ch,course c WHERE ch.`course`=c.id AND c.`score`=TRUE AND ch.`total` BETWEEN #{start} and #{end}")
    int queryChart(int start, int end);

    @Select("select max(total) from choice ch,course c,student s where ch.course=c.id and ch.student=s.id and c.teacher=#{teacher} and c.name like #{course} and s.grade like #{grade} and c.score=true")
    Float queryDataMax(String teacher, String course, String grade);

    @Select("select min(total) from choice ch,course c,student s where ch.course=c.id and ch.student=s.id and c.teacher=#{teacher} and c.name like #{course} and s.grade like #{grade} and c.score=true")
    Float queryDataMin(String teacher, String course, String grade);

    @Select("select avg(total) from choice ch,course c,student s where ch.course=c.id and ch.student=s.id and c.teacher=#{teacher} and c.name like #{course} and s.grade like #{grade} and c.score=true")
    Float queryDataAvg(String teacher, String course, String grade);

    @Select("select VARIANCE(total) from choice ch,course c,student s where ch.course=c.id and ch.student=s.id and c.teacher=#{teacher} and c.name like #{course} and s.grade like #{grade} and c.score=true")
    Float queryDataVariance(String teacher, String course, String grade);

    @Select("select STDDEV(total) from choice ch,course c,student s where ch.course=c.id and ch.student=s.id and c.teacher=#{teacher} and c.name like #{course} and s.grade like #{grade} and c.score=true")
    Float queryDataStddev(String teacher, String course, String grade);
}
