package com.example.educationalsystembackend.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Classroom {   //教室实体类
    private String id;     //教室号
    private String name;   //教室名
    private int number;    //容量
}
