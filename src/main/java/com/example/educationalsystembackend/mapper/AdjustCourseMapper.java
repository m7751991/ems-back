package com.example.educationalsystembackend.mapper;

import com.example.educationalsystembackend.pojo.AdjustCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AdjustCourseMapper {

    @Select("SELECT a.id,c.id AS course,c.name,t.name AS teacher,a.`from`,a.to,a.`week`,a.`lesson`,a.reason,a.result FROM adjust a,course c,teacher t WHERE a.course = c.`id` AND c.`teacher` = t.id limit #{num},#{size}")
    List<AdjustCourse> queryAllAdjustCourse(int num, int size);

    @Select("select count(*) from adjust")
    int queryAllAdjustCourseCount();

    @Select("SELECT a.id,c.id AS course,c.name,t.name AS teacher,a.`from`,a.to,a.`week`,a.`lesson`,a.reason,a.result FROM adjust a,course c,teacher t WHERE a.course = c.`id` AND c.`teacher` = t.id and c.teacher like #{teacher} and result like #{result} limit #{num},#{size}")
    List<AdjustCourse> queryAdjustCourse(int num, int size, String teacher, String result);

    @Select("select count(*) from adjust a,course c where c.teacher like #{teacher} and a.course=c.id and result like #{result}")
    int queryAdjustCourseCount(String teacher, String result);

    @Select("update adjust set result=1 where id=#{id}")
    void agreeAdjustCourse(Long id);

    @Select("update adjust set result=2 where id=#{id}")
    void refuseAdjustCourse(Long id);
}