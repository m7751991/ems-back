package com.example.educationalsystembackend.mapper;

import com.example.educationalsystembackend.pojo.AdjustCourse;
import com.example.educationalsystembackend.pojo.TeacherCourse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TeacherCourseMapper {

    //教师分页查询课程
    @Select("SELECT c.id,c.NAME AS course,cl.`name` AS classroom ,WEEK,START,END,kind,credit,CEILING(DATEDIFF(`from`,'2023-02-12')/7) as `from`,CEILING(DATEDIFF(`to`,'2023-02-12')/7) as `to` FROM course c,classroom cl WHERE c.`classroom`=cl.`id` AND c.teacher=#{teacher} order by `from`,`to` limit #{num},#{size}")
    List<TeacherCourse> queryAllTeacherCourse(String teacher, int num, int size);

    //教师查询课程总数
    @Select("SELECT count(*) FROM course WHERE teacher=#{teacher} ")
    int queryAllTeacherCourseCount(String teacher);

    //教师查询课程
    @Select("SELECT c.id,c.NAME AS course,cl.`name` AS classroom ,WEEK,START,END,kind,credit,CEILING(DATEDIFF(`from`,'2023-02-12')/7) as `from`,CEILING(DATEDIFF(`to`,'2023-02-12')/7) as `to` FROM course c,classroom cl WHERE c.`classroom`=cl.`id` AND c.teacher=#{teacher} and c.id=#{id} order by `from`,`to` limit #{num},#{size}")
    List<TeacherCourse> queryTeacherCourse(String teacher, String id, int num, int size);

    //教师查询课程数量
    @Select("SELECT count(*) FROM course WHERE teacher=#{teacher} and id=#{id}")
    int queryTeacherCourseCount(String teacher, String id);

    //查询教师课程号和课程名
    @Select("SELECT distinct NAME AS course FROM course WHERE teacher=#{teacher}")
    List<Map<String, Object>> queryTeacherCourseIdAndName(String teacher);

    @Select("select id from course where teacher=#{teacher}")
    List<Map<String, Object>> queryTeacherCourseId(String teacher);

    @Select("SELECT g.name AS grade,s.id,s.name AS student,s.sex FROM choice ch,student s,grade g WHERE ch.`student` = s.id AND s.`grade` = g.id AND ch.`course`=#{id}")
    List<Map<String, Object>> queryTeacherCourseExcelData(String id);

    @Insert("insert into adjust( course, `from`, `to`, week, lesson,reason) values (#{course},#{from},#{to},#{week},#{lesson},#{reason})")
    void addAdjustCourse(AdjustCourse adjustCourse);
}
