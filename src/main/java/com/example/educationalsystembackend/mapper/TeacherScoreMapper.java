package com.example.educationalsystembackend.mapper;

import com.example.educationalsystembackend.pojo.TeacherScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TeacherScoreMapper {

    //教师分页查询成绩
    @Select("select id,name as course,kind,score,credit from course where teacher=#{teacher} limit #{num},#{size}")
    List<TeacherScore> queryAllTeacherCourseList(String teacher, int num, int size);

    //教师查询成绩总数
    @Select("SELECT count(*) FROM course WHERE teacher=#{teacher} ")
    int queryAllTeacherCourseCount(String teacher);

    //教师查询课程
    @Select("select id,name as course,kind,score,credit from course where teacher=#{teacher} and name=#{course} limit #{num},#{size}")
    List<TeacherScore> queryTeacherCourse(String teacher, String course, int num, int size);

    //教师查询课程数量
    @Select("SELECT count(*) FROM course WHERE teacher=#{teacher} and name=#{course}")
    int queryTeacherCourseCount(String teacher, String course);

    @Select("SELECT s.id,s.name AS student,g.name AS grade,usual,exam,experiment,total FROM student s,grade g,choice ch WHERE s.`grade` = g.id AND ch.`student` = s.`id` AND ch.`course`=#{course}")
    List<TeacherScore> queryTeacherScoreStudentList(String course);

    //教师修改成绩
    @Update("update choice set usual=#{usual},experiment=#{experiment},exam=#{exam},total=#{total} where course=#{course} and student=#{id}")
    void addScore(TeacherScore teacherScore);

    int queryCourseScoreIsNULLCount(String course, int experiment);

    //修改成绩状态
    @Update("update course set score=#{score} where course.id=#{course}")
    void changeScoreStatus(String course, boolean score);
}
