package com.example.eebighomework.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.eebighomework.exception.BaseException;
import com.example.eebighomework.mapper.UserMapper;
import com.example.eebighomework.model.User;
import com.example.eebighomework.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
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
    public String register(User user) {
        // 判断用户名和密码是否为空
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            return "用户名或密码不能为空";
        }


        //判断用户名是否已存在，如果用户名存在但是deletetime不为空，代表该用户已被删除，可以添加
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.eq("username", user.getUsername()).isNull("delete_time");
        if (count(queryWrapper) > 0) {
            return "用户名已存在";
        }
        // 对密码加密
        user.setPassword(EncryptUtil.encrypt(user.getPassword()));

        userMapper.insertUser(user);
        return "";
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
        //搜索用户，可能会有重名，加上delete time = null判断用户是否存在
        queryWrapper.eq("username", username).eq("password", encryptPassword).isNull("delete_time");
        User user = getOne(queryWrapper);
        if (user == null) {
            return null;
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
