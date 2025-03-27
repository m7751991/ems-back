package com.example.educationalsystembackend.service.impl;

import com.example.educationalsystembackend.mapper.AdjustCourseMapper;
import com.example.educationalsystembackend.pojo.AdjustCourse;
import com.example.educationalsystembackend.service.AdjustCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdjustCourseServiceImpl implements AdjustCourseService {

    @Autowired
    private AdjustCourseMapper adjustCourseMapper;

    @Override
    public List<AdjustCourse> queryAllAdjustCourse(int num, int size) {
        return adjustCourseMapper.queryAllAdjustCourse(num, size);
    }

    @Override
    public int queryAllAdjustCourseCount() {
        return adjustCourseMapper.queryAllAdjustCourseCount();
    }

    @Override
    public List<AdjustCourse> queryAdjustCourse(int num, int size, String teacher, String result) {
        return adjustCourseMapper.queryAdjustCourse(num, size, teacher, result);
    }

    @Override
    public int queryAdjustCourseCount(String teacher, String result) {
        return adjustCourseMapper.queryAdjustCourseCount(teacher, result);
    }

    @Override
    public void agreeAdjustCourse(Long id) {
        adjustCourseMapper.agreeAdjustCourse(id);
    }

    @Override
    public void refuseAdjustCourse(Long id) {
        adjustCourseMapper.refuseAdjustCourse(id);
    }
}
