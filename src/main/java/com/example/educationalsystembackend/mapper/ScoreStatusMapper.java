package com.example.educationalsystembackend.mapper;

import com.example.educationalsystembackend.pojo.ScoreStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ScoreStatusMapper {

    @Select("SELECT score,c.id,c.name AS course,c.kind,c.`credit`,t.name AS teacher,c.usual,c.`experiment`,c.`exam` FROM course c,teacher t WHERE t.`id`=c.`teacher` limit #{num},#{size}")
    List<ScoreStatus> queryAllScoreStatus(int num, int size);

    @Select("select count(*) from course")
    int queryAllScoreStatusCount();

    List<ScoreStatus> queryScoreStatus(String teacher, List<Integer> scoreList, int num, int size);

    int queryScoreStatusCount(String teacher, List<Integer> scoreList);

    @Select("SELECT c.id,c.name AS course,c.kind,c.`credit`,t.name AS teacher FROM course c,teacher t WHERE t.`id`=c.`teacher` and c.score=0")
    List<ScoreStatus> queryScoreStatusExcelData();
}
