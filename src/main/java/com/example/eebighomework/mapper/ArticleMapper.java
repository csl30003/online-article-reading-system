package com.example.eebighomework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.eebighomework.model.Article;
import com.example.eebighomework.vo.ArticleVo;
import org.apache.ibatis.annotations.*;

/**
 * <p>
 * 文章 Mapper 接口
 * </p>
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

//    /**
//     * 查询文章评论列表
//     *
//     * @param articleId 文章id
//     * @return 评论列表
//     */
//    @Select("select c.id, c.content, c.create_time, u.nickname from comment c left join user u on c.user_id=u.id where c.article_id=#{articleId} order by c.create_time desc")
//    @Results(id="commentVoMap", value={
//            @Result(column="id", property="id"),
//            @Result(column="content", property="content"),
//            @Result(column="create_time", property="createTime"),
//            @Result(column="nickname", property="nickname")
//    })
//    List<CommentVo> selectCommentVoList(@Param("articleId") Integer articleId);
//
//    /**
//     * 查询文章排行榜
//     *
//     * @param type 排行榜类型：daily、weekly、monthly
//     * @return 排行榜
//     */
//    @Select("select id, title, like_count from article where date(create_time)=date_sub(curdate(), interval 1 #{type}) order by like_count desc limit 10")
//    @Results(id="articleRankVoMap", value={
//            @Result(column="id", property="id"),
//            @Result(column="title", property="title"),
//            @Result(column="like_count", property="likeCount")
//    })
//    List<ArticleRankVo> selectRank(@Param("type") String type);
//
//    /**
//     * 更新文章点赞数
//     *
//     * @param id    文章id
//     * @param count 点赞数变化量
//     */
//    @Update("update article set like_count=like_count+#{count} where id=#{id}")
//    void updateLikeCount(@Param("id") Integer id, @Param("count") Integer count);

    /**
     * 查询文章详情
     *
     * @param id 文章id
     * @return 文章详情
     */
    //注意API响应属性集内顺序与下面搜索的属性对应
    @Select("select a.id, a.title, a.content, a.create_time,a.update_time, a.like, a.user_id, u.username, u.nickname from article a left join user u on a.user_id=u.id where a.id=#{id}")
    @Results(id="articleVo", value={
            @Result(column="id", property="id"),
            @Result(column="title", property="title"),
            @Result(column="content", property="content"),
            @Result(column="create_time", property="createTime"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="like", property="like"),
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
