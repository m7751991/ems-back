package com.example.educationalsystembackend.service;

import com.example.educationalsystembackend.pojo.Classroom;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ClassroomService {

    List<Classroom> queryAllClassroom(int num, int size);

    int queryAllClassroomCount();

    void addClassroom(Classroom classroom);

    List<Classroom> queryClassroom(String id);

    int queryClassroomCount(String id);

    void deleteClassroom(String id);

    void updateClassroom(Classroom classroom);

    List<Map<String, Object>> queryClassroomByNumber(int number);

    String queryClassroomIdByName(String name);

    int queryClassroomNumberByName(String name);

    List<Classroom> queryClassroomExcelData();

    int queryClassroomMoreDateNumber(String course, Date from, Date to, int week, int start, int end, String classroom);

    void addClassrooms(List<Classroom> classroomList);
    
    /**
     * 更新教室状态
     * 
     * @param id 教室号
     * @param status 状态（空闲/使用中）
     */
    void updateClassroomStatus(String id, String status);
    
    /**
     * 根据状态查询教室
     * 
     * @param status 状态
     * @return 教室列表
     */
    List<Map<String, Object>> queryClassroomByStatus(String status);

    void deleteClassrooms(String ids);
}
