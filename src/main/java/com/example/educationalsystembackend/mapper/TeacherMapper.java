/*
 * @Descripttion: 
 * @version: 
 * @Author: zixi
 * @Date: 2025-03-27 00:12:47
 * @LastEditors: zixi
 * @LastEditTime: 2025-03-29 01:10:29
 */
package com.example.educationalsystembackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.educationalsystembackend.entity.TeacherEntity;
import com.example.educationalsystembackend.pojo.Teacher;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TeacherMapper extends BaseMapper<TeacherEntity> {

    // 添加老师
    @Insert("insert into teacher value (#{id},#{name},#{sex},#{phone},#{email},#{hireDate})")
    void addTeacher(Teacher teacher);

    // 分页查询所有老师
    @Select("select * from teacher order by id limit #{num},#{size}")
    List<Teacher> queryAllTeacher(int num, int size);

    // 查询老师总数
    @Select("select count(*) from teacher")
    int queryAllTeacherCount();

    // 删除老师
    @Delete("delete from teacher where id=#{id}")
    void deleteTeacher(String id);

    // 查询老师
    @Select("select * from teacher where id=#{id}")
    List<Teacher> queryTeacher(String id);

    // 查询老师数量
    @Select("select count(*) from teacher where id=#{id}")
    int queryTeacherCount(String id);

    // 修改老师
    @Update("update teacher set name=#{name},sex=#{sex},phone=#{phone},email=#{email},hireDate=#{hireDate} where id=#{id}")
    void updateTeacher(Teacher teacher);

    @Select("select t.id,t.name as teacher from teacher t,course c where c.teacher=t.id and c.name like #{course}")
    List<Map<String, Object>> queryAllTeacherNameByCourse(String course);

    // 查询所有老师工号和姓名
    @Select("SELECT id,`name` AS teacher FROM teacher")
    List<Map<String, Object>> queryAllTeacherIdAndName();

    @Select("select name from teacher where id=#{id}")
    String queryTeacherNameById(String id);

    // 通过老师姓名查询老师工号
    @Select("select id from teacher where name=#{name}")
    String queryTeacherIdByName(String name);

    @Select("SELECT t.id,t.name AS teacher FROM teacher t,course c,choice ch WHERE ch.`course`=c.id AND c.`teacher`=t.id AND ch.`student`=#{student} GROUP BY t.id,t.name;")
    List<Map<String, Object>> queryStudentChoiceTeacher(String student);

    @Select("SELECT t.id,t.name AS teacher FROM choice ch,course c,teacher t WHERE c.id=ch.course and c.teacher=t.id AND c.id like #{course} and ch.student=#{student} group by t.id,t.name")
    List<Map<String, Object>> queryStudentChoiceTeacherByCourse(String student, String course);

    @Select("select * from teacher")
    List<Teacher> queryTeacherExcelData();

    @Delete({
            "<script>",
            "DELETE FROM teacher WHERE id IN",
            "<foreach item='id' collection='ids' open='(' separator=',' close=')'>",
            "'${id}'",
            "</foreach>",
            "</script>"
    })
    void deleteTeachers(@Param("ids") List<String> ids);
}
