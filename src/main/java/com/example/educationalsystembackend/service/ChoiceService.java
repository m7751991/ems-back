package com.example.educationalsystembackend.service;


import com.example.educationalsystembackend.pojo.Choice;

import java.util.List;

public interface ChoiceService {

    List<Choice> queryAllChoice(int num, int size);

    int queryAllChoiceCount();

    List<Choice> queryChoice(String student, int num, int size);

    int queryChoiceCount(String student);

    void deleteChoice(Choice choice);

    int queryChoiceNumber(String course);

    List<Choice> queryChoiceExcelData();
}
