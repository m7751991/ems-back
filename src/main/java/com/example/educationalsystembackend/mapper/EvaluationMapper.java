package com.example.educationalsystembackend.mapper;

import com.example.educationalsystembackend.pojo.Evaluation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EvaluationMapper {

    //分页查询所有评价
    @Select("SELECT s.name AS student,c.name AS course,t.name AS teacher,evaluation,comment FROM choice ch,student s,teacher t,course c WHERE ch.`course`=c.id AND ch.`student`=s.id AND c.`teacher`=t.id AND evaluation IS NOT NULL limit #{num},#{size}")
    List<Evaluation> queryAllEvaluation(int num, int size);

    //查询评价总数
    @Select("select count(*) from choice where evaluation is not null")
    int queryAllEvaluationCount();

    //查询评价
    @Select("SELECT s.name AS student,c.name AS course,t.name AS teacher,evaluation,comment FROM choice ch,student s,teacher t,course c WHERE ch.`course`=c.id AND ch.`student`=s.id AND c.`teacher`=t.id and c.teacher=#{teacher} AND evaluation IS NOT NULL limit #{num},#{size}")
    List<Evaluation> queryEvaluation(String teacher, int num, int size);

    //查询评价数量
    @Select("select count(*) from choice ch,course c where c.id=ch.course and c.teacher=#{teacher} and evaluation is not null")
    int queryEvaluationCount(String teacher);
}
