package com.example.educationalsystembackend.mapper;

import com.example.educationalsystembackend.pojo.Grade;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface GradeMapper {

    //分页查询所有班级
    @Select("select * from grade order by id*1 limit #{num},#{size} ")
    List<Grade> queryAllGrade(int num, int size);

    //查询班级总数
    @Select("select count(*) from grade")
    int queryAllGradeCount();

    //增加班级
    @Insert("insert into grade(id,name) value (#{id},#{name})")
    void addGrade(Grade grade);

    //查询班级
    @Select("select * from grade where id=#{id}")
    List<Grade> queryGrade(String id);

    //查询班级数量
    @Select("select count(*) from grade where id=#{id}")
    int queryGradeCount(String id);

    //删除班级
    @Delete("delete from grade where id=#{id}")
    void deleteGrade(String id);

    //修改班级
    @Update("update grade set name=#{name} where id=#{id}")
    void updateGrade(Grade grade);

    //查询所有班级的班级号和班级名
    @Select("select id,name as grade from grade order by id*1")
    List<Map<String, Object>> queryAllGradeIdAndName();

    //查询班级人数
    @Select("select number from grade where id=#{grade}")
    int queryNumberByGrade(String grade);

    //修改班级人数
    @Update("update grade set number=#{number} where id=#{grade}")
    void alterNumberByGrade(String grade, int number);

    //查询班级号
    @Select("select id from grade where name=#{name}")
    String queryGradeIdByName(String name);

    //查询班级名
    @Select("select name from grade where id=#{id}")
    String queryGradeNameById(String id);

    //查询学生所在班级
    @Select("select grade from student where id=#{student}")
    String queryGradeByStudent(String student);

    //查询所有考试班级
    @Select("SELECT g.id,g.name AS grade FROM requiredcourse r,grade g where r.`grade`=g.id AND r.`classroom` IS NOT NULL AND r.`supervisor` IS NOT NULL GROUP BY g.name")
    List<Map<String, Object>> queryAllExamGrade();

    //查询监考班级
    @Select("SELECT g.id,g.`name` AS grade FROM requiredcourse r,grade g WHERE r.`grade`=g.`id` and r.supervisor=#{supervisor} GROUP BY g.name ")
    List<Map<String, Object>> querySuperviseExamGrade(String supervisor);

    //教师查看选择选课班级
    @Select("SELECT g.id,g.name AS grade FROM choice ch,course c,student s,grade g WHERE c.id=ch.course AND ch.`student`=s.`id` AND c.`teacher`=#{teacher} AND s.`grade`=g.id GROUP BY g.name")
    List<Map<String, Object>> queryChoiceGradeByTeacher(String teacher);

    //教师通过课程查询班级
    @Select("SELECT g.id,g.name AS grade FROM choice ch,course c,student s,grade g WHERE c.id=ch.course AND ch.`student`=s.`id` AND c.`name` like #{course} and c.`teacher`=#{teacher} AND s.`grade`=g.id GROUP BY g.name")
    List<Map<String, Object>> queryTeacherChoiceGradeByCourse(String course, String teacher);

    @Select("select grade from requiredcourse where course=#{course}")
    List<String> queryCoureGrade(String course);

    //查询班级表格数据
    @Select("select * from grade order by id*1")
    List<Grade> queryGradeExcelData();

    //查询上必修课的班级
    @Select("SELECT GROUP_CONCAT(g.name) AS grade FROM requiredcourse r,grade g WHERE g.id=r.grade and r.`course`=#{course} GROUP BY r.`course`")
    String queryRequiredCourseGrade(String course);
}
