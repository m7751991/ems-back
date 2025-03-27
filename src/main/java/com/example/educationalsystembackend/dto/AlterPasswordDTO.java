package com.example.educationalsystembackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlterPasswordDTO {

    private String account;

    private String oldPassword;

    private String newPassword;
}
