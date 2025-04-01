package com.example.educationalsystembackend.mapper;

import com.example.educationalsystembackend.pojo.RetakeCourse;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RetakeCourseMapper {

    // 分页查询所有重修课
    @Select("SELECT c.id,c.name AS name,credit,t.name AS teacher,cl.name AS classroom,c.week,c.`start`,c.`end`,c.number,CEILING(DATEDIFF(`from`,'2023-02-12')/7) as zcFrom,CEILING(DATEDIFF(`to`,'2023-02-12')/7) as zcTo,c.`from`,c.to FROM course c,teacher t,classroom cl WHERE c.`teacher` = t.id AND c.`classroom` = cl.`id` AND c.kind=3 limit #{num},#{size}")
    List<RetakeCourse> queryAllRetakeCourse(int num, int size);

    // 查询重修课总数
    @Select("select count(*) from course where kind=3")
    int queryAllRetakeCourseCount();

    // 分页查询重修课
    @Select("SELECT c.id,c.name AS name,credit,t.name AS teacher,cl.name AS classroom,c.week,c.`start`,c.`end`,c.number,CEILING(DATEDIFF(`from`,'2023-02-12')/7) as zcFrom,CEILING(DATEDIFF(`to`,'2023-02-12')/7) as zcTo,c.`from`,c.to FROM course c,teacher t,classroom cl WHERE c.`teacher` = t.id AND c.`classroom` = cl.`id` AND c.kind=3 and c.name=#{course} limit #{num},#{size}")
    List<RetakeCourse> queryRetakeCourse(String course, int num, int size);

    // 查询重修课数量
    @Select("select count(*) from course where kind=3 and name=#{course}")
    int queryRetakeCourseCount(String course);

    // 添加重修课
    @Insert("insert into course(id,name,teacher,week,start,end,flag,score,classroom,kind,number,`from`,`to`,credit) value (#{id},#{name},#{teacher},#{week},#{start},#{end},#{flag},#{score},#{classroom},#{kind},#{number},#{from},#{to},#{credit})")
    void addRetakeCourse(RetakeCourse retakeCourse);

    // 删除重修课
    @Delete("delete from course where id=#{course}")
    void deleteRetakeCourse(String course);

    // 修改重修课
    @Update("update course set name=#{name},number=#{number},credit=#{credit},teacher=#{teacher},classroom=#{classroom},week=#{week},start=#{start},end=#{end},`from`=#{from},`to`=#{to} where id=#{id}")
    void updateRetakeCourse(RetakeCourse retakeCourse);

    // 查询重修课容量
    @Select("select number from course where id=#{id}")
    int queryNumberById(String id);

    // 修改课程容量
    @Update("update course set number=#{number} where id=#{id}")
    void updateCourseNumber(String id, int number);

    @Select("SELECT " +
            "CASE " +
            "   WHEN teacher_conflict > 0 THEN '教师在该时间已有课程安排；' " +
            "   WHEN classroom_conflict > 0 THEN '教室在该时间已被占用；' " +
            "   ELSE NULL " +
            "END as conflict_message " +
            "FROM (" +
            "   SELECT " +
            "   SUM(CASE WHEN teacher = #{teacher} THEN 1 ELSE 0 END) as teacher_conflict, " +
            "   SUM(CASE WHEN classroom = #{classroom} THEN 1 ELSE 0 END) as classroom_conflict " +
            "   FROM course " +
            "   WHERE id != #{id} " +
            "   AND week = #{week} " +
            "   AND (`from` between #{from} and #{to} or `to` between #{from} and #{to}) " +
            "   AND (#{start} BETWEEN start AND end " +
            "       OR #{end} BETWEEN start AND end " +
            "       OR (#{start} <= start AND #{end} >= end)) " +
            ") t " +
            "WHERE teacher_conflict > 0 OR classroom_conflict > 0")
    String queryRetakeCourseMoreDateNumber(RetakeCourse retakeCourse);
}
