package com.example.educationalsystembackend.mapper;

import com.example.educationalsystembackend.pojo.TeacherEvaluation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TeacherEvaluationMapper {

    //教师分页查询所有评价
    @Select("SELECT s.name AS student,g.name AS grade,c.name AS course,evaluation,comment FROM grade g, choice ch,student s,course c WHERE ch.`course`=c.id AND ch.`student`=s.id AND s.`grade`=g.`id` AND c.teacher=#{teacher} AND evaluation IS NOT NULL limit #{num},#{size}")
    List<TeacherEvaluation> queryAllTeacherEvaluation(String teacher, int num, int size);

    //教师查询评价总数
    @Select("SELECT count(*) FROM choice ch,course c WHERE ch.`course`=c.id AND c.`teacher`=#{teacher} AND evaluation IS NOT NULL")
    int queryAllTeacherEvaluationCount(String teacher);

    //教师查询评价
    @Select("SELECT s.name AS student,g.name AS grade,c.name AS course,evaluation,comment FROM grade g, choice ch,student s,course c WHERE ch.`course`=c.id AND ch.`student`=s.id AND s.`grade`=g.`id` AND c.teacher=#{teacher} and c.name like #{course} and g.name like #{grade} AND evaluation IS NOT NULL limit #{num},#{size}")
    List<TeacherEvaluation> queryTeacherEvaluation(String teacher, String course, String grade, int num, int size);

    //教师查询评价数量
    @Select("SELECT COUNT(*) FROM grade g, choice ch,student s,course c WHERE ch.`course`=c.id AND ch.`student`=s.id AND s.`grade`=g.`id` AND c.teacher=#{teacher} AND c.teacher=#{teacher} and c.name like #{course} and g.name like #{grade} AND evaluation IS NOT NULL")
    int queryTeacherEvaluationCount(String teacher, String course, String grade);
}
