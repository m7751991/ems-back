package com.example.educationalsystembackend.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordVO {

    @NotBlank(message="帐号不能为空")
    private String account;

    @NotBlank(message="密码不能为空")
    private String password;

    @NotBlank(message="验证码不能为空")
    private String code;
}
