package com.example.eebighomework.vo;

import com.example.eebighomework.model.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleVo {
    private Integer id;
    private String title;
    private String content;
    private Integer like;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private UserVo userVo;

    public ArticleVo(Article article, User user) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.likeCount = article.getLikeCount();
        this.createTime = article.getUploadTime();
        this.updateTime = article.getUpdateTime();
        this.userVo = new UserVo(user);
    }
}