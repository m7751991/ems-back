/*
 * @Descripttion: 
 * @version: 
 * @Author: zixi
 * @Date: 2025-03-27 00:12:47
 * @LastEditors: zixi
 * @LastEditTime: 2025-03-28 14:39:38
 */
package com.example.educationalsystembackend.mapper;

import com.example.educationalsystembackend.pojo.Classroom;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ClassroomMapper {

    //分页查询所有教室
    @Select("select * from Classroom limit #{num},#{size}")
    List<Classroom> queryAllClassroom(int num, int size);

    //查询教室总数
    @Select("select count(*) from classroom")
    int queryAllClassroomCount();

    //添加教室
    @Insert("insert into Classroom value (#{id},#{name},#{number},#{status})")
    void addClassroom(Classroom classroom);

    //查询教室
    @Select("select * from Classroom where id=#{id}")
    List<Classroom> queryClassroom(String id);

    //查询教室数量
    @Select("select count(*) from classroom where id=#{id}")
    int queryClassroomCount(String id);

    //删除教室
    @Delete("delete from Classroom where id=#{id}")
    void deleteClassroom(String id);

    //修改教室
    @Update("update Classroom set name=#{name},number=#{number},status=#{status} where id=#{id}")
    void updateClassroom(Classroom classroom);

    //查询容量大于数量的教室
    @Select("select id,name as classroom from classroom where number>=#{nubmer}")
    List<Map<String, Object>> queryClassroomByNumber(int number);

    //通过教室名查询教室号
    @Select("select id from classroom where name=#{name}")
    String queryClassroomIdByName(String name);

    //通过教室名查询容量
    @Select("select number from classroom where name=#{name}")
    int queryClassroomNumberByName(String name);

    @Select("select * from classroom")
    List<Classroom> queryClassroomExcelData();

    //教室冲突
    @Select("SELECT COUNT(*) FROM course WHERE week=#{week} and start=#{start} and end=#{end} and (`from` between #{from} and #{to} or `to` between #{from} and #{to} or (`from`<#{from} and `to`>#{to})) and id!=#{course} and classroom=#{classroom}")
    int queryClassroomMoreDateNumber(String course, Date from, Date to, int week, int start, int end, String classroom);

    void addClassrooms(List<Classroom> classroomList);

    @Delete({
        "<script>",
        "DELETE FROM classroom WHERE id IN",
        "<foreach item='id' collection='ids' open='(' separator=',' close=')'>",
        "'${id}'",
        "</foreach>",
        "</script>"
    })
    void deleteClassrooms(@Param("ids") List<String> ids);
}
