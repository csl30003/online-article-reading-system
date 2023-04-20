package com.example.eebighomework.vo;

import com.example.eebighomework.model.Comment;
import com.example.eebighomework.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {
    private Integer id;
    private String content;
    private LocalDateTime createTime;
    private String nickname;
    private Integer userId;
    private String username;



}