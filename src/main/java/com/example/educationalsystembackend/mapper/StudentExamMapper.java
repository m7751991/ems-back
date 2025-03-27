package com.example.educationalsystembackend.mapper;

import com.example.educationalsystembackend.pojo.StudentExam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentExamMapper {

    //分页查询学生所有考试
    @Select("SELECT c.name AS course,t.name AS `supervisor`,cl.name AS classroom,r.date,r.start,r.end FROM classroom cl,teacher t,grade g,requiredcourse r,course c WHERE r.`classroom`=cl.id AND c.id=r.`course` AND r.`supervisor`=t.`id` AND r.`grade`=g.`id` AND r.`grade`=#{grade} limit #{num},#{size}")
    List<StudentExam> queryStudentExam(String grade, int num, int size);

    //查询学生考试总数
    @Select("SELECT count(*) FROM requiredcourse WHERE grade=#{grade} AND  supervisor IS NOT NULL AND classroom IS NOT NULL")
    int queryStudentExamCount(String grade);

    //查询学生考试
    @Select("SELECT c.name AS course,t.name AS `supervisor`,cl.name AS classroom,r.date,r.start,r.end FROM classroom cl,teacher t,grade g,requiredcourse r,course c WHERE r.`classroom`=cl.id AND c.id=r.`course` AND r.`supervisor`=t.`id` AND r.`grade`=g.`id` AND r.`grade`=#{grade} and c.id=#{course}")
    List<StudentExam> queryStudentExamByCourse(String grade, String course);

    //查询学生考试数量
    @Select("SELECT count(*) FROM requiredcourse WHERE grade=#{grade} and course=#{course} AND supervisor IS NOT NULL AND classroom IS NOT NULL")
    int queryStudentExamCountByCourse(String grade, String course);
}
