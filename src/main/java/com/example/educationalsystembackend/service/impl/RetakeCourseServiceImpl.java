package com.example.educationalsystembackend.service.impl;

import com.example.educationalsystembackend.mapper.RetakeCourseMapper;
import com.example.educationalsystembackend.pojo.RetakeCourse;
import com.example.educationalsystembackend.service.RetakeCourseService;
import com.example.educationalsystembackend.util.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetakeCourseServiceImpl implements RetakeCourseService {

    @Autowired
    private RetakeCourseMapper retakeCourseMapper;

    @Override
    public List<RetakeCourse> queryAllRetakeCourse(int num, int size) {
        return retakeCourseMapper.queryAllRetakeCourse(num, size);
    }

    @Override
    public int queryAllRetakeCourseCount() {
        return retakeCourseMapper.queryAllRetakeCourseCount();
    }

    @Override
    public List<RetakeCourse> queryRetakeCourse(String course, int num, int size) {
        return retakeCourseMapper.queryRetakeCourse(course, num, size);
    }

    @Override
    public int queryRetakeCourseCount(String course) {
        return retakeCourseMapper.queryRetakeCourseCount(course);
    }

    @Override
    public void addRetakeCourse(RetakeCourse retakeCourse) {
        retakeCourseMapper.addRetakeCourse(retakeCourse);
    }

    @Override
    public void deleteRetakeCourse(String course) {
        retakeCourseMapper.deleteRetakeCourse(course);
    }

    @Override
    public void updateRetakeCourse(RetakeCourse retakeCourse) {
        retakeCourseMapper.updateRetakeCourse(retakeCourse);
    }

    @Override
    public int queryNumberById(String id) {
        return retakeCourseMapper.queryNumberById(id);
    }

    @Override
    public void updateCourseNumber(String id, int number) {
        retakeCourseMapper.updateCourseNumber(id, number);
    }

    @Override
    public void queryRetakeCourseMoreDateNumber(RetakeCourse retakeCourse) throws ConflictException {
        String conflictMessage = retakeCourseMapper.queryRetakeCourseMoreDateNumber(retakeCourse);
        if (conflictMessage != null) {
            throw new ConflictException(conflictMessage);
        }
    }

}
