package com.example.educationalsystembackend.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Teacher { // 教师实体类
    private String id; // 工号
    private String name; // 姓名
    private String sex; // 性别
    private String phone; // 电话
    private String email; // 邮箱
    private String hireDate; // 入职日期
    private String userName; // 用户名
    private String password; // 密码
}
