package com.example.eebighomework.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 文章数据传输对象
 * </p>
 */
@Data
public class ArticleDto {


    /**
     * 标题
     */
    @ApiModelProperty(value = "标题", required = true)
    private String title;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容", required = true)
    private String content;
}

