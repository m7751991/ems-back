package com.example.educationalsystembackend.mapper;

import com.example.educationalsystembackend.pojo.StudentEvaluation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface StudentEvaluationMapper {

    //学生分页查询所有评价
    @Select("SELECT c.name as course ,t.name as teacher,ch.`evaluation`,comment FROM choice ch,course c,teacher t WHERE ch.`course`=c.id AND c.`teacher`=t.`id` AND ch.`student`=#{student}")
    List<StudentEvaluation> queryStudentEvaluationList(String student);

    //学生添加评价
    @Update("UPDATE course c,choice ch SET evaluation=#{evaluation},comment=#{comment} WHERE ch.`course`=c.id AND c.name=#{course} AND c.`teacher`=#{teacher} AND ch.`student`=#{student}")
    void addEvaluation(String student, String course, String teacher, int evaluation, String comment);

    //学生查询评价
    @Select("SELECT c.name as course ,t.name as teacher,ch.`evaluation` FROM choice ch,course c,teacher t WHERE ch.`course`=c.id AND c.`teacher`=t.`id` AND ch.`student`=#{student} and c.id like #{course} and c.teacher like #{teacher}")
    List<StudentEvaluation> searchStudentEvaluation(String student, String course, String teacher);
}
