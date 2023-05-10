package com.example.eebighomework.vo;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ArticleVo {
    private Integer id;
    private String title;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer like;
    private Integer userId;
    private String username;
    private String nickname;
}