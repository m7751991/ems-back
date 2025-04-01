package com.example.educationalsystembackend.service;

import com.example.educationalsystembackend.pojo.Schedule;
import com.example.educationalsystembackend.pojo.StudentChoice;

import java.util.List;
import java.util.Map;

public interface StudentChoiceService {

    List<StudentChoice> quyeryAllStudentChoice(int num, int size);

    int queryOpenElectiveCourseCount();

    List<String> queryStudentChoiceCourseById(String id);

    void deleteStudentChoice(String student, String course);

    void addStudentChoice(String student, String course);

    List<Schedule> queryScheduleById(String id);

    int queryStudentChoiceCount(String id, int week, int start, int end);

    List<StudentChoice> queryStudentChoiceByCourse(String course, int num, int size);

    int queryChoiceCountByStudent(String student);

    int queryStudentElectiveCourseNumber(String student);
    
    /**
     * 获取学生当前已选课程的总学分
     *
     * @param student 学生ID
     * @return 总学分
     */
    float getStudentCurrentCredits(String student);
    
    /**
     * 添加学生选课申请（待审批）
     *
     * @param student 学生ID
     * @param course 课程ID
     */
    void addStudentChoiceForApproval(String student, String course);
    
    /**
     * 查询待审批的选课申请
     *
     * @param num 页码
     * @param size 每页大小
     * @return 待审批选课申请列表
     */
    List<Map<String, Object>> queryPendingApprovals(int num, int size);
    
    /**
     * 查询待审批选课申请总数
     *
     * @return 待审批总数
     */
    int queryPendingApprovalsCount();
    
    /**
     * 更新选课申请的审批状态
     *
     * @param student 学生ID
     * @param course 课程ID
     * @param approved 是否通过
     * @param reason 拒绝原因（若不通过）
     */
    void updateApprovalStatus(String student, String course, boolean approved, String reason);
}
