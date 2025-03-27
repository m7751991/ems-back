package com.example.educationalsystembackend.mapper;

import com.example.educationalsystembackend.pojo.Schedule;
import com.example.educationalsystembackend.pojo.TeacherChoice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TeacherChoiceMapper {

    //教师分页查询所有选课
    @Select("SELECT c.id,kind,s.name,s.id as student,c.name AS course,g.name as grade FROM course c,choice ch,student s,grade g WHERE g.id=s.grade and ch.`student`=s.`id` AND ch.`course`=c.id AND c.`teacher`=#{teacher} limit #{num},#{size}")
    List<TeacherChoice> queryAllTeacherChoice(String teacher, int num, int size);

    //教师查询选课总数
    @Select("SELECT COUNT(*) FROM choice ch,course c WHERE ch.`course`=c.`id` AND c.`teacher`=#{teacher}")
    int queryAllTeacherChoiceCount(String teacher);

    //教师查询选课
    @Select("SELECT c.id,kind,s.name,s.id as student,c.name AS course,g.name as grade FROM course c,choice ch,student s,grade g WHERE g.id=s.grade and ch.`student`=s.`id` AND ch.`course`=c.id AND c.`teacher`=#{teacher} and c.name=#{course} limit #{num},#{size}")
    List<TeacherChoice> queryTeacherChoice(String teacher, String course, int num, int size);

    //教师查询选课数量
    @Select("SELECT COUNT(*) FROM choice ch,course c WHERE ch.`course`=c.`id` AND c.`teacher`=#{teacher} and c.name=#{course}")
    int queryTeacherChoiceCount(String teacher, String course);

    //教师删除学生选课
    @Delete("delete from choice where student=#{student} and course=#{course}")
    void deleteTeacherChoice(String student, String course);

    //教师查询课表
    @Select("SELECT c.name AS course,t.name AS teacher,cl.`name` AS classroom,WEEK,START,END,kind,CEILING(DATEDIFF(`from`,'2023-02-12')/7) as `from`,CEILING(DATEDIFF(`to`,'2023-02-12')/7) as `to` FROM course c,teacher t,classroom cl WHERE  c.`classroom`=cl.`id` AND  c.teacher=t.id AND t.id=#{teacher}")
    List<Schedule> queryTeacherSchedule(String teacher);
}
