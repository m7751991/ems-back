package com.example.educationalsystembackend.mapper;

import com.example.educationalsystembackend.pojo.StudentScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentScoreMapper {

    @Select("SELECT c.name as course,t.name as teacher,credit,ch.`total`,c.kind FROM choice ch,student s,course c ,teacher t WHERE c.teacher=t.id and ch.`course`=c.id AND ch.`student`=s.`id` AND ch.`student`=#{student} AND c.`score`=TRUE limit #{num},#{size}")
    List<StudentScore> queryStudentScore(String student, int num, int size);

    @Select("SELECT c.name as course,t.name as teacher,credit,ch.`total`,c.kind FROM choice ch,student s,course c ,teacher t WHERE c.teacher=t.id and ch.`course`=c.id AND ch.`student`=s.`id` AND ch.`student`=#{student} AND c.`score`=TRUE order by ${prop} ${order} limit #{num},#{size}")
    List<StudentScore> queryStudentScoreByOrder(String student, String prop, String order, int num, int size);

    @Select("SELECT COUNT(*) FROM choice ch,course c WHERE ch.`course`=c.id AND ch.student=#{Student} AND c.`score`=TRUE")
    int queryStudentScoreCount(String student);

    @Select("SELECT c.name as course,t.name as teacher,credit,ch.`total`,c.kind FROM choice ch,student s,course c ,teacher t WHERE c.teacher=t.id and ch.`course`=c.id AND ch.`student`=s.`id` AND ch.`student`=#{student} AND c.`score`=TRUE and c.name=#{course} limit #{num},#{size}")
    List<StudentScore> queryStudentScoreByCourese(String student, String course, int num, int size);

    @Select("SELECT COUNT(*) FROM choice ch,course c WHERE ch.`course`=c.id AND ch.student=#{student} AND c.`score`=TRUE and c.name=#{course}")
    int queryStudentScoreCountByCourse(String student, String course);

    @Select("select max(total) from course c,choice ch where ch.course=c.id and ch.student=#{student} and c.name=#{course}")
    Integer queryStudentCourseMaxScore(String student, String course);
}
