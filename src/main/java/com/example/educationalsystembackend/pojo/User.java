package com.example.educationalsystembackend.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {          //用户

    @NotBlank(message = "账号不能为空")
    private String account;  //账号

    @NotBlank(message = "密码不能为空")
    private String password; //密码
}
