package com.example.educationalsystembackend.mapper;

import com.example.educationalsystembackend.pojo.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ScoreMapper {

    //分页查询所有成绩
    @Select("SELECT s.id,s.name AS student,c.`id` AS course,credit,c.name as name,t.`name` AS teacher,c.kind, ch.usual,ch.exam,ch.experiment,ch.total,c.usual as usualRate,c.experiment as experimentRate,c.exam as examRate FROM choice ch,student s,course c,teacher t WHERE ch.`student`=s.id AND ch.`course`=c.id AND c.`teacher`=t.`id` AND c.`score`=TRUE limit #{num},#{size}")
    List<Score> queryAllScore(int num, int size);

    //查询成绩总数
    @Select("SELECT count(*) FROM course c,choice ch where c.id=ch.course and score=true")
    int queryAllScoreCount();

    //查询成绩
    @Select("SELECT s.id,s.name AS student,c.`id` AS course,credit,c.name as name,t.`name` AS teacher,c.kind, ch.usual,ch.exam,ch.experiment,ch.total,c.usual as usualRate,c.experiment as experimentRate,c.exam as examRate FROM choice ch,student s,course c,teacher t WHERE ch.`student`=s.id AND s.name=#{student} and ch.`course`=c.id AND c.`teacher`=t.`id` AND c.`score`=TRUE limit #{num},#{size}")
    List<Score> queryScore(String student, int num, int size);

    //查询考试数量
    @Select("SELECT count(*) FROM course c,choice ch,student s where c.id=ch.course and score=true and ch.student=s.id and s.name=#{student}")
    int queryScoreCount(String student);

    //删除成绩
    @Update("update choice set usual=0,experiment=0,exam=0,total=0  where student=#{student} and course=#{course}")
    void deleteScore(String student, String course);

    @Select("SELECT g.name AS grade,s.id,s.name AS student,c.kind,c.name AS course,ch.`total` FROM choice ch,course c,student s,grade g WHERE c.id = ch.course AND c.`score` = TRUE  AND ch.`student` = s.id AND s.`grade` = g.id AND g.id=#{grade} order by course,ch.student")
    List<Map<String, Object>> queryScoreExcelData(String grade);
}
