package com.example.educationalsystembackend.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlterPasswordVO {

    @NotBlank(message ="新密码不能为空")
    private String newPassword;

    @NotBlank(message ="旧密码不能为空")
    private String oldPassword;
}
