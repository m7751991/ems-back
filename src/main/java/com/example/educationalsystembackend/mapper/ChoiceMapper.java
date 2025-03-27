package com.example.educationalsystembackend.mapper;

import com.example.educationalsystembackend.pojo.Choice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ChoiceMapper {

    //分页查询所有选课
    @Select("SELECT c.id,s.id AS student,s.name,c.name AS course,g.name AS grade,t.name AS teacher,kind FROM grade g,choice ch,student s,course c,teacher t WHERE s.id=ch.student AND ch.course=c.id AND s.`grade`=g.id AND c.teacher=t.id limit #{num},#{size}")
    List<Choice> queryAllChoice(int num, int size);

    //查询选课总数
    @Select("select count(*) from choice")
    int queryAllChoiceCount();

    //分页查询选课
    @Select("SELECT c.id,s.id AS student,s.name,c.name AS course,g.name AS grade,t.name AS teacher,kind FROM grade g,choice ch,student s,course c,teacher t WHERE s.id=ch.student AND ch.course=c.id AND s.`grade`=g.id  AND c.teacher=t.id and ch.student=#{student} limit #{num},#{size}")
    List<Choice> queryChoice(String student, int num, int size);

    //查询选课总数
    @Select("select count(*) from choice where student=#{student}")
    int queryChoiceCount(String student);

    //删除选课
    @Delete("delete from choice where course=#{course} and student=#{student}")
    void deleteChoice(Choice choice);

    //查询课程的选课数量
    @Select("select count(*) from choice where course=#{course}")
    int queryChoiceNumber(String course);

    @Select("select c.id,s.name as student,c.name as course,t.name as teacher,kind from choice ch,student s,course c,teacher t where s.id=ch.student and ch.course=c.id and c.teacher=t.id order by ch.student")
    List<Choice> queryChoiceExcelData();
}
