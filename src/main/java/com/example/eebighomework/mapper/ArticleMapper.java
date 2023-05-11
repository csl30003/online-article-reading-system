package com.example.eebighomework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.eebighomework.model.Article;
import com.example.eebighomework.model.Likes;
import com.example.eebighomework.model.User;
import com.example.eebighomework.vo.ArticleRankVo;
import com.example.eebighomework.vo.ArticleVo;
import com.example.eebighomework.vo.CommentVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 文章 Mapper 接口
 * </p>
 */
@Repository
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {


    /**
     * 查询文章评论列表
     *
     * @param articleId 文章id
     * @return 评论列表
     */
    @Select("select c.id, c.content, c.create_time, u.nickname from comment c left join user u on c.user_id=u.id where c.article_id=#{articleId} order by c.create_time desc")
    @Results(id="commentVo", value={
            @Result(column="id", property="id"),
            @Result(column="content", property="content"),
            @Result(column="create_time", property="createTime"),
            @Result(column="nickname", property="nickname")
    })
    List<CommentVo> selectCommentVoList(@Param("articleId") Integer articleId);

    /**
     * 查询文章排行榜
     *
     * @param days 排行榜类型：DAY,WEEK,MONTH
     * @return 排行榜
     */
    @Select("SELECT id, title, likes\n" +
            "FROM article\n" +
            "WHERE create_time >= DATE_SUB(CURDATE(), INTERVAL #{days} DAY) \n" +
            "ORDER BY likes DESC\n" +
            "LIMIT 10")
    @Results(id="articleRankVo", value={
            @Result(column="id", property="id"),
            @Result(column="title", property="title"),
            @Result(column="likes", property="likes")
    })
    List<ArticleRankVo> selectRank(@Param("days") Integer days);

    /**
     * 更新文章点赞数
     *
     * @param id    文章id
     * @param count 点赞数变化量
     */
    @Update("update article set likes=likes+#{count} where id=#{id}")
    void updateLikeCount(@Param("id") Integer id, @Param("count") Integer count);

    /**
     * 插入点赞记录
     *
     * @param likes 点赞记录
     */
    @Insert("INSERT INTO likes (user_id, article_id) " +
            "VALUES (#{userId}, #{articleId})")
    void insertLikes(Likes likes);

    /**
     * 删除点赞记录
     * @param id
     */
    @Update("update likes set delete_time=NOW() where id=#{id}")
    void deleteLikes(@Param("id") Integer id);

    /**
     * 查询文章详情
     *
     * @param id 文章id
     * @return 文章详情
     */
    //注意API响应属性集内顺序与下面搜索的属性对应
    //这里的a. u. 是表名的简写，用于区分
    @Select("select a.id, a.title, a.content, a.create_time,a.update_time, a.likes, a.user_id, u.username, u.nickname from article a left join user u on a.user_id=u.id where a.id=#{id}")
    @Results(id="articleVo", value={
            @Result(column="id", property="id"),
            @Result(column="title", property="title"),
            @Result(column="content", property="content"),
            @Result(column="create_time", property="createTime"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="likes", property="likes"),
            @Result(column="user_id", property="userId"),
            @Result(column="username", property="username"),
            @Result(column="nickname", property="nickname")
    })
    ArticleVo selectArticleVoById(@Param("id") Integer id);




//    /**
//     * 评论文章
//     *
//     * @param comment 评论信息
//     */
//    @Insert("insert into comment(article_id, user_id, content) values(#{articleId}, #{userId}, #{content})")
//    void insertComment(Comment comment);
}
