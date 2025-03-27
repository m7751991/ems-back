package com.example.educationalsystembackend.mapper;

import com.example.educationalsystembackend.pojo.StudentCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentCourseMapper {

    @Select("SELECT c.name AS course,t.name AS teacher,c.kind,cl.name AS classroom,week,CEILING(DATEDIFF(`from`,'2023-02-12') / 7) AS `from`,CEILING(DATEDIFF(`to`, '2023-02-12') / 7) AS `to`,c.start,c.end,c.credit FROM course c,choice ch,student s,classroom cl,teacher t WHERE c.id = ch.`course` AND ch.`student` = s.id AND c.`classroom` = cl.`id` AND c.`teacher` = t.id AND s.id = #{student} order by `from`,`to` limit #{num},#{size}")
    List<StudentCourse> queryAllStudentCourse(String student, int num, int size);

    @Select("select count(*) from choice where student=#{student}")
    int queryAllStudentCourseCount(String student);

    @Select("SELECT c.name AS course,t.name AS teacher,c.kind,cl.name AS classroom,week,CEILING(DATEDIFF(`from`,'2023-02-12') / 7) AS `from`,CEILING(DATEDIFF(`to`, '2023-02-12') / 7) AS `to`,c.start,c.end,c.credit FROM course c,choice ch,student s,classroom cl,teacher t WHERE c.id = ch.`course` AND ch.`student` = s.id AND c.`classroom` = cl.`id` AND c.`teacher` = t.id AND c.name=#{course} and s.id = #{student} order by `from`,`to` limit #{num},#{size}")
    List<StudentCourse> queryStudentCourse(String student, String course, int num, int size);

    @Select("select count(*) from choice ch,course c where ch.course=c.id and ch.student=#{student} and c.name=#{course}")
    int queryStudentCourseCount(String student, String course);
}
