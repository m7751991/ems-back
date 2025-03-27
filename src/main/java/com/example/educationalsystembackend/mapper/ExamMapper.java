package com.example.educationalsystembackend.mapper;

import com.example.educationalsystembackend.pojo.Exam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface ExamMapper {

    //分页查询所有考试
    @Select("SELECT g.name AS grade,c.name AS course,c.id,g.number,credit,t.`name` AS teacher,r.supervisor AS supervisor,cl.`name` AS classroom,r.date,r.start,r.end FROM requiredcourse r,course c,teacher t,grade g,classroom cl WHERE r.`course`=c.id AND r.`grade`=g.id AND r.`classroom`=cl.`id` AND c.`teacher`=t.`id` limit #{num},#{size}")
    List<Exam> queryAllExam(int num, int size);

    //查询考试总数
    @Select("SELECT count(*) FROM requiredcourse where supervisor is not null and classroom is not null")
    int queryAllExamCount();

    //查询班级考试
    @Select("SELECT g.name AS grade,c.id,c.name AS course,g.number,credit,t.`name` AS teacher,r.supervisor AS supervisor,cl.`name` AS classroom,r.date,r.start,r.end FROM requiredcourse r,course c,teacher t,grade g,classroom cl WHERE r.`course`=c.id AND r.`grade`=g.id AND r.`classroom`=cl.`id` AND c.`teacher`=t.`id` and g.id=#{grade} limit #{num},#{size}")
    List<Exam> queryExam(String grade, int num, int size);

    //查询班级考试数量
    @Select("SELECT count(*) FROM requiredcourse where grade=#{grade} and supervisor is not null and classroom is not null")
    int queryExamCount(String grade);

    //删除考试
    @Delete("update requiredcourse set supervisor=null,classroom=null,date=null,start=null,end=null where course=#{course} and grade=#{grade}")
    void deleteExam(String course, String grade);

    //添加考试
    @Update("update requiredcourse set supervisor=#{supervisor},classroom=#{classroom},date=#{date},start=#{start},end=#{end} where grade=#{grade} and course=#{course}")
    void addExam(Exam exam);

    //修改考试
    @Update("update requiredcourse set supervisor=#{supervisor},classroom=#{classroom},date=#{date},start=#{start},end=#{end} where course=#{course} and grade=#{grade}")
    void alterExam(Exam exam);

    @Select("SELECT g.name AS grade,c.name AS course,g.number,t.`name` AS teacher,r.supervisor,cl.`name` AS classroom,r.date,r.start,r.end FROM requiredcourse r,course c,teacher t,grade g,classroom cl WHERE r.`course`=c.id AND r.`grade`=g.id AND r.`classroom`=cl.`id` AND c.`teacher`=t.`id` and r.date between #{from} and #{to}")
    List<Exam> queryExamExcelData(Date from, Date to);

    @Select("SELECT COUNT(*) FROM requiredcourse WHERE grade=#{grade} and DATE=#{date} and (`start` between #{start} and #{end} or `end` between #{start} and #{end} or (`start`<#{start} and `end`>#{end})) and course!=#{course}")
    int queryExamCourseMoreTimeNumber(Exam exam);

    @Select("SELECT COUNT(*) FROM requiredcourse WHERE classroom=#{classroom} and DATE=#{date} and (`start` between #{start} and #{end} or `end` between #{start} and #{end} or (`start`<#{start} and `end`>#{end}))")
    int queryExamClassroomMoreTimeNumber(Exam exam);

    @Select("SELECT COUNT(*) FROM requiredcourse WHERE supervisor=#{supervisor} and DATE=#{date} and (`start` between #{start} and #{end} or `end` between #{start} and #{end} or (`start`<#{start} and `end`>#{end}))")
    int queryExamTeacherMoreTimeNumber(Exam exam);
}
