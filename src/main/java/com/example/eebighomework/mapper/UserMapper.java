package com.example.eebighomework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.eebighomework.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Insert("INSERT INTO user (username, password, nickname) " +
            "VALUES (#{username}, #{password}, #{nickname})")
    void insertUser(User user);
}