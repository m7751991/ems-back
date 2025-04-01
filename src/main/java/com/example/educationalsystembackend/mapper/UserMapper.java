package com.example.educationalsystembackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.educationalsystembackend.entity.UserEntity;
import com.example.educationalsystembackend.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<UserEntity> {

    //查询密码
    @Select("select password from user where account=#{account}")
    String queryPassword(String account);

    //查询权限
    @Select("select access from user where account=#{account}")
    int queryAccess(String account);

    //添加用户
    @Insert("insert into user values (#{user.account},#{user.password},#{access})")
    void addUser(User user, int access);

    //修改密码
    @Update("update user set password=#{password} where account=#{account}")
    void alterPassword(String account, String password);

    //删除用户
    @Delete("delete from user where account=#{account}")
    void deleteUser(String account);

    //删除多个用户
    @Delete({
            "<script>",
            "DELETE FROM user WHERE account IN",
            "<foreach item='id' collection='ids' open='(' separator=',' close=')'>",
            "'${id}'",
            "</foreach>",
            "</script>"
    })
    void deleteUsers(@Param("ids") List<String> ids);
}
