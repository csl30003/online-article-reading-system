package com.example.eebighomework.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * <p>
 * 评论数据传输对象
 * </p>
 */

@Data
public class CommentDto {


        @ApiModelProperty(value = "评论内容", required = true)
        private String content;

}
