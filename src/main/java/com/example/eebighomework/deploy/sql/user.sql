CREATE TABLE `user`
(
    `id`          int          NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `username`    varchar(255) NOT NULL COMMENT '用户名',
    `password`    varchar(255) NOT NULL COMMENT '密码',
    `nickname`    varchar(255) NOT NULL COMMENT '昵称',
    `email`       varchar(255) DEFAULT NULL COMMENT '邮箱',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime     DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

CREATE TABLE `article`
(
    `id`          int          NOT NULL AUTO_INCREMENT COMMENT '文章id',
    `title`       varchar(255) NOT NULL COMMENT '文章标题',
    `content`     longtext     NOT NULL COMMENT '文章内容',
    `user_id`     int          NOT NULL COMMENT '用户id',
    `like`        int          NOT NULL DEFAULT 0 COMMENT '点赞数量',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime     DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`),
    KEY           `fk_user_id` (`user_id`),
    CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表';

CREATE TABLE `comment`
(
    `id`          int          NOT NULL AUTO_INCREMENT COMMENT '评论id',
    `content`     varchar(255) NOT NULL COMMENT '评论内容',
    `user_id`     int          NOT NULL COMMENT '用户id',
    `article_id`  int          NOT NULL COMMENT '文章id',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime     DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`),
    KEY           `fk_user_id` (`user_id`),
    KEY           `fk_article_id` (`article_id`),
    CONSTRAINT `fk_article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论表';

CREATE TABLE `like`
(
    `id`          int      NOT NULL AUTO_INCREMENT COMMENT '点赞id',
    `user_id`     int      NOT NULL COMMENT '用户id',
    `article_id`  int      NOT NULL COMMENT '文章id',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime     DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`),
    KEY           `fk_user_id` (`user_id`),
    KEY           `fk_article_id` (`article_id`),
    CONSTRAINT `fk_article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='点赞表';
