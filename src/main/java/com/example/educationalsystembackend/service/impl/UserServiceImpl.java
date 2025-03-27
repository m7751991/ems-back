package com.example.educationalsystembackend.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.educationalsystembackend.dto.AlterPasswordDTO;
import com.example.educationalsystembackend.dto.ResetPasswordDTO;
import com.example.educationalsystembackend.entity.UserEntity;
import com.example.educationalsystembackend.mapper.UserMapper;
import com.example.educationalsystembackend.pojo.User;
import com.example.educationalsystembackend.result.Result;
import com.example.educationalsystembackend.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private JavaMailSender sender; // 引入Spring Mail依赖后，会自动装配到IOC容器。用来发送邮件

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean alterPassword(AlterPasswordDTO alterPasswordDTO) {
        String password= new LambdaQueryChainWrapper<>(userMapper).select(UserEntity::getPassword).eq(UserEntity::getAccount,alterPasswordDTO.getAccount()).one().getPassword();
        UserEntity user=new UserEntity();
        BeanUtils.copyProperties(alterPasswordDTO,user);
        user.setPassword(alterPasswordDTO.getNewPassword());
        if(StringUtils.equals(password,alterPasswordDTO.getOldPassword())) {
            new LambdaUpdateChainWrapper<>(userMapper).eq(UserEntity::getAccount, alterPasswordDTO.getAccount()).update(user);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String queryPassword(String account) {
        return userMapper.queryPassword(account);
    }

    @Override
    public int queryAccess(String account) {
        return userMapper.queryAccess(account);
    }

    @Override
    public void addUser(User user, int access) {
        userMapper.addUser(user, access);
    }

    @Override
    public void deleteUser(String account) {
        userMapper.deleteUser(account);
    }

    @Override
    @Async
    public void sendCode(String account, String email) {
        String source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(source.charAt(random.nextInt(36)));
        }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("教务系统重置密码"); // 发送邮件的标题
        message.setText("验证码：" + code + "，切勿将验证码泄露给他人，本条验证码有效期1分钟。"); // 发送邮件的内容
        message.setTo(email); // 指定要接收邮件的用户邮箱账号
        message.setFrom("1522929908@qq.com"); // 发送邮件的邮箱账号，注意一定要和配置文件中的一致！
        sender.send(message); // 调用send方法发送邮件即可
        redisTemplate.opsForValue().set(account, code.toString(), 60, TimeUnit.SECONDS);
    }

    @Override
    @Transactional
    public boolean resetPassword(ResetPasswordDTO resetPasswordDTO) {
        String realCode = redisTemplate.opsForValue().get(resetPasswordDTO.getAccount());
        if (!StringUtils.equals(realCode,resetPasswordDTO.getCode()))
            return false;
        else {
            UserEntity userEntity = new UserEntity();
            BeanUtils.copyProperties(resetPasswordDTO, userEntity);
            System.out.println(userEntity);
            new LambdaUpdateChainWrapper<>(userMapper).eq(UserEntity::getAccount,userEntity.getAccount()).update(userEntity);
            redisTemplate.delete(userEntity.getAccount());
            return true;
        }
    }
}
