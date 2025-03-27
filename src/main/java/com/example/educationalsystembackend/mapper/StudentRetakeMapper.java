package com.example.educationalsystembackend.mapper;

import com.example.educationalsystembackend.pojo.StudentRetake;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentRetakeMapper {

    @Select("SELECT c.name FROM course c,choice ch WHERE c.id=ch.course AND ch.total<60 AND c.`score`=TRUE AND ch.student=#{student}")
    String[] queryRetakeCourseNameList(String student);

    @Select("SELECT c.credit FROM course c,choice ch WHERE c.id=ch.course AND ch.total<60 AND c.`score`=TRUE AND ch.student=#{student}")
    Float[] queryRetakeCourseCreditList(String student);

    @Select("SELECT c.credit FROM course c,choice ch WHERE c.id=ch.course AND ch.student=#{student} and c.name=#{course}")
    Float queryRetakeCourseCredit(String student, String course);

    @Select("SELECT c.id,c.name AS course,credit,GROUP_CONCAT(g.name) AS grade,c.`number`,CEILING(DATEDIFF(`from`,'2023-02-12') / 7) AS `from`,CEILING(DATEDIFF(`to`, '2023-02-12') / 7) AS `to`,c.week,c.start,c.end,t.name AS teacher,cl.name AS classroom,kind,true as disabled FROM  requiredcourse r,grade g,course c,teacher t,classroom cl WHERE r.`grade` = g.id AND r.`course` = c.id AND c.`teacher` = t.`id` AND c.`classroom` = cl.`id` AND c.kind = 1 AND c.`name`=#{course} and credit=#{credit}  AND c.id NOT IN (SELECT course FROM choice WHERE total < 60 AND student=#{student})GROUP BY r.`course`")
    List<StudentRetake> queryAllStudentRetakeCourseKind1(String student, String course, float credit);

    @Select("SELECT c.id,c.name AS course,'组班' AS grade,credit,c.number,WEEK,START,END,kind,true as disabled,t.name AS teacher,cl.name AS classroom,CEILING(DATEDIFF(`from`,'2023-02-12') / 7) AS `from`,CEILING(DATEDIFF(`to`, '2023-02-12') / 7) AS `to` FROM teacher t,classroom cl,course c WHERE kind=3 and  c.`teacher` = t.id AND c.`classroom` = cl.`id` AND c.`name`=#{course} and credit=#{credit} ")
    List<StudentRetake> queryAllStudentRetakeCourseKind3(String student, String course, float credit);

    @Select("SELECT c.id,c.name AS course,GROUP_CONCAT(g.name) AS grade,credit,c.`number`,c.week,true as disabled,c.start,c.end,t.name AS teacher,cl.name AS classroom,kind,CEILING(DATEDIFF(`from`,'2023-02-12') / 7) AS `from`,CEILING(DATEDIFF(`to`, '2023-02-12') / 7) AS `to` FROM  requiredcourse r,grade g,course c,teacher t,classroom cl WHERE r.`grade` = g.id AND r.`course` = c.id AND c.`teacher` = t.`id` AND c.`classroom` = cl.`id` AND c.credit=#{credit} and c.name=#{course} AND c.id NOT IN (SELECT course FROM choice WHERE total < 60 AND student=#{student}) GROUP BY r.`course`")
    List<StudentRetake> queryStudentRetakeCourseKind1(String student, String course, float credit);

    @Select("SELECT c.id,c.name AS course,'组班' AS grade,c.number,credit,WEEK,START,END,kind,CEILING(DATEDIFF(`from`,'2023-02-12') / 7) AS `from`,CEILING(DATEDIFF(`to`, '2023-02-12') / 7) AS `to`,t.name AS teacher,true as disabled,cl.name AS classroom FROM teacher t,classroom cl,course c WHERE kind=3 and  c.`teacher` = t.id AND c.`classroom` = cl.`id` AND c.name=#{course} and credit=#{credit}")
    List<StudentRetake> queryStudentRetakeCourseKind3(String student, String course, float credit);
}
