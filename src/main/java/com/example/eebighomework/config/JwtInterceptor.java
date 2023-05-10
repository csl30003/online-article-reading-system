package com.example.eebighomework.config;


import com.example.eebighomework.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果是OPTIONS则结束请求
        // （用postman发送请求和正常请求不一样一个点是正常请求会先发送option方法的请求来确保安全（有一定条件），再发送别的请求，所以需要以下代码放行）
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }

        String token = request.getHeader("Authorization");
        System.out.println(token);
        if (token == null) {
            throw new RuntimeException("未授权访问");
        }
        // 验证token，并从中获取用户信息
        if (!jwtUtil.validateToken(token)) {
            throw new RuntimeException("未授权访问");
        }
        int userId = Integer.parseInt(jwtUtil.getUserIdFromToken(token));
        request.setAttribute("userId", userId);
        return true;
    }
}