package com.example.educationalsystembackend.mapper;

import com.example.educationalsystembackend.pojo.ElectiveCourse;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ElectiveCourseMapper {

    // 分页查询选修课
    @Select("select c.id,c.name,t.name as teacher,credit,c.week,c.start,c.end,c.flag,c.number,cl.name as classroom,CEILING(DATEDIFF(`from`,'2023-02-12')/7) as zcFrom,CEILING(DATEDIFF(`to`,'2023-02-12')/7) as zcTo,c.`from`,c.to from teacher t,course c,classroom cl where c.classroom=cl.id and c.teacher=t.id and c.kind=2 order by c.id limit #{num},#{size}")
    List<ElectiveCourse> queryAllElectiveCourse(int num, int size);

    // 查询选修课总数
    @Select("select count(*) from course where kind=2")
    int queryAllElectiveCourseCount();

    // 删除选修课
    @Delete("delete from course where id=#{id}")
    void deleteElectiveCourse(String id);

    // 搜索选修课
    @Select("select c.id,c.name,t.name as teacher,credit,cl.name as classroom,c.week,c.start,c.end,c.flag,c.number,CEILING(DATEDIFF(`from`,'2023-02-12')/7) as zcFrom,CEILING(DATEDIFF(`to`,'2023-02-12')/7) as zcTo,c.`from`,c.to from teacher t,course c,classroom cl where c.classroom=cl.id and c.teacher=t.id and c.kind=2 and c.id=#{id}")
    List<ElectiveCourse> queryElectiveCourse(String id);

    // 查询选修课数量
    @Select("select count(*) from course where kind=2 and id=#{id}")
    int queryElectiveCourseCount(String id);

    // 添加选修课
    @Insert("insert into course(id,name,teacher,week,start,end,flag,score,classroom,kind,number,`from`,`to`,credit) value (#{id},#{name},#{teacher},#{week},#{start},#{end},#{flag},#{score},#{classroom},#{kind},#{number},#{from},#{to},#{credit})")
    void addElectiveCourse(ElectiveCourse electiveCourse);

    // 查询该选修课的周次
    @Select("select week from course where id=#{id}")
    int queryElectiveCourseWeekById(String id);

    // 查询该选修课的上课节次
    @Select("select start from course where id=#{id}")
    int queryElectiveCourseStartById(String id);

    // 查询该选修课的下课节次
    @Select("select end from course where id=#{id}")
    int queryElectiveCourseEndById(String id);

    // 查询该选修课的容量
    @Select("select number from course where id=#{id}")
    int queryElectiveCourseNumberById(String id);

    // 修改选修课
    @Update("update course set name=#{name},credit=#{credit},number=#{number},teacher=#{teacher},week=#{week},start=#{start},end=#{end},`from`=#{from},`to`=#{to},classroom=#{classroom} where id=#{id}")
    void updateElectiveCourse(ElectiveCourse electiveCourse);

    // 通过课程名和任课老师查看课程号
    @Select("select c.id from course c,teacher t WHERE c.teacher=t.id and c.name=#{name} and t.name=#{teacher}")
    String queryCourseIdByNameAndTeacher(String name, String teacher);

    @Select("select distinct 0 as id,c.name as course from course c,teacher t where c.teacher=t.id and t.id like #{teacher} ")
    List<Map<String, Object>> queryAllCourseNameByTeacher(String teacher);

    // 查询是该课程的数量
    @Select("select count(*) from  course where name=#{name}")
    int queryCourseCountByName(String name);

    // @Select("select c.id,c.name as course from choice ch,course c where
    // ch.course=c.id and ch.student=#{student} group by c.name")
    @Select("SELECT DISTINCT c.id, c.name as course FROM choice ch, course c WHERE ch.course=c.id AND ch.student=#{student}")
    List<Map<String, Object>> queryCourseByStudent(String student);

    @Select("select name from course where id=#{id}")
    String queryCourseNameById(String id);

    @Update("update course set flag=#{flag} where course.id=#{course}")
    void changeElectiveCourseStatus(String course, boolean flag);

    // 教师教的课程
    @Select("SELECT c.id,c.name AS course FROM teacher t,course c,choice ch WHERE ch.`course`=c.id AND c.`teacher`=t.id AND t.id=#{teacher} GROUP BY c.id,c.name")
    List<Map<String, Object>> queryCourseByTeacher(String teacher);

    // 教师通过班级查询课程
    @Select("SELECT c.id,c.name FROM teacher t,course c,choice ch,student s,grade g WHERE ch.`student`=s.`id` AND g.`id`=s.`grade` AND ch.`course`=c.id AND c.`teacher`=t.id AND t.id=#{teacher} AND g.`name` like #{grade} GROUP BY c.id,c.name")
    List<Map<String, Object>> queryCourseByTeacherAndGrade(String teacher, String grade);

    // 教师查询选课
    @Select("SELECT id,c.name AS course FROM course c,choice ch WHERE c.id=ch.`course` AND c.teacher like #{teacher} AND ch.student=#{student}")
    List<Map<String, Object>> queryChoiceCourseByTeacher(String student, String teacher);

    @Select("select c.id,c.name,t.name as teacher,credit,c.week,c.start,c.end,c.number,cl.name as classroom,CEILING(DATEDIFF(`from`,'2023-02-12')/7) as zcFrom,CEILING(DATEDIFF(`to`,'2023-02-12')/7) as zcTo,c.`from`,c.`to` from teacher t,course c,classroom cl where cl.id=c.classroom and c.teacher=t.id and c.kind=2 order by c.id")
    List<ElectiveCourse> queryElectiveCourseExcelData();

    @Select("SELECT COUNT(*) FROM course WHERE `to` >= #{from} AND teacher=#{teacher} and week=#{week} and start=#{start} and end=#{end} and id!=#{id}")
    int queryElectiveCourseMoreDateNumber(ElectiveCourse electiveCourse);

    @Select("update course set usual=#{usual},experiment=#{experiment},exam=#{exam} where id=#{course}")
    void updateScoreProportion(int usual, int experiment, int exam, String course);

    @Select("select usual from course where id=#{course}")
    int queryUsual(String course);

    @Select("select exam from course where id=#{course}")
    int queryExam(String course);

    @Select("select experiment from course where id=#{course}")
    int queryExperiment(String course);

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
    String queryElectiveCourseTeacherMoreDateNumber(ElectiveCourse electiveCourse);
}
