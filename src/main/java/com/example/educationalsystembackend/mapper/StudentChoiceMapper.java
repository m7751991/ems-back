package com.example.educationalsystembackend.mapper;

import com.example.educationalsystembackend.pojo.Schedule;
import com.example.educationalsystembackend.pojo.StudentChoice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentChoiceMapper {

    //学生分页查询选修课
    @Select("SELECT c.id,c.name,t.name AS teacher,credit,true as disabled,c.week,c.start,c.end ,cl.name as classroom, 0 AS flag,c.number,CEILING(DATEDIFF(`from`,'2023-02-12')/7) as `from`,CEILING(DATEDIFF(`to`,'2023-02-12')/7) as `to` FROM teacher t,course c,classroom cl WHERE c.teacher=t.id AND c.`classroom`=cl.`id` AND c.`kind`=2 and c.flag=true ORDER BY c.id limit #{num},#{size}")
    List<StudentChoice> quyeryAllStudentChoice(int num, int size);

    //查询公开的选修课数量
    @Select("select count(*) from course where kind=2 and flag=true")
    int queryOpenElectiveCourseCount();

    //学生已选课的课程号
    @Select("select course from choice where student=#{id}")
    List<String> queryStudentChoiceCourseById(String id);

    //删除选课
    @Delete("delete from choice where student=#{student} and course=#{course}")
    void deleteStudentChoice(String student, String course);

    //添加选课
    @Insert("insert into choice(student, course) value (#{student},#{course})")
    void addStudentChoice(String student, String course);

    //学生查询课表
    @Select("SELECT c.name AS course,t.name AS teacher,cl.`name` AS classroom,WEEK,START,END,kind,CEILING(DATEDIFF(`from`,'2023-02-12')/7) as `from`,CEILING(DATEDIFF(`to`,'2023-02-12')/7) as `to` FROM course c,choice ch,teacher t,classroom cl WHERE c.id=ch.course AND c.`classroom`=cl.`id` AND  c.teacher=t.id AND ch.student=#{id}")
    List<Schedule> queryScheduleById(String id);

    //学生当前时刻课程数量
    @Select("select count(*) from course c,choice ch where ch.course=c.id and ch.student=#{id} and week=#{week} and start=#{start} and end=#{end}")
    int queryStudentChoiceCount(String id, int week, int start, int end);

    //学生查询选修课
    @Select("select c.id,c.name,t.name as teacher,credit,true as disabled,c.week,c.start,c.end,cl.name as classroom, 0 as flag,c.number,CEILING(DATEDIFF(`from`,'2023-02-12')/7) as `from`,CEILING(DATEDIFF(`to`,'2023-02-12')/7) as `to` from teacher t,course c,classroom cl where c.teacher=t.id and c.name=#{course} and c.classroom=cl.id and c.kind=2 order by c.id limit #{num},#{size}")
    List<StudentChoice> queryStudentChoiceByCourse(String course, int num, int size);

    //学生查询选修课数量
    @Select("select count(*) from choice where student=#{student}")
    int queryChoiceCountByStudent(String student);

    //学生查询选修课数量
    @Select("select count(*) from choice ch,course c where ch.course=c.id and ch.student=#{student} and c.kind=2")
    int queryStudentElectiveCourseNumber(String student);
}
