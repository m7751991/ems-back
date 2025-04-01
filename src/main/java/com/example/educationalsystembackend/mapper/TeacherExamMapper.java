package com.example.educationalsystembackend.mapper;

import com.example.educationalsystembackend.pojo.TeacherExam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TeacherExamMapper {

    //教师分页查询考试
    @Select("SELECT c.name ,g.name AS grade,t.name AS supervisor,cl.name AS classroom,r.date,r.start,r.end FROM course c,requiredcourse r,teacher t,classroom cl,grade g WHERE c.id=r.`course` AND r.`supervisor`=t.`id` AND r.`classroom`=cl.id AND r.`grade`=g.id AND c.teacher=#{teacher} limit #{num},#{size}")
    List<TeacherExam> queryAllTeacherExam(String teacher, int num, int size);

    //教师查询考试总数
    @Select("SELECT COUNT(*) FROM course c,requiredcourse r WHERE c.id=r.`course` AND r.`classroom` IS NOT NULL AND r.`supervisor` IS NOT NULL AND c.`teacher`=#{teacher}")
    int queryAllTeacherExamCount(String teacher);

    //教师查询考试
    @Select("SELECT c.name AS course,g.name AS grade,t.name AS supervisor,cl.name AS classroom,r.date,r.start,r.end FROM course c,requiredcourse r,teacher t,classroom cl,grade g WHERE c.id=r.`course` AND r.`supervisor`=t.`id` AND r.`classroom`=cl.id AND r.`grade`=g.id AND c.teacher=#{teacher} and c.id=#{course} limit #{num},#{size}")
    List<TeacherExam> queryTeacherExam(String teacher, String course, int num, int size);

    //教师查询考试数量
    @Select("SELECT COUNT(*) FROM course c,requiredcourse r WHERE c.id=r.`course` AND r.`classroom` IS NOT NULL AND r.`supervisor` IS NOT NULL AND c.`teacher`=#{teacher}")
    int queryTeacherExamCount(String teacher, String course);

    //教师查询所有监考
    @Select("SELECT c.name AS course,g.name AS grade,t.name AS teacher,cl.name AS classroom,r.date,r.start,r.end FROM course c,requiredcourse r,teacher t,classroom cl,grade g WHERE c.id=r.`course` AND c.`teacher`=t.`id` AND r.`classroom`=cl.id AND r.`grade`=g.id AND r.`supervisor`=#{teacher} limit #{num},#{size}")
    List<TeacherExam> queryAllSuperviseExam(String teacher, int num, int size);

    //教师查询监考总数
    @Select("SELECT COUNT(*) FROM requiredcourse where supervisor=#{teacher}")
    int queryAllSuperviseExamCount(String teacher);

    //教室查询监考
    @Select("SELECT c.name AS course,g.name AS grade,t.name AS teacher,cl.name AS classroom,r.date,r.start,r.end FROM course c,requiredcourse r,teacher t,classroom cl,grade g WHERE c.id=r.`course` AND c.`teacher`=t.`id` AND r.`classroom`=cl.id AND r.`grade`=g.id AND r.`supervisor`=#{teacher} and r.grade=#{grade} limit #{num},#{size}")
    List<TeacherExam> querySuperviseExam(String teacher, String grade, int num, int size);

    //教室查询监考数量
    @Select("SELECT COUNT(*) FROM requiredcourse where supervisor=#{teacher} and grade =#{grade}")
    int querySuperviseExamCount(String teacher, String grade);
}
