package com.example.educationalsystembackend.service.impl;

import com.example.educationalsystembackend.mapper.ChoiceMapper;
import com.example.educationalsystembackend.pojo.Choice;
import com.example.educationalsystembackend.service.ChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChoiceServiceImpl implements ChoiceService {

    @Autowired
    private ChoiceMapper choiceMapper;

    @Override
    public List<Choice> queryAllChoice(int num, int size) {
        return choiceMapper.queryAllChoice(num, size);
    }

    @Override
    public int queryAllChoiceCount() {
        return choiceMapper.queryAllChoiceCount();
    }

    @Override
    public List<Choice> queryChoice(String name, int num, int size) {
        return choiceMapper.queryChoice(name, num, size);
    }

    @Override
    public int queryChoiceCount(String name) {
        return choiceMapper.queryChoiceCount(name);
    }

    @Override
    public void deleteChoice(Choice choice) {
        choiceMapper.deleteChoice(choice);
    }

    @Override
    public int queryChoiceNumber(String course) {
        return choiceMapper.queryChoiceNumber(course);
    }

    @Override
    public List<Choice> queryChoiceExcelData() {
        return choiceMapper.queryChoiceExcelData();
    }
}
