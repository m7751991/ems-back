package com.example.educationalsystembackend.service.impl;

import org.springframework.stereotype.Service;
import com.example.educationalsystembackend.pojo.RequiredCourse;
import com.example.educationalsystembackend.service.ConflictChecker;
import java.util.List;
import com.example.educationalsystembackend.util.ConflictException;

@Service
public class ConflictCheckerImpl implements ConflictChecker {

  /**
   * 检查课程是否与已有课程冲突
   * 
   * @param course          课程
   * @param existingCourses 已存在的课程列表
   * @throws ConflictException 包含具体冲突信息的异常
   */

  @Override
  public void checkConflicts(RequiredCourse course, List<RequiredCourse> existingCourses) throws ConflictException {
    for (RequiredCourse existing : existingCourses) {
      // 1. 判断是否为同一时间段（相同星期+相同节次）
      if (isSameTimeSlot(existing, course)) {
        // 2. 判断日期范围是否有重叠
        if (hasDateOverlap(existing, course)) {
          // 3. 检查教师冲突
          checkTeacherConflict(existing, course);
          // 4. 检查教室冲突
          checkClassroomConflict(existing, course);
        }
      }
    }
  }

  /**
   * 判断两个课程是否在同一时间段（星期几+节次相同）
   */
  public static boolean isSameTimeSlot(RequiredCourse a, RequiredCourse b) {
    return a.getWeek() == b.getWeek()
        && a.getStart() == b.getStart();
  }

  /**
   * 判断两个课程的日期范围是否有重叠
   */
  public static boolean hasDateOverlap(RequiredCourse a, RequiredCourse b) {
    return !a.getFrom().before(b.getFrom())
        && !b.getTo().before(a.getFrom());
  }

  /**
   * 教师冲突检查
   */
  private static void checkTeacherConflict(
      RequiredCourse existing, RequiredCourse newCourse)
      throws ConflictException {
    if (existing.getTeacher().equals(newCourse.getTeacher())) {
      throw new ConflictException(
          String.format("教师 %s 在 %s 第%d节课 存在时间冲突（冲突课程：%s）",
              existing.getTeacher(),
              getWeekDayName(existing.getWeek()),
              existing.getStart(),
              existing.getName()));
    }
  }

  /**
   * 教室冲突检查
   */
  private static void checkClassroomConflict(
      RequiredCourse existing, RequiredCourse newCourse)
      throws ConflictException {
    if (existing.getClassroom().equals(newCourse.getClassroom())) {
      throw new ConflictException(
          String.format("教室 %s 在 %s 第%d节课 已被占用（占用课程：%s）",
              existing.getClassroom(),
              getWeekDayName(existing.getWeek()),
              existing.getStart(),
              existing.getName()));
    }
  }

  /**
   * 星期几名称转换
   */
  private static String getWeekDayName(int dayOfWeek) {
    String[] weekDays = { "周一", "周二", "周三", "周四", "周五", "周六", "周日" };
    return weekDays[dayOfWeek - 1];
  }
}
