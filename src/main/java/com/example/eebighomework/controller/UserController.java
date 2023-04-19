package com.example.eebighomework.controller;


import com.example.eebighomework.common.R;
import com.example.eebighomework.dto.UserDto;
import com.example.eebighomework.model.User;
import com.example.eebighomework.service.UserService;
//import com.example.eebighomework.util.JwtUtil;
import com.example.eebighomework.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * <p>
 * 用户控制器
 * </p>
 */
@RestController
@RequestMapping("/api/user")
@Api(tags = "用户相关接口")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     *
     * @param userDto 用户信息
     * @return 注册结果
     */
    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public R<String> register(@RequestBody UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setNickname(userDto.getNickname());
        String temp = userService.register(user);
        if(Objects.equals(temp, "")){
            return R.success("注册成功");
        }
        return R.error(temp);
    }

    /**
     * 用户登录
     *
     * @param userDto 用户信息
     * @return 登录结果
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public R<String> login(@RequestBody UserDto userDto) {
        User user = userService.login(userDto.getUsername(), userDto.getPassword());
        if (user == null){
            return R.error("登录失败");
        }
        String token = JwtUtil.generateToken(user.getId().toString());
        return R.success(token);
    }
}
