package com.example.educationalsystembackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.educationalsystembackend.dto.AlterPasswordDTO;
import com.example.educationalsystembackend.dto.ResetPasswordDTO;
import com.example.educationalsystembackend.entity.StudentEntity;
import com.example.educationalsystembackend.entity.TeacherEntity;
import com.example.educationalsystembackend.entity.UserEntity;
import com.example.educationalsystembackend.pojo.Student;
import com.example.educationalsystembackend.result.Result;
import com.example.educationalsystembackend.service.GradeService;
import com.example.educationalsystembackend.service.StudentService;
import com.example.educationalsystembackend.service.TeacherService;
import com.example.educationalsystembackend.service.UserService;
import com.example.educationalsystembackend.util.JWT;
import com.example.educationalsystembackend.vo.AlterPasswordVO;
import com.example.educationalsystembackend.vo.ResetPasswordVO;
import com.example.educationalsystembackend.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@Validated
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @PostMapping("/login")
    public Result login(@Validated @RequestBody UserVO userVO) {
        Map<String, Object> map = new LinkedHashMap<>();
        LambdaQueryWrapper<UserEntity> userLambdaQueryWrapper = new LambdaQueryWrapper<UserEntity>()
                .eq(UserEntity::getAccount, userVO.getAccount());

        UserEntity user = userService.getOne(userLambdaQueryWrapper);
        Optional<UserEntity> optional = Optional.ofNullable(user);
        System.out.println("登录信息：" + user);
        if (!optional.isPresent() || !StringUtils.equals(userVO.getPassword(), user.getPassword())) // 密码错误
            return Result.success(401, "登录失败,账号或密码错误", null);
        else { // 密码正确
            map.put("token", JWT.getToken(user.getAccount())); // token
            map.put("access", user.getAccess()); // 权限
            String name; // 姓名
            if (user.getAccess() == 1)
                map.put("name", "管理员");
            else if (user.getAccess() == 2) {
                LambdaQueryWrapper<StudentEntity> studentLambdaQueryWrapper = new LambdaQueryWrapper<StudentEntity>()
                        .eq(StudentEntity::getId, user.getAccount()).select(StudentEntity::getName);
                name = studentService.getOne(studentLambdaQueryWrapper).getName();
                map.put("name", name);
            } else {
                LambdaQueryWrapper<TeacherEntity> teacherLambdaQueryWrapper = new LambdaQueryWrapper<TeacherEntity>()
                        .eq(TeacherEntity::getId, user.getAccount()).select(TeacherEntity::getName);
                name = teacherService.getOne(teacherLambdaQueryWrapper).getName();
                map.put("name", name);
            }
            return Result.success(200, "登录成功", map);
        }
    }

    @PostMapping("/alterPassword")
    public Result alterPassword(@RequestBody @Validated AlterPasswordVO alterPasswordVO) {
        String account = JWT.token(httpServletRequest.getHeader("Authorization"));
        AlterPasswordDTO alterPasswordDTO = new AlterPasswordDTO();
        BeanUtils.copyProperties(alterPasswordVO, alterPasswordDTO);
        alterPasswordDTO.setAccount(account);
        boolean result = userService.alterPassword(alterPasswordDTO);
        if (result) {
            return Result.success(200, "修改密码成功", null);
        } else {
            return Result.success(400, "原密码错误", null);
        }
    }

    @GetMapping("/sendCode/{account}/{email}")
    public Result sendCode(@NotBlank(message = "帐号不能为空") @PathVariable("account") String account,
            @Email(message = "邮箱格式错误") @PathVariable("email") String email) {
        userService.sendCode(account, email);
        return Result.success(200, "验证码已发送至邮箱，有效期1分钟", null);
    }

    @PostMapping("/resetPassword")
    public Result resetPassword(@RequestBody @Validated ResetPasswordVO resetPasswordVO) {
        ResetPasswordDTO resetPasswordDTO = new ResetPasswordDTO();
        BeanUtils.copyProperties(resetPasswordVO, resetPasswordDTO);
        boolean result = userService.resetPassword(resetPasswordDTO);
        if (!result)
            return Result.success(500, "验证码错误", null);
        else {
            return Result.success(200, "密码重置成功", null);
        }
    }

    /**
     * 检查用户名是否已存在
     * 
     * @param username 用户名
     * @return 是否存在
     */
    public boolean isUsernameExists(String username) {
        // 使用LambdaQueryWrapper构建查询条件
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<UserEntity>()
                .eq(UserEntity::getAccount, username);

        // 查询用户
        UserEntity user = userService.getOne(queryWrapper);
        // 如果查询结果不为空，则表示用户已存在
        return user != null;
    }

    /**
     * 学生注册
     *
     * @param student  学生信息
     * @param password 密码
     * @return Response
     */
    @PostMapping("/register")
    public Result register(@RequestBody Student student) {

        // System.out.println("注册信息：" + student);
        // // 检查两次密码是否一致
        // if (!student.getPassword().equals(student.getConfirmPassword())) {
        //     return Result.success(400, "两次密码不一致", null);
        // }

        // // 检查用户名是否已存在
        // if (isUsernameExists(student.getUserName())) {
        //     return Result.success(400, "该用户名已存在", null);
        // }
        // // 创建学生信息
        // studentService.addStuent(student);

        return Result.success(200, "注册成功", null);
    }
}
