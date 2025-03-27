package com.example.educationalsystembackend.mapper;

import com.example.educationalsystembackend.pojo.AssignExam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AssignExamMapper {

    @Select("SELECT c.id,c.name AS course,c.credit,g.name AS grade,t.name AS teacher,g.number FROM course c,requiredcourse r,teacher t,grade g WHERE r.`course`=c.id AND c.`teacher`=t.id AND r.`grade`=g.id AND r.`classroom` is NULL and now()>=`to` AND r.`supervisor` is NULL LIMIT #{num},#{size}")
    List<AssignExam> queryALLAssignExam(int num, int size);

    @Select("select count(*) from requiredcourse r,course c where r.course=c.id and r.classroom is null and r.supervisor is null and now()>=`to`")
    int queryALLAssignExamCount();

    @Select("SELECT c.id,c.name AS course,c.credit,g.name AS grade,t.name AS teacher,g.number FROM course c,requiredcourse r,teacher t,grade g WHERE r.`course`=c.id and now()>=`to` AND c.`teacher`=t.id AND r.`grade`=g.id AND r.`classroom` is NULL AND r.`supervisor` is NULL and c.name=#{course} LIMIT #{num},#{size}")
    List<AssignExam> queryAssignExam(String course, int num, int size);

    @Select("select count(*) from requiredcourse r, course c  where r.course=c.id and r.classroom is null and supervisor is null and c.name=#{course} and now()>=`to`")
    int queryAssignExamCount(String course);
}
