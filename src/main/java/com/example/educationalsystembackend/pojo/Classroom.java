/*
 * @Descripttion: 
 * @version: 
 * @Author: zixi
 * @Date: 2025-03-27 00:12:47
 * @LastEditors: zixi
 * @LastEditTime: 2025-03-28 14:39:08
 */
package com.example.educationalsystembackend.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Classroom { // 教室实体类
    private String id; // 教室号
    private String name; // 教室名
    private int number; // 容量
    private int status; // 教室状态 - 空闲/使用中
}
