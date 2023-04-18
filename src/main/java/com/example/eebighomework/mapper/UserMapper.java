package com.example.eebighomework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.eebighomework.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}