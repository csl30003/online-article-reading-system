package com.example.eebighomework.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.eebighomework.mapper.ArticleMapper;
import com.example.eebighomework.model.Article;
import com.example.eebighomework.model.Likes;
import com.example.eebighomework.vo.ArticleRankVo;
import com.example.eebighomework.vo.ArticleVo;
import com.example.eebighomework.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    /**
     * 获取文章列表
     *
     * @param page    页码
     * @param size    每页条数
     * @param keyword 关键词
     * @return 文章列表
     */
    public Page<Article> list(Integer page, Integer size, String keyword) {
        Page<Article> pager = new Page<>(page, size);
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(keyword)) {
            queryWrapper.like("title", keyword);
        }
        queryWrapper.orderByDesc("create_time");
        return articleMapper.selectPage(pager, queryWrapper);
    }

    /**
     * 获取文章详情
     *
     * @param id 文章id
     * @return 文章详情
     */
    public ArticleVo get(Integer id) {
        return articleMapper.selectArticleVoById(id);
    }

    /**
     * 获取文章评论列表
     *
     * @param id 文章id
     * @return 评论列表
     */
    public List<CommentVo> comment(Integer id) {
        return articleMapper.selectCommentVoList(id);
    }

    /**王伟
     * 上传文章
     *
     * @param article 文章信息
     */
    public void upload(Article article) {
        articleMapper.insert(article);
    }

//    /**
//     * 评论文章
//     *
//     * @param comment 评论信息
//     */
//    public void comment(Comment comment) {
//        articleMapper.insertComment(comment);
//    }
//
    /**
     * LISIZT
     * 获取文章排行榜
     *
     * @param days 排行榜类型：daily、weekly、monthly
     * @return 排行榜
     */
    public List<ArticleRankVo> rank(Integer days) {
        return articleMapper.selectRank(days);
    }

    /**
     * LISIZT
     * 点赞文章
     *
     * @param id 文章id
     */
    public void like(Integer id) {
        articleMapper.updateLikeCount(id, 1);
    }

    /**
     * 添加likes表数据
     * @param likes
     */
    public void insertlike(Likes likes){
        articleMapper.insertLikes(likes);
    }

    /**
     * LISIZT
     * 取消点赞文章
     *
     * @param id 文章id
     */
    public void cancelLike(Integer id) {
        articleMapper.updateLikeCount(id, -1);
    }

    /**
     * 删除likes表数据（逻辑）
     * @param id 文章id
     */
    public void unlike(Integer id,Integer userid) {
        articleMapper.deleteLikes(id,userid);
    }
    /**
     * 查找likes表数据
     * @param id 文章id
     */
    public Likes selectlike(Integer id,Integer userid) {
        Likes likes=articleMapper.selectLikes(id,userid);
        return likes;
    }
}


