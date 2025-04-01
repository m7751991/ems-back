/*
 * @Descripttion: 
 * @version: 
 * @Author: zixi
 * @Date: 2025-03-27 00:12:47
 * @LastEditors: zixi
 * @LastEditTime: 2025-03-28 12:00:40
 */
package com.example.educationalsystembackend.pojo;

import com.example.educationalsystembackend.validated.AddStudentGroup;
import com.example.educationalsystembackend.validated.DeleteStudentGroup;
import com.example.educationalsystembackend.validated.UpdateStudentGroup;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Student { // 学生实体类

    @NotBlank(message = "学号不能为空", groups = { DeleteStudentGroup.class, UpdateStudentGroup.class })
    private String id; // 学号

    @NotBlank(message = "姓名不能为空", groups = { AddStudentGroup.class })
    private String name; // 姓名

    @NotBlank(message = "班级不能为空", groups = { AddStudentGroup.class })
    private String grade; // 班级

    @NotBlank(message = "性别不能为空", groups = { AddStudentGroup.class })
    private String sex; // 性别

    private String userName; // 账号

    private String password; // 密码

    private String phone; // 电话

    private String email; // 邮箱

    private String enrollmentDate; // 入学日期

    private List<String> gradeList; // 班级列表

}
