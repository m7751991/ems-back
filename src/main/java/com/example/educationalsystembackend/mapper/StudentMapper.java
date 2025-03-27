package com.example.educationalsystembackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.educationalsystembackend.entity.StudentEntity;
import com.example.educationalsystembackend.pojo.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper extends BaseMapper<StudentEntity> {

    //添加学生
    @Insert("insert into student value (#{id},#{name},#{grade},#{sex})")
    void addStuent(Student student);

    //分页查询所有学生
    @Select("SELECT s.id,s.name,g.`name` AS grade ,s.`sex` FROM student s,grade g WHERE g.id=s.grade order by g.name,s.id*1 limit #{num},#{size}")
    List<Student> queryAllStudent(int num, int size);

    //查询学生总数
    @Select("select count(*) from student")
    int queryAllStudentCount();

    //删除学生
    @Delete("delete from student where id=#{id}")
    void deleteStudent(String id);

    List<Student> queryStudent(String id, String grade, int num, int size);

    //查询学生数量
    @Select("select count(*) from student where id=#{id}")
    int queryStudentCount(String id);

    //查询班级学生数量
    int queryGradeStudentCount(String id, String grade);

    //修改学生
    @Update("update student set name=#{name},grade=#{grade},sex=#{sex} where id=#{id}")
    void updateStudent(Student student);

    @Select("select id from student where name=#{name}")
    String queryStudentIdByName(String name);

    @Select("select name from student where id=#{id}")
    String queryStudentNameById(String id);

    @Select("SELECT id FROM student WHERE grade=#{grade}")
    List<String> queryStudentIdByGrade(String grade);

    @Select("select id,name,sex from student where grade like #{grade}")
    List<Student> queryStudentExcelData(String grade);
}

