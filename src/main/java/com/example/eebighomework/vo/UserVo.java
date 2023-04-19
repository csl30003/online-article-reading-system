package com.example.eebighomework.vo;

import com.example.eebighomework.model.User;
import lombok.Data;

@Data
public class UserVo {
    private Integer id;
    private String username;
    private String nickname;

    public UserVo(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
    }
}