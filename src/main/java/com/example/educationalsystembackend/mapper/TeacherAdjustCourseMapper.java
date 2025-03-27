package com.example.educationalsystembackend.mapper;

import com.example.educationalsystembackend.pojo.AdjustCourse;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface TeacherAdjustCourseMapper {

    @Select("SELECT a.id,c.id AS course,c.name,a.`from`,a.to,a.`week`,a.`lesson`,a.reason,a.result FROM adjust a,course c WHERE a.course = c.`id` and c.teacher=#{teacher} limit #{num},#{size}")
    List<AdjustCourse> queryAllTeacherAdjustCourse(String teacher, int num, int size);

    @Select("select count(*) from adjust a,course c where a.course=c.id and teacher=#{teacher}")
    int queryAllTeacherAdjustCourseCount(String teacher);

    @Select("SELECT DISTINCT `name` as course FROM course c,adjust a WHERE c.id=a.course and teacher=#{teacher}")
    List<Map<String, Object>> queryTeacherAdjustCourseList(String teacher);

    List<AdjustCourse> queryTeacherAdjustCourse(String teacher, String course, List<Integer> resultList, int num, int size);

    int queryTeacherAdjustCourseCount(String teacher, String course, List<Integer> resultList);

    @Select("select * from adjust where id=#{id}")
    AdjustCourse queryTeacherAdjustCourseForm(Long id);

    @Update("update adjust set `from`=#{from},`to`=#{to},week=#{week},lesson=#{lesson},reason=#{reason},result=0 where id=#{id}")
    void updateAdjustCourse(AdjustCourse adjustCourse);

    @Select("select CEILING(DATEDIFF(c.`from`,'2023-02-12')/7) as `from` from course c,adjust a where c.id=a.course and a.id=#{id}")
    int queryAdjustCourseFrom(Long id);

    @Select("select CEILING(DATEDIFF(c.`to`,'2023-02-12')/7) as `to` from course c,adjust a where c.id=a.course and a.id=#{id}")
    int queryAdjustCourseTo(Long id);

    @Delete("delete from adjust where id=#{id}")
    void deleteTeacherAdjustCourse(Long id);
}
