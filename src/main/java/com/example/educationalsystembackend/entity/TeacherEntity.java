package com.example.educationalsystembackend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("teacher")
public class TeacherEntity {

    @TableId("id")
    private String id;

    @TableField("name")
    private String name;

    @TableField("sex")
    private String sex;
}
