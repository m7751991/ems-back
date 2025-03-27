package com.example.educationalsystembackend.mapper;

import com.example.educationalsystembackend.pojo.RequiredCourse;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface RequiredCourseMapper {

    //分页查询必修课列表
    @Select("SELECT c.id,c.name AS course,GROUP_CONCAT(g.name) AS grade,sum(g.number) as `number`,credit,c.week,c.from,c.to,c.start,c.end,t.name AS teacher,cl.name AS classroom,CEILING(DATEDIFF(`from`,'2023-02-12')/7) as zcFrom,CEILING(DATEDIFF(`to`,'2023-02-12')/7) as zcTo FROM requiredcourse r,grade g,course c,teacher t,classroom cl WHERE r.`grade` = g.id AND r.`course` = c.id AND c.`teacher`=t.`id` AND c.`classroom`=cl.`id` and c.kind=1 GROUP BY r.`course` limit #{num},#{size}")
    List<RequiredCourse> queryAllRequiredCourse(int num, int size);

    @Select("SELECT c.id,c.name AS course,g.name AS grade,g.number FROM requiredcourse r,grade g,course c WHERE r.`grade`=g.id AND r.`course`=c.id and c.id=#{id}")
    List<Map<String, Object>> queryRequiredCourseChildren(String id);

    //查询必修课总数
    @Select("select count(*) from course where kind=1")
    int queryAllRequiredCourseCount();

    //查询必修课
    @Select("SELECT c.id,c.name AS course,GROUP_CONCAT(g.name) AS grade,credit,sum(g.number) as `number`,c.week,c.from,c.to,c.start,c.end,t.name AS teacher,cl.name AS classroom,CEILING(DATEDIFF(`from`,'2023-02-12')/7) as zcFrom,CEILING(DATEDIFF(`to`,'2023-02-12')/7) as zcTo FROM requiredcourse r,grade g,course c,teacher t,classroom cl WHERE r.`grade` = g.id AND r.`course` = c.id AND c.`teacher`=t.`id` AND c.`classroom`=cl.`id` AND c.kind=1 GROUP BY r.`course` HAVING GROUP_CONCAT(g.name) LIKE #{grade} limit #{num},#{size}")
    List<RequiredCourse> queryRequiredCourse(String grade, int num, int size);

    //查询必修课数量
    @Select("select count(*) from requiredcourse where grade=#{grade}")
    int queryRequiredCourseCount(String grade);

    //通过课程号查询必修课数量
    @Select("select count(*) from requiredcourse where course=#{course}")
    int queryRequiredCourseCountById(String course);

    //删除必修课
    @Delete("delete from requiredcourse where course=#{course} and grade=#{grade}")
    void deleteRequiredCourse(String course, String grade);

    //添加必修课
    @Insert("insert into requiredcourse(course,grade) values(#{course},#{grade})")
    void addRequiredCourse(String course, String grade);

    //查询上该必修课的班级数
    @Select("select count(*) from requiredcourse where course=#{course}")
    int queryGradeCount(String course);

    @Select("SELECT c.id,c.name AS course FROM choice ch,course c WHERE ch.`course`=c.id AND ch.`student`=#{student} AND c.`kind`=1")
    List<Map<String, Object>> queryStudentRequiredCourse(String student);

    //查询教师必修课课程号和课程名
    @Select("SELECT c.`id`,c.name AS course FROM course c,requiredcourse r WHERE r.`course`=c.`id` AND c.teacher=#{teacher} group by c.name")
    List<Map<String, Object>> queryTeacherRequiredCourse(String teacher);

    //修改必修课
    @Update("update course set name=#{course},credit=#{credit},teacher=#{teacher},week=#{week},start=#{start},end=#{end},classroom=#{classroom},`from`=#{from},`to`=#{to} where id=#{id}")
    void updateRequiredCourse(RequiredCourse requiredCourse);

    //查询必修课表格数据
    @Select("SELECT c.id,c.name AS course,credit,GROUP_CONCAT(g.name) AS grade,c.`number`,c.week,c.from,c.to,c.start,c.end,t.name AS teacher,cl.name AS classroom,CEILING(DATEDIFF(`from`,'2023-02-12')/7) as zcFrom,CEILING(DATEDIFF(`to`,'2023-02-12')/7) as zcTo FROM requiredcourse r,grade g,course c,teacher t,classroom cl WHERE r.`grade` = g.id AND r.`course` = c.id AND c.`teacher`=t.`id` AND c.`classroom`=cl.`id` and c.kind=1 GROUP BY r.`course`")
    List<RequiredCourse> queryRequiredCourseExcelData();

    //教师课程冲突
    @Select("SELECT COUNT(*) FROM course WHERE (`from` between #{from} and #{to} or `to` between #{from} and #{to}) AND teacher=#{teacher} and week=#{week} and start=#{start} and end=#{end} and id!=#{id}")
    int queryRequiredCourseTeacherMoreDateNumber(RequiredCourse requiredCourse);
}
