package com.example.educationalsystembackend.service;

import com.example.educationalsystembackend.pojo.RequiredCourse;
import java.util.List;
import com.example.educationalsystembackend.util.ConflictException;
public interface ConflictChecker {
  void checkConflicts(RequiredCourse course, List<RequiredCourse> existingCourses) throws ConflictException;;
}
