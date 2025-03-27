package com.example.educationalsystembackend.exception;

import com.example.educationalsystembackend.result.Result;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        return Result.success(500, Objects.requireNonNull(ex.getFieldError()).getDefaultMessage(), null);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result ConstraintViolationExceptionHandler(ConstraintViolationException ex) {
        List<String> msgList = new ArrayList<>();
        for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
            msgList.add(constraintViolation.getMessage());
        }
        String messages = StringUtils.join(msgList, ';');
        return Result.success(500, messages, null);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        System.out.println(ex.getMessage() + "1221");
        if (ex.getMessage().contains("Duplicate entry")) {
            String[] split = ex.getMessage().split(" ");
            String msg = split[2] + "已存在";
            msg = msg.replace("'", "");
            return Result.success(500, msg, null);
        }
        return Result.success(500, "未知错误", null);
    }

}
