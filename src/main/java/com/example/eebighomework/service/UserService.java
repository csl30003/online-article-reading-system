package com.example.eebighomework.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.eebighomework.exception.BaseException;
import com.example.eebighomework.mapper.UserMapper;
import com.example.eebighomework.model.User;
import com.example.eebighomework.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 用户服务实现类
 * </p>
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册
     *
     * @param user 用户信息
     */
    public void register(User user) {
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            throw new BaseException("用户名或密码不能为空");
        }
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.eq("username", user.getUsername());
        if (count(queryWrapper) > 0) {
            throw new BaseException("用户名已存在");
        }
        // 对密码加密
        user.setPassword(EncryptUtil.encrypt(user.getPassword()));

        userMapper.insertUser(user);
    }

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    public User login(String username, String password) {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        // 对密码加密
        String encryptPassword = EncryptUtil.encrypt(password);
        queryWrapper.eq("username", username).eq("password", encryptPassword);
        User user = getOne(queryWrapper);
        if (user == null) {
            throw new BaseException("用户名或密码错误");
        }
        return user;
    }

    /**
     * 获取用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    public User getUser(Integer id) {
        User user = getById(id);
        if (user == null) {
            throw new BaseException("用户不存在");
        }
        return user;
    }
}
