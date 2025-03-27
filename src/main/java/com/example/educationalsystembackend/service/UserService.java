package com.example.educationalsystembackend.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.educationalsystembackend.dto.AlterPasswordDTO;
import com.example.educationalsystembackend.dto.ResetPasswordDTO;
import com.example.educationalsystembackend.entity.UserEntity;
import com.example.educationalsystembackend.pojo.User;

public interface UserService extends IService<UserEntity> {

    boolean alterPassword(AlterPasswordDTO alterPasswordDTO);

    String queryPassword(String account);

    int queryAccess(String account);

    void addUser(User user, int access);

    void deleteUser(String account);

    void sendCode(String account,String email);

    boolean resetPassword(ResetPasswordDTO resetPasswordDTO);
}
