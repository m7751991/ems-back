package com.example.educationalsystembackend.service.impl;

import com.example.educationalsystembackend.mapper.AssignExamMapper;
import com.example.educationalsystembackend.pojo.AssignExam;
import com.example.educationalsystembackend.service.AssignExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignExamServiceImpl implements AssignExamService {

    @Autowired
    private AssignExamMapper assignExamMapper;

    @Override
    public List<AssignExam> queryALLAssignExam(int num, int size) {
        return assignExamMapper.queryALLAssignExam(num, size);
    }

    @Override
    public int queryALLAssignExamCount() {
        return assignExamMapper.queryALLAssignExamCount();
    }

    @Override
    public List<AssignExam> queryAssignExam(String course, int num, int size) {
        return assignExamMapper.queryAssignExam(course, num, size);
    }

    @Override
    public int queryAssignExamCount(String course) {
        return assignExamMapper.queryAssignExamCount(course);
    }
}
