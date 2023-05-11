package com.example.eebighomework.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ArticleRankVo {
    private Integer id;
    private String title;
    private Integer likes;
}
